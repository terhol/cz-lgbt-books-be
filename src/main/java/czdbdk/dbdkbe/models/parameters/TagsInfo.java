package czdbdk.dbdkbe.models.parameters;

import lombok.Data;

/**
 * @author Tereza Holm
 */
@Data
class TagsInfo {
    private String name;
    private String slug;
    private Long booksMatchesValue;

    TagsInfo(String slug) {
        this.slug = slug;
    }
}
