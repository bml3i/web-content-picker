package bi.leo.picker.repository;

import bi.leo.picker.entity.ExtractTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ExtractTaskRepository extends JpaRepository<ExtractTask, Long> {

    @Query("SELECT et FROM ExtractTask et " + "WHERE et.taskType = :type " +
            "AND et.nextRunDateTime <= :currentDateTime AND et.processStatus IN :processStatus " +
            "AND et.activeFlag = TRUE")
    List<ExtractTask> getReadyExtractTasksByType(String type, LocalDateTime currentDateTime, List<String> processStatus);

}
