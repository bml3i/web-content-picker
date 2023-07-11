package bi.leo.picker.service;

import bi.leo.picker.entity.ExtractHistory;
import bi.leo.picker.entity.ExtractTask;
import bi.leo.picker.exception.CustomWebDriverException;
import bi.leo.picker.model.ExtractRequest;
import bi.leo.picker.model.ExtractResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BatchService {

    @Autowired
    ExtractService extractService;

    @Autowired
    ExtractTaskService extractTaskService;

    public Long handleExtractTask(ExtractTask extractTask) {

        // Mark the Extract Task status to In-Process first
        ExtractTask extractTaskDB = extractTaskService.getExtractTaskById(extractTask.getId());
        extractTaskDB.setProcessStatus("I");
        extractTaskDB.setUpdateDateTime(LocalDateTime.now().withNano(0));

        extractTaskDB = extractTaskService.save(extractTaskDB);

        ExtractRequest extractRequest = new ExtractRequest(extractTask);
        ExtractResult extractResult = null;

        try {
            extractResult = extractService.extractFieldValue(extractRequest);
        } catch (CustomWebDriverException e) {
            // handle this exception
            extractTaskDB.setProcessStatus("F");
            extractTaskDB.setUpdateDateTime(LocalDateTime.now().withNano(0));
        }

        Long extractTaskId = extractTaskDB.getId();
        String extractResultValue = extractResult.getValue();
        System.out.println("extractTaskId[" + extractTaskId + "]" + " - extractResultValue: " + extractResultValue);

        if (extractResultValue != null) {
            // save result to extract history
            List<ExtractHistory> extractHistoryList = extractTaskDB.getExtractHistories();
            extractHistoryList.add(new ExtractHistory(extractResultValue, LocalDateTime.now().withNano(0)));

            // reset next run date time
            extractTaskDB.resetNextRunDateTime(extractTaskDB.getTaskInterval());
            extractTaskDB.setUpdateDateTime(LocalDateTime.now().withNano(0));
            extractTaskDB.setProcessStatus("C");
        }

        extractTaskService.save(extractTaskDB);

       return extractTaskDB.getId();
    }

}
