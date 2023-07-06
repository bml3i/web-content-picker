package bi.leo.picker.model;

import bi.leo.picker.entity.ExtractTask;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtractRequest {

    private String extractUrl;

    private Integer extractOption;

    private String extractExpression;


    public ExtractRequest(ExtractTask extractTask) {
        this.extractUrl = extractTask.getExtractUrl();
        this.extractOption = extractTask.getExtractOption();
        this.extractExpression = extractTask.getExtractExpression();
    }
}