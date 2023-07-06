package bi.leo.picker.service;

import bi.leo.picker.entity.ExtractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class ScheduledService {

    @Autowired
    ExtractTaskService extractTaskService;

    @Autowired
    BatchService batchService;

    @Scheduled(fixedDelay = 5000)
    public void ExtractTaskHandler01() {

        String taskType = "MINUTELY";

        System.out.println("ExtractTaskHandler01 started - " + System.currentTimeMillis() / 1000);

        List<ExtractTask> readyExtractTasks = extractTaskService.getReadyExtractTasksByType(taskType);

        System.out.println(readyExtractTasks);

        // readyExtractTasks.stream().forEach((item) -> batchService.handleExtractTask(item));

        CompletableFuture[] futureList = readyExtractTasks.stream().map(item -> CompletableFuture.supplyAsync(
                        () -> {
                            return batchService.handleExtractTask(item);
                        }).whenComplete((s, e) -> {
                })
        ).toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futureList).join();

        System.out.println("ExtractTaskHandler01 finished - " + System.currentTimeMillis() / 1000);
    }
}
