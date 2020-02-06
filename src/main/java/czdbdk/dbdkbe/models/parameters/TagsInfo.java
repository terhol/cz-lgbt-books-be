package czdbdk.dbdkbe.models.parameters;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author Tereza Holm
 */
@Data
@Component
public class TagsInfo {
    private String name;
    private String slug;
    private Long booksMatchesValue;


    public TagsInfo(){}

    public TagsInfo(String slug){
        this.slug = slug;
    }
}
