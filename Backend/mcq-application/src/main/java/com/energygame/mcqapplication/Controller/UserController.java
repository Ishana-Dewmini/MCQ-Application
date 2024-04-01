package com.energygame.mcqapplication.Controller;
import io.swagger.v3.core.util.Json;
import org.springframework.security.access.AccessDeniedException;
import com.energygame.mcqapplication.Config.JwtTokenProvider;
import com.energygame.mcqapplication.Model.User;
import com.energygame.mcqapplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/energy-quest/user")
public class UserController {
    private final UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public Map<String, Object> saveUser(@RequestBody String userName) {
        return userService.saveUser(userName);
    }



    @GetMapping("/id/{user_id}")
    public User getUserById(@PathVariable long user_id, @RequestHeader("Authorization") String token) {

        if (!jwtTokenProvider.validateToken(token)) {

            throw new AccessDeniedException("Invalid token..!");
        }
        return userService.getUserById(user_id);
    }

    @GetMapping("/score/{user_id}")
    public Integer getQuestionnaireScore(@PathVariable long user_id, @RequestHeader("Authorization") String token) {
        if (!jwtTokenProvider.validateToken(token)) {

            throw new AccessDeniedException("Invalid token..!");
        }
        return userService.getQuestionnaireScore(user_id);
    }

    @PostMapping("profile/{user_id}")
    public User updateProfileStatus(@PathVariable int user_id, @RequestHeader("Authorization") String token) {
        if (!jwtTokenProvider.validateToken(token)) {
            // Handle invalid token (e.g., return unauthorized response)
            throw new AccessDeniedException("Invalid token..!");
        }
        return userService.updateProfileStatus(user_id);
    }

    @PostMapping("questionnaire/{user_id}")
    public User updateQuestionnaireStatus(@PathVariable int user_id, @RequestHeader("Authorization") String token) {
        if (!jwtTokenProvider.validateToken(token)) {

            throw new AccessDeniedException("Invalid token..!");
        }
        return userService.updateQuestionnaireStatus(user_id);
    }

    @PostMapping("score/{user_id}/{score}")
    public User updateQuestionnaireScore(@PathVariable int user_id, @PathVariable int score, @RequestHeader("Authorization") String token) {
        if (!jwtTokenProvider.validateToken(token)) {

            throw new AccessDeniedException("Invalid token..!");
        }
        return userService.updateQuestionnaireScore(user_id, score);
    }
}


