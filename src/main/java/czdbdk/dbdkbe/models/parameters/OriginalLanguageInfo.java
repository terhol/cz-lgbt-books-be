package czdbdk.dbdkbe.models.parameters;

import czdbdk.dbdkbe.models.databaseModels.Language;
import lombok.Data;

/**
 * @author Tereza Holm
 */
@Data
class OriginalLanguageInfo {
    private Long booksMatchesValue;
    private String name;
    private String slug;

    OriginalLanguageInfo(Language language) {
        name = language.getName();
        slug = language.getSlug();
    }
}
