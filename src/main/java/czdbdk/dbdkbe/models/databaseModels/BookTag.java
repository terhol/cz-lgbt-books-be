package czdbdk.dbdkbe.models.databaseModels;

import czdbdk.dbdkbe.models.BookTagId;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * @author Tereza Holm
 */
@Data
@Entity(name = "book_tag")
@IdClass(BookTagId.class)
public class BookTag {
    @Id
    @Column(name = "book_id")
    private Long bookId;
    @Id
    @Column(name = "tag_slug")
    private String tagSlug;
}
