package czdbdk.dbdkbe.repositories;

import czdbdk.dbdkbe.models.databaseModels.BookTag;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Tereza Holm
 */
public interface BookTagRepository extends PagingAndSortingRepository<BookTag, Long> {
    Long countByTagSlug(String slug);
}
