package bi.leo.picker.entity;

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

    private LocalDateTime nextRunDateTime;

    // HOURLY, DAILY, MINUTELY
    private String taskType;

    // unit: second
    private Integer taskInterval;

    private Boolean activeFlag;

    private LocalDateTime createDateTime;

    private LocalDateTime updateDateTime;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "task_history_map")
    private List<ExtractHistory> extractHistories = new ArrayList<>();

}
