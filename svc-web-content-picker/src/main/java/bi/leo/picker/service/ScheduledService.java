package bi.leo.picker.service;

import bi.leo.picker.entity.ExtractTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class ScheduledService {

    @Autowired
    ExtractTaskService extractTaskService;

    @Autowired
    BatchService batchService;

    public static final int THREAD_COUNT_MINUTELY = 8;

    public static final int THREAD_COUNT_HOURLY = 2;

    public static final int THREAD_COUNT_DAILY = 2;

    public static final String TASK_TYPE_MINUTELY = "MINUTELY";

    public static final String TASK_TYPE_HOURLY = "HOURLY";

    public static final String TASK_TYPE_DAILY = "DAILY";


    @Scheduled(fixedDelay = 15000)
    public void ExtractTaskHandler01() {

        String taskType = TASK_TYPE_MINUTELY;

        System.out.println("ScheduledService.ExtractTaskHandler01 started - " + System.currentTimeMillis() / 1000);

        List<ExtractTask> readyExtractTasks = extractTaskService.getReadyExtractTasksByType(taskType);

        System.out.println("ScheduledService.ExtractTaskHandler01 - readyExtractTasks.size() = " + readyExtractTasks.size());

        Executor executor = Executors.newFixedThreadPool(THREAD_COUNT_MINUTELY);

        CompletableFuture[] futureList = readyExtractTasks.stream().map(item -> CompletableFuture.supplyAsync(
                        () -> {
                            return batchService.handleExtractTask(item);
                        }, executor).whenComplete((s, e) -> {
                })
        ).toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futureList).join();

        System.out.println("ScheduledService.ExtractTaskHandler01 finished - " + System.currentTimeMillis() / 1000);
    }

    @Scheduled(fixedDelay = 30000)
    public void ExtractTaskHandler02() {

        String taskType = TASK_TYPE_HOURLY;

        System.out.println("ScheduledService.ExtractTaskHandler02 started - " + System.currentTimeMillis() / 1000);

        List<ExtractTask> readyExtractTasks = extractTaskService.getReadyExtractTasksByType(taskType);

        System.out.println("ScheduledService.ExtractTaskHandler02 - readyExtractTasks.size() = " + readyExtractTasks.size());

        Executor executor = Executors.newFixedThreadPool(THREAD_COUNT_HOURLY);

        CompletableFuture[] futureList = readyExtractTasks.stream().map(item -> CompletableFuture.supplyAsync(
                        () -> {
                            return batchService.handleExtractTask(item);
                        }, executor).whenComplete((s, e) -> {
                })
        ).toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futureList).join();

        System.out.println("ScheduledService.ExtractTaskHandler02 finished - " + System.currentTimeMillis() / 1000);
    }

    @Scheduled(fixedDelay = 60000)
    public void ExtractTaskHandler03() {

        String taskType = TASK_TYPE_DAILY;

        System.out.println("ScheduledService.ExtractTaskHandler03 started - " + System.currentTimeMillis() / 1000);

        List<ExtractTask> readyExtractTasks = extractTaskService.getReadyExtractTasksByType(taskType);

        System.out.println("ScheduledService.ExtractTaskHandler03 - readyExtractTasks.size() = " + readyExtractTasks.size());

        Executor executor = Executors.newFixedThreadPool(THREAD_COUNT_DAILY);

        CompletableFuture[] futureList = readyExtractTasks.stream().map(item -> CompletableFuture.supplyAsync(
                        () -> {
                            return batchService.handleExtractTask(item);
                        }, executor).whenComplete((s, e) -> {
                })
        ).toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futureList).join();

        System.out.println("ScheduledService.ExtractTaskHandler03 finished - " + System.currentTimeMillis() / 1000);
    }

    @Scheduled(fixedDelay = 60000 * 30)
    public void ResetExtractTaskStatusJob() {

        System.out.println("ScheduledService.ResetExtractTaskStatusJob started - " + System.currentTimeMillis() / 1000);

        List<ExtractTask> obsoleteExtractTasks = extractTaskService.getObsoleteExtractTasks();

        System.out.println("ScheduledService.ResetExtractTaskStatusJob - obsoleteExtractTasks.size() = " + obsoleteExtractTasks.size());

        obsoleteExtractTasks.forEach(item -> {
            // Reset Extract Task status
            item.setProcessStatus("F");
            item.setUpdateDateTime(LocalDateTime.now());
            extractTaskService.save(item);
        });

        System.out.println("ScheduledService.ResetExtractTaskStatusJob finished - " + System.currentTimeMillis() / 1000);
    }
}
