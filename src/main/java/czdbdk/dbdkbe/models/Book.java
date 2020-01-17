package czdbdk.dbdkbe.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import czdbdk.dbdkbe.jview.DataView;
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
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * @author Tereza Holm
 */
@Entity(name = "book")
@Data
public class Book implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Id
    @Column(name = "id")
    private Long bookId;
    @Column(name = "book_id")
    @JsonView(DataView.SummaryView.class)
    private Long bookNumber;
    @JsonView(DataView.SummaryView.class)
    private String title;
    @Column(name = "year_of_issue")
    @JsonView(DataView.SummaryView.class)
    private int yearOfIssue;
    @Column(name = "date_of_addition")
    @JsonView(DataView.SummaryView.class)
    private Date dateOfAddition = new Date(System.currentTimeMillis());
    @JsonView(DataView.DetailView.class)
    private String publisher;
    @JsonView(DataView.DetailView.class)
    private String description;
    @JsonView(DataView.DetailView.class)
    private String isbn;
    @Column(name = "number_of_pages")
    @JsonView(DataView.DetailView.class)
    private int numberOfPages;
    @Column(name = "original_language")
    @JsonView(DataView.DetailView.class)
    private String originalLanguage;
    @Column(name = "link_goodreads")
    @JsonView(DataView.DetailView.class)
    private String linkGoodreads;
    @Column(name = "link_databaze")
    @JsonView(DataView.DetailView.class)
    private String linkDatabaze;
    @Column(name = "link_cbdb")
    @JsonView(DataView.DetailView.class)
    private String linkCbdb;
    /*@Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "image")
    @JsonView(DataView.SummaryView.class)
    private byte[] imageURL;*/
    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "author_id")
    )
    @JsonManagedReference
    @JsonView(DataView.SummaryView.class)
    private List<Author> authors;
    @ManyToMany
    @JoinTable(
            name = "book_tag",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "tag_id")
    )
    @JsonManagedReference
    @JsonView(DataView.SummaryView.class)
    private List<Tag> tags;
}
