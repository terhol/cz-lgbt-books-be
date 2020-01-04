package czdbdk.dbdkbe.repositories;

import czdbdk.dbdkbe.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Tereza Holm
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
}
