package bi.leo.picker.dto;

import lombok.Data;

@Data
public class ExtractTaskDto {

    private Long id;

    private String extractUrl;

    private String extractExpression;

    private Integer extractOption;

    // HOURLY, DAILY, MINUTELY
    private String taskType;

    // unit: second
    private Integer taskInterval;

    private Boolean activeFlag;

    private Boolean shareFlag;

}
