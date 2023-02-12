package bi.leo.picker.controller;

import bi.leo.picker.exception.WebDriverPoolException;
import bi.leo.picker.model.ExtractRequest;
import bi.leo.picker.model.ExtractResult;
import bi.leo.picker.model.ResponseResult;
import bi.leo.picker.service.ExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExtractController {

    @Autowired
    ExtractService extractService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/extract", method=RequestMethod.POST)
    public ResponseEntity extract(@RequestBody ExtractRequest extractRequest) throws WebDriverPoolException {

        ExtractResult extractResult = extractService.extractFieldValue(extractRequest);

        ResponseResult responseResult = new ResponseResult().buildSuccessMessage(extractResult);

        return new ResponseEntity(responseResult, HttpStatus.OK);
    }
}