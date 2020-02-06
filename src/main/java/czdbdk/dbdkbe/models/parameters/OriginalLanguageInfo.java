package czdbdk.dbdkbe.models.parameters;

import lombok.Data;


/**
 * @author Tereza Holm
 */
@Data
public class OriginalLanguageInfo {
    private Long booksMatchesValue;
    private String name;
    private String slug;

    public OriginalLanguageInfo(String name){
        this.name = name; }



}
