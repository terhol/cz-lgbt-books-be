package czdbdk.dbdkbe.repositories;

import czdbdk.dbdkbe.models.databaseModels.Language;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author Tereza Holm
 */
public interface LanguageRepository extends PagingAndSortingRepository<Language, Long> {
    List<Language> findAll();
}
