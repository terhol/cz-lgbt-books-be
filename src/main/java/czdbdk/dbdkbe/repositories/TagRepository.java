package czdbdk.dbdkbe.repositories;

import czdbdk.dbdkbe.models.databaseModels.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Tereza Holm
 */
public interface TagRepository extends JpaRepository<Tag, Long> {


    @Query(value = "SELECT a.name FROM Tag a WHERE slug = ?1", nativeQuery = true)
    String getNameFromSlug(String currentSlug);

    List<Tag> findAll();
}
