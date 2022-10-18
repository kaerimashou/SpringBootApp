package org.azati.first_test_task.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BOOK")
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(generator = "increment")
    private Long id;

    @Column(name = "TITLE")
    @NotEmpty(message = "Title must not be empty")
    @Size(min = 2, max = 30, message = "Title should be between 2 and 30 characters")
    private String title;

    @Column(name = "AUTHOR")
    @NotEmpty(message = "Author must not be empty")
    private String author;

    @Column(name = "BOOK_YEAR")
    @NotNull(message = "Year must not be empty")
    @Min(value = 0, message = "Year cannot be less than 0")
    @Max(value = 2022, message = "Year cannot be greater than 2022")
    private Integer bookYear;

    @Column(name = "COVER")
    private String cover;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private Person borrower;

    public Person getBorrower() {
        return borrower;
    }

    public void setBorrower(Person borrower) {
        this.borrower = borrower;
    }

    public Book(Book book) {
        this.id = book.id;
        this.author = book.author;
        this.bookYear = book.bookYear;
        this.borrower = book.borrower;
        this.title = book.title;
    }

    @Override
    public String toString() {
        return title + ", " + author + ", " + bookYear;
    }
}
