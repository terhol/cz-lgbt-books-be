package czdbdk.dbdkbe.models;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import java.sql.Date;
import java.util.List;

/**
 * @author Tereza Holm
 */
@Entity(name = "book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "book_id")
    private int bookId;
    private String title;
    @Column(name = "year_of_issue")
    private int yearOfIssue;
    @Column(name = "date_of_addition")
    private Date dateOfAddition = new Date(System.currentTimeMillis());
    private String publisher;
    private String description;
    private String isbn;
    @Column(name = "number_of_pages")
    private int numberOfPages;
    @Column(name = "original_language")
    private String originalLanguage;
    @Column(name = "link_goodreads")
    private String linkGoodreads;
    @Column(name = "link_databaze")
    private String linkDatabaze;
    @Column(name = "link_cbdb")
    private String linkCbdb;
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] image;
    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;
    @ManyToMany
    @JoinTable(
            name = "book_tag",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;
}
