package czdbdk.dbdkbe.repositories;

import czdbdk.dbdkbe.models.databaseModels.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Tereza Holm
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {
    boolean existsByFirstNameAndLastName(String firstName, String lastName);
}
