package czdbdk.dbdkbe.models.databaseModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import czdbdk.dbdkbe.jview.DataView;
import czdbdk.dbdkbe.models.Links;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.time.LocalDate;
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
    private Long id;
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
    @Embedded
    private Links links;
    @JsonView(DataView.SummaryView.class)
    private String imageURL;
    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = {@JoinColumn(name = "name", referencedColumnName = "name"),
                    @JoinColumn(name = "surname", referencedColumnName = "surname")}
    )
    @JsonView(DataView.SummaryView.class)
    private List<Author> authors;
    @ManyToMany
    @JoinTable(
            name = "book_tag",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_slug", referencedColumnName = "slug")
    )
    @JsonView(DataView.SummaryView.class)
    private List<Tag> tags;
    @JsonView(DataView.SummaryView.class)
    private String slug;
}
