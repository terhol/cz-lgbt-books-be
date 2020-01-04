package czdbdk.dbdkbe.repositories;

import czdbdk.dbdkbe.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Tereza Holm
 */
public interface BookRepository extends JpaRepository<Book, Long> {
}
