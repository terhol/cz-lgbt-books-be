package czdbdk.dbdkbe.exceptions;

import lombok.Data;

import java.util.Map;

/**
 * @author Tereza Holm
 */
@Data
public class ExceptionResponse {

    private Integer status;
    private String path;
    private String errorMessage;
    //private String trace;

    public ExceptionResponse(int status, Map<String, Object> errorAttributes){
        this.setStatus(status);
        this.setPath((String) errorAttributes.get("path"));
        this.setErrorMessage((String) errorAttributes.get("message"));
        //this.setTrace((String) errorAttributes.get("trace"));
    }


}
