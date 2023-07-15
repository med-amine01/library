package de.tekup.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import java.util.List;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Author extends AbstractEntity {

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private List<Book> books;

    @PreRemove
    private void removeAuthorFromBooks() {
        if (books != null) {
            for (Book book : books) {
                book.setAuthor(null);
            }
        }
    }
}