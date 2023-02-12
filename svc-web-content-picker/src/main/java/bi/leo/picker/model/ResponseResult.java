package bi.leo.picker.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseResult {

    @JsonProperty("success")
    private boolean success;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("data")
    private Object data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("error")
    private CustomError error;

    public ResponseResult buildErrorMessage(CustomError error) {
        this.setSuccess(false);

        if (error != null) {
            this.setError(error);
        }

        return this;
    }


    public ResponseResult buildSuccessMessage() {
        return this.buildSuccessMessage(null);
    }

    public ResponseResult buildSuccessMessage(Object data) {
        this.setSuccess(true);

        if (data != null) {
            this.setData(data);
        }

        return this;
    }

}
