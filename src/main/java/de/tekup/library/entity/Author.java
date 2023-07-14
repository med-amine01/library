package de.tekup.library.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Entity;

import javax.persistence.OneToMany;
import java.util.List;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Author extends AbstractEntity {

    private String name;

    @OneToMany(mappedBy = "author")
    private List<Book> books;
}