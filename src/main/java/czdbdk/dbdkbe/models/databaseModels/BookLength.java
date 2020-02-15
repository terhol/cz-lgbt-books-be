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
public class BookLength implements Comparable<BookLength> {
    private String name;
    @Id
    private String slug;
    @Column(name = "min_pages")
    private Integer minPages;
    @Column(name = "max_pages")
    private Integer maxPages;
    @Transient
    private Integer booksMatchesValue;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookLength)) return false;
        BookLength bookLength = (BookLength) o;
        return minPages.equals(bookLength.getMinPages());
    }

    @Override
    public int hashCode() {
        return minPages * 31;
    }

    @Override
    public int compareTo(BookLength bookLength) {
        return minPages.compareTo(bookLength.getMinPages());
    }

    public void nullMinPages() {
        if (minPages == 0) minPages = null;
    }

    public void nullMaxPages() {
        if (maxPages == 100000) maxPages = null;
    }
}
