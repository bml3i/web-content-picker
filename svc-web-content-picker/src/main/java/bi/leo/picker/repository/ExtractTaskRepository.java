package bi.leo.picker.repository;

import bi.leo.picker.entity.ExtractTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExtractTaskRepository extends JpaRepository<ExtractTask, Long> {

    @Query("SELECT et FROM ExtractTask et WHERE et.taskType = :type")
    List<ExtractTask> getReadyExtractTasksByType(String type);

}
