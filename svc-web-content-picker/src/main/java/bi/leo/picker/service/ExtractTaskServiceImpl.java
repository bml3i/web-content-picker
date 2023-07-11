package bi.leo.picker.service;

import bi.leo.picker.entity.ExtractHistory;
import bi.leo.picker.entity.ExtractTask;
import bi.leo.picker.repository.ExtractTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class ExtractTaskServiceImpl implements ExtractTaskService {

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
        LocalDateTime currentDateTime = LocalDateTime.now().withNano(0);
        List<String> processStatus = Arrays.asList("C", "F");
        return extractTaskRepository.getReadyExtractTasksByType(type, currentDateTime, processStatus);
    }

    @Override
    public ExtractTask save(ExtractTask extractTask) {
        return extractTaskRepository.save(extractTask);
    }

    @Override
    public ExtractHistory getRecentExtractHistoryByUuid(String uuid) {
        ExtractTask extractTask = extractTaskRepository.findByUuid(uuid);

        if (extractTask != null && extractTask.getExtractHistories() != null) {
            return extractTask.getExtractHistories().stream().reduce((first, second) -> {
                if (first.getCreateDateTime().isBefore(second.getCreateDateTime())) {
                    return second;
                } else {
                    return first;
                }
            }).orElse(null);
        } else {
            return null;
        }
    }

    @Override
    public List<ExtractTask> getObsoleteExtractTasks() {
        LocalDateTime currentDateTime = LocalDateTime.now().withNano(0).minusMinutes(30);
        List<String> processStatus = Arrays.asList("I");
        return extractTaskRepository.getObsoleteExtractTasks(currentDateTime, processStatus);
    }

}
