package czdbdk.dbdkbe.exceptions;

import lombok.Data;

import java.util.Map;

/**
 * @author Tereza Holm
 */
@Data
class ExceptionResponse {
    private Integer status;
    private String path;
    private String errorMessage;

    ExceptionResponse(int status, Map<String, Object> errorAttributes) {
        setStatus(status);
        setPath((String) errorAttributes.get("path"));
        setErrorMessage((String) errorAttributes.get("message"));
    }
}
