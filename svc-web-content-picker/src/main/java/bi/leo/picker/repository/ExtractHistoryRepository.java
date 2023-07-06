package bi.leo.picker.repository;

import bi.leo.picker.entity.ExtractTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtractHistoryRepository extends JpaRepository<ExtractTask, Long> {


}
