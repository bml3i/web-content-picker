package bi.leo.picker.service;

import bi.leo.picker.entity.ExtractTask;

import java.util.List;

public interface ExtractTaskService {

    public List<ExtractTask> getExtractTasks();

    public List<ExtractTask> getReadyExtractTasksByType(String type);

}
