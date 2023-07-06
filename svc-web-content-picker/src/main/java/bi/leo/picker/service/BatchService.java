package bi.leo.picker.service;

import bi.leo.picker.entity.ExtractTask;
import bi.leo.picker.exception.CustomWebDriverException;
import bi.leo.picker.model.ExtractRequest;
import bi.leo.picker.model.ExtractResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class BatchService {

    @Autowired
    ExtractService extractService;

    @Autowired
    ExtractTaskService extractTaskService;

    @Async
    public void handleExtractTask(ExtractTask extractTask) {

        // Mark the Extract Task status to In-Process first
        ExtractTask extractTaskDB = extractTaskService.getExtractTaskById(extractTask.getId());
        extractTaskDB.setProcessStatus("I");

        extractTaskDB = extractTaskService.save(extractTaskDB);

        ExtractRequest extractRequest = new ExtractRequest(extractTask);
        ExtractResult extractResult = null;

        try {
            extractResult = extractService.extractFieldValue(extractRequest);
        } catch (CustomWebDriverException e) {
            // handle this exception
            extractTaskDB.setProcessStatus("F");
        }

        String extractResultValue = extractResult.getValue();
        System.out.println("extractResultValue: " + extractResultValue);

        if (extractResultValue != null) {
            // save result to extract history

            // reset next run date time
            extractTaskDB.resetNextRunDateTime(extractTaskDB.getTaskInterval());
            extractTaskDB.setProcessStatus("C");
        }

        extractTaskService.save(extractTaskDB);
    }

}
