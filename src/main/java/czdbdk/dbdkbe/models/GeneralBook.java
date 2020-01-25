package czdbdk.dbdkbe.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import czdbdk.dbdkbe.jview.DataView;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Tereza Holm
 */
@MappedSuperclass
@Data
public class GeneralBook {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Id
    @Column(name = "id")
    private Long bookId;
    @JsonIgnore
    @Column(name = "book_id")
    @JsonProperty("id")
    @JsonView(DataView.SummaryView.class)
    private Long bookNumber;
    @JsonView(DataView.SummaryView.class)
    private String title;
    @Column(name = "year_of_issue")
    @JsonView(DataView.SummaryView.class)
    private String yearOfIssue;
    @Column(name = "date_of_addition")
    @JsonView(DataView.SummaryView.class)
    private LocalDate dateOfAddition;
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
    @JsonProperty("links.goodreads")
    @JsonView(DataView.DetailView.class)
    private String linkGoodreads;
    @JsonProperty("links.databazeKnih")
    @Column(name = "link_databaze")
    @JsonView(DataView.DetailView.class)
    private String linkDatabaze;
    @JsonProperty("links.cbdb")
    @Column(name = "link_cbdb")
    @JsonView(DataView.DetailView.class)
    private String linkCbdb;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "author_id")
    )
    @JsonView(DataView.SummaryView.class)
    private List<Author> authors;
    @ManyToMany
    @JoinTable(
            name = "book_tag",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "tag_id")
    )
    @JsonView(DataView.SummaryView.class)
    private List<Tag> tags;
}
