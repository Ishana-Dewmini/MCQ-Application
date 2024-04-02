package com.energygame.mcqapplication.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.energygame.mcqapplication.Config.JwtTokenProvider;
import com.energygame.mcqapplication.Model.User;
import com.energygame.mcqapplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
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
    public ResponseEntity<?> getUserById(@PathVariable int user_id, @RequestHeader("Authorization") String token) {
        String userName = this.userService.getUserById(user_id).getUserName();
        Integer validationId = jwtTokenProvider.validateToken(token,userName);
        if (validationId.equals(401)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        } else if (validationId.equals(500)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
        User user = userService.getUserById(user_id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/score/{user_id}")
    public ResponseEntity<?> getQuestionnaireScore(@PathVariable long user_id, @RequestHeader("Authorization") String token) {
        String userName = this.userService.getUserById(user_id).getUserName();
        Integer validationId = jwtTokenProvider.validateToken(token,userName);
        if (validationId.equals(401)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        } else if (validationId.equals(500)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
        Integer score = userService.getQuestionnaireScore(user_id);
        return ResponseEntity.ok(score);
    }

    @PostMapping("profile/{user_id}")
    public ResponseEntity<?> updateProfileStatus(@PathVariable int user_id, @RequestHeader("Authorization") String token) {
        String userName = this.userService.getUserById(user_id).getUserName();
        Integer validationId = jwtTokenProvider.validateToken(token,userName);
        if (validationId.equals(401)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        } else if (validationId.equals(500)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
        return ResponseEntity.ok(userService.updateProfileStatus(user_id));
    }

    @PostMapping("questionnaire/{user_id}")
    public ResponseEntity<?> updateQuestionnaireStatus(@PathVariable int user_id, @RequestHeader("Authorization") String token) {
        String userName = this.userService.getUserById(user_id).getUserName();
        Integer validationId = jwtTokenProvider.validateToken(token,userName);
        if (validationId.equals(401)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        } else if (validationId.equals(500)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
        return ResponseEntity.ok(userService.updateQuestionnaireStatus(user_id));
    }

    @PostMapping("score/{user_id}/{score}")
    public ResponseEntity<?> updateQuestionnaireScore(@PathVariable int user_id, @PathVariable int score, @RequestHeader("Authorization") String token) {
        String userName = this.userService.getUserById(user_id).getUserName();
        Integer validationId = jwtTokenProvider.validateToken(token,userName);
        if (validationId.equals(401)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        } else if (validationId.equals(500)) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
        return ResponseEntity.ok(userService.updateQuestionnaireScore(user_id, score));
    }
}


