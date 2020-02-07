package czdbdk.dbdkbe.models.parameters;

import lombok.Data;

/**
 * @author Tereza Holm
 */
@Data
public class BookSizeInfo {

    private String slug;
    private String name;
    private Long booksMatchesValue;

    public BookSizeInfo(String name){
        this.name = name;

    }

}
