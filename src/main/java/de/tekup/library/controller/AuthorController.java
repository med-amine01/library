package de.tekup.library.controller;

import com.github.javafaker.Faker;
import de.tekup.library.entity.Author;
import de.tekup.library.entity.Book;
import de.tekup.library.service.AuthorService;
import de.tekup.library.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/authors")
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;
    private final BookService bookService;

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Optional<Author> optionalAuthor = authorService.getAuthorById(id);

        return optionalAuthor.map(author -> new ResponseEntity<>(author, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        Author createdAuthor = authorService.createAuthor(author);

        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        Author updatedAuthor = authorService.updateAuthor(id, author);

        return updatedAuthor != null ? new ResponseEntity<>(updatedAuthor, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/generate/{numAuthors}/{numBooksPerAuthor}")
    public ResponseEntity<List<Author>> generateBooksAndAuthors(@PathVariable int numAuthors,
                                                                @PathVariable int numBooksPerAuthor) {
        Faker faker = new Faker();
        List<Author> authors = new ArrayList<>();

        for (int i = 0; i < numAuthors; i++) {
            Author author = new Author();
            author.setName(faker.book().author());
            authorService.createAuthor(author);

            List<Book> books = new ArrayList<>();

            for (int j = 0; j < numBooksPerAuthor; j++) {
                Book book = new Book();
                book.setTitle(faker.book().title());
                book.setAuthor(author);
                book.setPublisher(faker.book().publisher());
                book.setYearOfPublication(faker.number().numberBetween(1900, 2023));
                bookService.createBook(book);

                books.add(book);
            }

            author.setBooks(books);
            authors.add(author);
        }

        return new ResponseEntity<>(authors, HttpStatus.CREATED);
    }

}