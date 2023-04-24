package bi.leo.picker.controller;

import bi.leo.picker.exception.CustomWebDriverException;
import bi.leo.picker.model.ExtractRequest;
import bi.leo.picker.model.ExtractResult;
import bi.leo.picker.model.ResponseResult;
import bi.leo.picker.service.ExtractService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ExtractController {

    @Autowired
    ExtractService extractService;

    @Autowired
    ObjectMapper objectMapper;

    @CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000"})
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000"})
    @GetMapping(path = "/user")
    @ResponseBody
    public ObjectNode getUserInfo() {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("name", "Frank");
        objectNode.put("age", 24);
        return objectNode;
    }

    @CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000"})
    @RequestMapping(value = "/extract", method=RequestMethod.POST)
    public ResponseEntity extract(@RequestBody ExtractRequest extractRequest) throws CustomWebDriverException {

        ExtractResult extractResult = extractService.extractFieldValue(extractRequest);

        ResponseResult responseResult = new ResponseResult().buildSuccessMessage(extractResult);

        return new ResponseEntity(responseResult, HttpStatus.OK);
    }
}