package czdbdk.dbdkbe.repositories;

import czdbdk.dbdkbe.models.databaseModels.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Tereza Holm
 */
public interface TagRepository extends JpaRepository<Tag, Long> {

    Long countBySlug(String slug);

    @Query(value = "SELECT a.name FROM Tag a WHERE slug = ?1")
    String getNameFromSlug(String currentSlug);
}
