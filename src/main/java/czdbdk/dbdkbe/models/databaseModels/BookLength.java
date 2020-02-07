package czdbdk.dbdkbe.models.databaseModels;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * @author Tereza Holm
 */
@Data
@Entity(name = "book_length")
public class BookLength {
    private String name;
    @Id
    private String slug;
    @Column(name = "min_pages")
    private int minPages;
    @Column(name = "max_pages")
    private int maxPages;
    @Transient
    private int booksMatchesValue;
}
