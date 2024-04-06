package com.energygame.mcqapplication.Controller;
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

    // End point to save a new user in the database
    @PostMapping
    public Map<String, Object> saveUser(@RequestBody String userName) {
        return userService.saveUser(userName);
    }


    // End point to find user by userId
    @GetMapping("/id/{user_id}")
    public ResponseEntity<?> getUserById(@PathVariable int user_id, @RequestHeader("Authorization") String token) {
        String userName = this.userService.getUserById(user_id).getUserName();
        ResponseEntity<?> responseEntity = jwtTokenProvider.validateToken(token,userName);
        if (responseEntity != null) return responseEntity;

        User user = userService.getUserById(user_id);
        return ResponseEntity.ok(user);
    }

    // End point to get questionnaire score of a user
    @GetMapping("/score/{user_id}")
    public ResponseEntity<?> getQuestionnaireScore(@PathVariable int user_id, @RequestHeader("Authorization") String token) {
        String userName = this.userService.getUserById(user_id).getUserName();
        ResponseEntity<?> responseEntity = jwtTokenProvider.validateToken(token,userName);
        if (responseEntity != null) return responseEntity;

        Integer score = userService.getQuestionnaireScore(user_id);
        return ResponseEntity.ok(score);
    }

    // End point to update profile updating status of a user
    @PostMapping("profile/{user_id}")
    public ResponseEntity<?> updateProfileStatus(@PathVariable int user_id, @RequestHeader("Authorization") String token) {
        String userName = this.userService.getUserById(user_id).getUserName();
        ResponseEntity<?> responseEntity = jwtTokenProvider.validateToken(token,userName);
        if (responseEntity != null) return responseEntity;

        return ResponseEntity.ok(userService.updateProfileStatus(user_id));
    }

    // End point to update questionnaire status of a user
    @PostMapping("questionnaire/{user_id}")
    public ResponseEntity<?> updateQuestionnaireStatus(@PathVariable int user_id, @RequestHeader("Authorization") String token) {
        String userName = this.userService.getUserById(user_id).getUserName();
        ResponseEntity<?> responseEntity = jwtTokenProvider.validateToken(token,userName);
        if (responseEntity != null) return responseEntity;

        return ResponseEntity.ok(userService.updateQuestionnaireStatus(user_id));
    }

    // End point to update questionnaire score of a user
    @PostMapping("score/{user_id}/{score}")
    public ResponseEntity<?> updateQuestionnaireScore(@PathVariable int user_id, @PathVariable int score, @RequestHeader("Authorization") String token) {
        String userName = this.userService.getUserById(user_id).getUserName();
        ResponseEntity<?> responseEntity = jwtTokenProvider.validateToken(token,userName);
        if (responseEntity != null) return responseEntity;

        return ResponseEntity.ok(userService.updateQuestionnaireScore(user_id, score));
    }

    @PostMapping("level/{user_id}/{gameLevel}")
    public ResponseEntity<?> updateGameLevel(@PathVariable int user_id, @PathVariable int gameLevel, @RequestHeader("Authorization") String token) {
        String userName = this.userService.getUserById(user_id).getUserName();
        ResponseEntity<?> responseEntity = jwtTokenProvider.validateToken(token,userName);
        if (responseEntity != null) return responseEntity;

        return ResponseEntity.ok(userService.updateGameLevel(user_id, gameLevel));
    }

    @PostMapping("game/{user_id}/{gameCoin}")
    public ResponseEntity<?> updateGameCoin(@PathVariable int user_id, @PathVariable int gameCoin, @RequestHeader("Authorization") String token) {
        String userName = this.userService.getUserById(user_id).getUserName();
        ResponseEntity<?> responseEntity = jwtTokenProvider.validateToken(token,userName);
        if (responseEntity != null) return responseEntity;

        return ResponseEntity.ok(userService.updateGameCoin(user_id, gameCoin));
    }

    @PostMapping("energy/{user_id}/{energyCoin}")
    public ResponseEntity<?> updateEnergyCoin(@PathVariable int user_id, @PathVariable int energyCoin, @RequestHeader("Authorization") String token) {
        String userName = this.userService.getUserById(user_id).getUserName();
        ResponseEntity<?> responseEntity = jwtTokenProvider.validateToken(token,userName);
        if (responseEntity != null) return responseEntity;

        return ResponseEntity.ok(userService.updateEnergyCoin(user_id, energyCoin));
    }

    @GetMapping("/level/{user_id}")
    public ResponseEntity<?> getGameLevel(@PathVariable int user_id, @RequestHeader("Authorization") String token) {
        String userName = this.userService.getUserById(user_id).getUserName();
        ResponseEntity<?> responseEntity = jwtTokenProvider.validateToken(token,userName);
        if (responseEntity != null) return responseEntity;

        Integer level = userService.getGameLevel(user_id);
        return ResponseEntity.ok(level);
    }

    @GetMapping("/game/{user_id}")
    public ResponseEntity<?> getGameCoin(@PathVariable int user_id, @RequestHeader("Authorization") String token) {
        String userName = this.userService.getUserById(user_id).getUserName();
        ResponseEntity<?> responseEntity = jwtTokenProvider.validateToken(token,userName);
        if (responseEntity != null) return responseEntity;

        Integer gameCoin = userService.getGameCoin(user_id);
        return ResponseEntity.ok(gameCoin);
    }

    @GetMapping("/energy/{user_id}")
    public ResponseEntity<?> getEnergyCoin(@PathVariable int user_id, @RequestHeader("Authorization") String token) {
        String userName = this.userService.getUserById(user_id).getUserName();
        ResponseEntity<?> responseEntity = jwtTokenProvider.validateToken(token,userName);
        if (responseEntity != null) return responseEntity;

        Integer energyCoin = userService.getEnergyCoin(user_id);
        return ResponseEntity.ok(energyCoin);
    }
}


