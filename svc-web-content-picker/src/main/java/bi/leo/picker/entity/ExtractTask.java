package bi.leo.picker.entity;

import bi.leo.picker.dto.ExtractTaskDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "extract_task")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtractTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String extractUrl;

    private String extractExpression;

    private Integer extractOption;

    // HOURLY, DAILY, MINUTELY
    private String taskType;

    // unit: second
    private Integer taskInterval;

    private LocalDateTime nextRunDateTime;

    private Boolean activeFlag;

    // C = Completed, I = In-Process, F = Failure
    private String processStatus;

    private LocalDateTime createDateTime;

    private LocalDateTime updateDateTime;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "task_history_map")
    private List<ExtractHistory> extractHistories = new ArrayList<>();

    @Override
    public String toString() {
        return "ExtractTask{" +
                "id=" + id +
                ", extractUrl='" + extractUrl + '\'' +
                ", extractExpression='" + extractExpression + '\'' +
                ", extractOption=" + extractOption +
                ", taskType='" + taskType + '\'' +
                ", taskInterval=" + taskInterval +
                ", nextRunDateTime=" + nextRunDateTime +
                ", activeFlag=" + activeFlag +
                ", processStatus='" + processStatus + '\'' +
                ", createDateTime=" + createDateTime +
                ", updateDateTime=" + updateDateTime +
                '}';
    }

    // convert from DTO
    public ExtractTask(ExtractTaskDto extractTaskDto) {
        this.setId(extractTaskDto.getId());
        this.setExtractUrl(extractTaskDto.getExtractUrl());
        this.setExtractExpression(extractTaskDto.getExtractExpression());
        this.setExtractOption(extractTaskDto.getExtractOption());
        this.setTaskType(extractTaskDto.getTaskType());
        this.setTaskInterval(extractTaskDto.getTaskInterval());
        this.setActiveFlag(extractTaskDto.getActiveFlag());

        // create a new one
        if (extractTaskDto.getId() == null) {
            this.setProcessStatus("C");
            LocalDateTime currentDateTime = LocalDateTime.now();
            this.setCreateDateTime(currentDateTime);
            this.setUpdateDateTime(currentDateTime);
            this.setNextRunDateTime(currentDateTime.plusSeconds(10));
        } else {
            // update the existing one
            LocalDateTime currentDateTime = LocalDateTime.now();
            this.setUpdateDateTime(currentDateTime);
        }
    }
}
