package bi.leo.picker.controller;

import bi.leo.picker.dto.ExtractTaskDto;
import bi.leo.picker.entity.ExtractTask;
import bi.leo.picker.entity.User;
import bi.leo.picker.model.ResponseResult;
import bi.leo.picker.service.ExtractTaskService;
import bi.leo.picker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000"})
@RestController
public class ExtractTaskController {

    @Autowired
    ExtractTaskService extractTaskService;

    @Autowired
    UserService userService;

    @GetMapping(value = "/api/extractTasks")
    public ResponseEntity getExtractTasks() {
        ResponseResult responseResult = new ResponseResult().buildSuccessMessage(extractTaskService.getExtractTasks());

        return new ResponseEntity(responseResult, HttpStatus.OK);
    }

    @GetMapping(value = "/api/readyExtractTasks/{taskType}")
    public ResponseEntity getExtractTasks(@PathVariable String taskType) {
        ResponseResult responseResult = new ResponseResult().buildSuccessMessage(extractTaskService.getReadyExtractTasksByType(taskType));

        return new ResponseEntity(responseResult, HttpStatus.OK);
    }

    @GetMapping(value = "/api/extractTask/{uuid}/recent")
    public ResponseEntity getRecentExtractHistoryByUuid(@PathVariable String uuid) {
        ResponseResult responseResult = new ResponseResult().buildSuccessMessage(extractTaskService.getRecentExtractHistoryByUuid(uuid));

        return new ResponseEntity(responseResult, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/user/{userId}/extractTasks", method = RequestMethod.POST)
    public ResponseEntity saveExtractTask(@RequestBody ExtractTaskDto extractTaskDto, @PathVariable String userId) {

        ResponseResult responseResult = null;

        Optional<User> optionalUser = userService.getUserById(Long.parseLong(userId));

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // ID is supposed to be NULL / 0L?
            if(extractTaskDto.getId() == null) {

                ExtractTask extractTask = new ExtractTask(extractTaskDto);

                user.getExtractTasks().add(extractTask);

                User savedUser = userService.save(user);
                ExtractTask newExtractTask = savedUser.getExtractTasks().get(savedUser.getExtractTasks().size() - 1);

                responseResult = new ResponseResult().buildSuccessMessage(newExtractTask);
            } else {
                // should not be there
                System.out.println("ExtractTaskController.saveExtractTask() - extractTaskDto.getId() is not null");
            }

        } else {
            // should not be there
            System.out.println("ExtractTaskController.saveExtractTask() - user does not exist");

        }

        return new ResponseEntity(responseResult, HttpStatus.OK);
    }

}
