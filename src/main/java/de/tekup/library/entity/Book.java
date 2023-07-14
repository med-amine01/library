package de.tekup.library.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.Entity;

import javax.persistence.ManyToOne;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Book extends AbstractEntity {

    private String title;

    @ManyToOne
    private Author author;

    private String publisher;

    private int yearOfPublication;
}
