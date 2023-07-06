package bi.leo.picker.service;

import bi.leo.picker.entity.ExtractHistory;
import bi.leo.picker.entity.ExtractTask;

import java.util.List;

public interface ExtractTaskService {

    public ExtractTask getExtractTaskById(Long id);

    public List<ExtractTask> getExtractTasks();

    public List<ExtractTask> getReadyExtractTasksByType(String type);

    public ExtractTask save(ExtractTask extractTask);

    public ExtractHistory getRecentExtractHistoryByUuid(String uuid);

}
