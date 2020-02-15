package czdbdk.dbdkbe.exceptions;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tereza Holm
 */
@Controller
@Data
public class CommonErrorController implements ErrorController {
    @Autowired
    private ErrorAttributes errorAttributes;

    @ResponseBody
    @RequestMapping(value = "error")
    public ExceptionResponse error(WebRequest request, HttpServletResponse response) {
        return new ExceptionResponse(response.getStatus(), getErrorAttributes(request));
    }

    @Override
    public String getErrorPath() {
        return "error";
    }

    private Map<String, Object> getErrorAttributes(WebRequest request) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.putAll(errorAttributes.getErrorAttributes(request, false));
        return errorMap;
    }
}
