package czdbdk.dbdkbe.repositories;

import czdbdk.dbdkbe.models.databaseModels.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Tereza Holm
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query(value = "SELECT a.name FROM tag a WHERE slug = ?1", nativeQuery = true)
    String getNameFromSlug(String currentSlug);

    @Override
    List<Tag> findAll();

    Tag findBySlug(String slug);
}
