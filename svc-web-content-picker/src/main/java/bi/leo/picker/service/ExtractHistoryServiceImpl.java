package bi.leo.picker.service;

import bi.leo.picker.repository.ExtractHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExtractHistoryServiceImpl implements ExtractHistoryService{

    @Autowired
    ExtractHistoryRepository extractHistoryRepository;

}
