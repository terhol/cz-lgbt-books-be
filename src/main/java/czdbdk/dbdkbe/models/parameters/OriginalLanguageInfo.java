package czdbdk.dbdkbe.models.parameters;

import czdbdk.dbdkbe.models.databaseModels.Language;
import lombok.Data;

/**
 * @author Tereza Holm
 */
@Data
public class OriginalLanguageInfo {
    private Long booksMatchesValue;
    private String name;
    private String slug;

    public OriginalLanguageInfo(Language language) {
        this.name = language.getName();
        this.slug = language.getSlug();
    }
}
