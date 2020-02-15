package czdbdk.dbdkbe.models;

import czdbdk.dbdkbe.models.databaseModels.Book;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Tereza Holm
 */
@Data
public class BooksWithTotal {
    private final long total;
    private final List<Book> books;

    public BooksWithTotal(Page<Book> booksPage) {
        total = booksPage.getTotalElements();
        books = booksPage.getContent();
    }
}
