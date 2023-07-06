package bi.leo.picker.service;

import bi.leo.picker.entity.ExtractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduledService {

    @Autowired
    ExtractTaskService extractTaskService;

    @Scheduled(fixedDelay = 5000)
    public void ExtractTaskHandler01() {

        String taskType = "MINUTELY";

        System.out.println("ExtractTaskHandler01 started - " + System.currentTimeMillis() / 1000);

        List<ExtractTask> readyExtractTasks = extractTaskService.getReadyExtractTasksByType(taskType);



        System.out.println(readyExtractTasks);


    }
}
