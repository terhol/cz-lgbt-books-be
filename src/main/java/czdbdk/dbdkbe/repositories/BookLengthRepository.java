package czdbdk.dbdkbe.repositories;

import czdbdk.dbdkbe.models.databaseModels.BookLength;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Tereza Holm
 */
@Repository
public interface BookLengthRepository extends PagingAndSortingRepository<BookLength, Long> {
    @Query(value = "select * from book_length", nativeQuery = true)
    List<BookLength> findAllLengths();

    BookLength findBySlug(String slug);
}
