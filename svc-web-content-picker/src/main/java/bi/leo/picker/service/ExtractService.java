package bi.leo.picker.service;

import bi.leo.picker.exception.CustomWebDriverException;
import bi.leo.picker.model.ExtractRequest;
import bi.leo.picker.model.ExtractResult;

public interface ExtractService {
    ExtractResult extractFieldValue(ExtractRequest extractRequest) throws CustomWebDriverException;
}
