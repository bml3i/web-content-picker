package bi.leo.picker.service;

import bi.leo.picker.entity.ExtractTask;
import bi.leo.picker.repository.ExtractTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtractTaskServiceImpl implements ExtractTaskService{

    @Autowired
    private ExtractTaskRepository extractTaskRepository;

    @Override
    public List<ExtractTask> getExtractTasks() {
        return extractTaskRepository.findAll();
    }

    @Override
    public List<ExtractTask> getReadyExtractTasksByType(String type) {
        return extractTaskRepository.getReadyExtractTasksByType(type);
    }
}
