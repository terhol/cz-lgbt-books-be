package czdbdk.dbdkbe.repositories;

import czdbdk.dbdkbe.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Tereza Holm
 */
@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    Page<Book> findAll(Pageable pageable);

    Optional<Book> findBySlug(String slug);
}
