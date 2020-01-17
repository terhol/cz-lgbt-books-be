package czdbdk.dbdkbe.models;

import lombok.Data;

/**
 * @author Tereza Holm
 */
@Data
public class BookCount {

    private Long total;

    public BookCount(Long total){
        this.total = total;
    }


}
