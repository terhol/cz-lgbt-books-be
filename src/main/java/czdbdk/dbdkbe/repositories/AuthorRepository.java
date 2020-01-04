package czdbdk.dbdkbe.repositories;

import czdbdk.dbdkbe.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Tereza Holm
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
