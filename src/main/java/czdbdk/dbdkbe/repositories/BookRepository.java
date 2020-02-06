package czdbdk.dbdkbe.repositories;

import czdbdk.dbdkbe.models.databaseModels.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @author Tereza Holm
 */
@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    Page<Book> findAll(Pageable pageable);

    Optional<Book> findBySlug(String slug);

    @Query(value = "select max(b.date_of_addition) from Book b", nativeQuery = true)
    LocalDate findMaxDate();

    @Query(value = "select * from Book ORDER BY RANDOM() limit 1", nativeQuery = true)
    Book findRandomBook();

    Long countByOriginalLanguage(String originalLanguage);

    @Query(value = "select count(b) from Book b where (b.numberOfPages > ?1) and (b.number_of_pages < ?2)")
    Long countByNumberOfPages(Long min, Long Max);
}
