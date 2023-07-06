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

    @Column(name = "ext_url")
    private String extractUrl;

    @Column(name = "ext_expr")
    private String extractExpression;

    @Column(name = "ext_opt")
    private Integer extractOption;

    // HOURLY, DAILY, MINUTELY
    @Column(name = "tsk_typ")
    private String taskType;

    // unit: second
    @Column(name = "tsk_inr")
    private Integer taskInterval;

    @Column(name = "next_run_dttm")
    private LocalDateTime nextRunDateTime;

    @Column(name = "active_flg")
    private Boolean activeFlag;

    // C = Completed, I = In-Process, F = Failure
    @Column(name = "proc_stat")
    private String processStatus;

    @Column(name = "create_dttm")
    private LocalDateTime createDateTime;

    @Column(name = "upd_dttm")
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

    public void resetNextRunDateTime(int bufferInSecond) {
        if (bufferInSecond <= 0) {
            return;
        }

        LocalDateTime currentDateTime = LocalDateTime.now();
        while (this.nextRunDateTime.isBefore(currentDateTime)) {
            this.nextRunDateTime = this.nextRunDateTime.plusSeconds(bufferInSecond);
        }
    }
}
