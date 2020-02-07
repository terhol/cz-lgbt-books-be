package czdbdk.dbdkbe.models.parameters;

import lombok.Data;

/**
 * @author Tereza Holm
 */
@Data
public class TagsInfo {
    private String name;
    private String slug;
    private Long booksMatchesValue;

    public TagsInfo(String slug){
        this.slug = slug;
    }
}
