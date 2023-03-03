package bi.leo.picker.exception;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CustomWebDriverException extends Exception {

    private String errorCode;

    private String errorMessage;

    public CustomWebDriverException() {
        super();
    }

    public CustomWebDriverException(String code, String message) {
        super();
        this.errorCode = code;
        this.errorMessage = message;
    }
}
