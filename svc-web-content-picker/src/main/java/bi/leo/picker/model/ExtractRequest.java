package bi.leo.picker.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtractRequest {

    private String extractUrl;

    private Integer extractOption;

    private String extractExpression;

}