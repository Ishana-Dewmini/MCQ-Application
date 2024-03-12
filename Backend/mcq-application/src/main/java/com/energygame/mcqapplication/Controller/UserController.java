package com.energygame.mcqapplication.Controller;


import com.energygame.mcqapplication.Model.User;
import com.energygame.mcqapplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/energy-quest/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/token/{jwtToken}")
    public User getUserByToken(@PathVariable String jwtToken) {
        return userService.getUserByToken(jwtToken);
    }

    @GetMapping("/id/{user_id}")
    public User getUserById(@PathVariable long user_id) {
        return userService.getUserById(user_id);
    }
    @PostMapping("profile/{user_id}")
    public User updateProfileStatus(@PathVariable int user_id) {
        return userService.updateProfileStatus(user_id);
    }

    @PostMapping("questionnaire/{user_id}")
    public User updateQuestionnaireStatus(@PathVariable int user_id) {
        return userService.updateQuestionnaireStatus(user_id);
    }
}


