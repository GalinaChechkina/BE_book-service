package ait.cohort34.book.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "isbn")
@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "isbn")
    String isbn;
    @Column(name = "title")
    String title;
    @ManyToMany
            @JoinTable(
                    name = "book_authors",
                    joinColumns = @JoinColumn(name = "book_isbn"),
                    inverseJoinColumns = @JoinColumn(name = "author_name")
            )
    Set<Author> authors;
    @ManyToOne
            @JoinTable(
                    name = "book_publisher",
                    joinColumns = @JoinColumn(name = "book_isbn"),
                    inverseJoinColumns = @JoinColumn(name = "publisher_publisher_name")
    )
    Publisher publisher;
}
