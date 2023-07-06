package bi.leo.picker.service;

import bi.leo.picker.entity.ExtractTask;
import bi.leo.picker.repository.ExtractTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class ExtractTaskServiceImpl implements ExtractTaskService{

    @Autowired
    private ExtractTaskRepository extractTaskRepository;


    @Override
    public ExtractTask getExtractTaskById(Long id) {
        // TODO
        return extractTaskRepository.findById(id).get();
    }

    @Override
    public List<ExtractTask> getExtractTasks() {
        return extractTaskRepository.findAll();
    }

    @Override
    public List<ExtractTask> getReadyExtractTasksByType(String type) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<String> processStatus = Arrays.asList("C", "F");
        return extractTaskRepository.getReadyExtractTasksByType(type, currentDateTime, processStatus);
    }

    @Override
    public ExtractTask save(ExtractTask extractTask) {
        return extractTaskRepository.save(extractTask);
    }
}
