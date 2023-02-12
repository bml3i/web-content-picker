package bi.leo.picker.exception;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WebDriverPoolException extends Exception {

    private String errorCode;

    private String errorMessage;

    public WebDriverPoolException() {
        super();
    }

    public WebDriverPoolException(String code, String message) {
        super();
        this.errorCode = code;
        this.errorMessage = message;
    }
}
