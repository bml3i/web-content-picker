package bi.leo.picker.controller;

import bi.leo.picker.entity.User;
import bi.leo.picker.model.ResponseResult;
import bi.leo.picker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000"})
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity getUsers() {
        ResponseResult responseResult = new ResponseResult().buildSuccessMessage(userService.getUsers());

        return new ResponseEntity(responseResult, HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity saveUser(@RequestBody User user) {
        User newUser = userService.save(user);
        ResponseResult responseResult = new ResponseResult().buildSuccessMessage(newUser);

        return new ResponseEntity(responseResult, HttpStatus.OK);
    }

}
