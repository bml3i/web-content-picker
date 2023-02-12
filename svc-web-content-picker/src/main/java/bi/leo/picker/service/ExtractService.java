package bi.leo.picker.service;

import bi.leo.picker.exception.WebDriverPoolException;
import bi.leo.picker.model.ExtractRequest;
import bi.leo.picker.model.ExtractResult;

public interface ExtractService {
    ExtractResult extractFieldValue(ExtractRequest extractRequest) throws WebDriverPoolException;
}
