package czdbdk.dbdkbe.repositories;

import czdbdk.dbdkbe.models.databaseModels.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @author Tereza Holm
 */
@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long>, JpaSpecificationExecutor {
    @Override
    Page<Book> findAll(Specification specification, Pageable pageable);

    Optional<Book> findBySlug(String slug);

    @Query(value = "select max(b.date_of_addition) from book b", nativeQuery = true)
    LocalDate findMaxDate();

    @Query(value = "select * from book ORDER BY RANDOM() limit 1", nativeQuery = true)
    Book findRandomBook();

    @Query(value = "select count(b) from book b where b.language_slug = ?1", nativeQuery = true)
    Long countByLanguageSlug(String languageSlug);

    @Query(value = "select count(b) from book b where (b.number_of_pages > ?1) and (b.number_of_pages < ?2)", nativeQuery = true)
    int countByNumberOfPages(int min, int Max);
}
