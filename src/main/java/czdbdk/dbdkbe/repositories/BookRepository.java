package czdbdk.dbdkbe.repositories;

import czdbdk.dbdkbe.models.Book;
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
}
