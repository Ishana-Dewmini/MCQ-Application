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

    // End point to save a new user in the database
    @PostMapping
    public Map<String, Object> saveUser(@RequestBody String userName) {
        return userService.saveUser(userName);
    }


    // End point to find user by userId
    @GetMapping("/id/{user_id}")
    public ResponseEntity<?> getUserById(@PathVariable int user_id, @RequestHeader("Authorization") String token) {
        if (this.userService.getUserById(user_id)==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        String userName = this.userService.getUserById(user_id).getUserName();
        ResponseEntity<?> responseEntity = jwtTokenProvider.validateToken(token,userName);
        if (responseEntity != null)
            return responseEntity;
        User user = userService.getUserById(user_id);
        return ResponseEntity.ok(user);
    }

    // End point to get questionnaire score of a user
    @GetMapping("/score/{userId}")
    public ResponseEntity<?> getQuestionnaireScore(@PathVariable int userId, @RequestHeader("Authorization") String token) {
        if (this.userService.getUserById(userId)==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        String userName = this.userService.getUserById(userId).getUserName();
        ResponseEntity<?> responseEntity = jwtTokenProvider.validateToken(token,userName);
        if (responseEntity != null)
            return responseEntity;
        Integer score = userService.getQuestionnaireScore(userId);
        return ResponseEntity.ok(score);
    }

    // End point to update profile updating status of a user
    @PostMapping("profile/{userId}")
    public ResponseEntity<?> updateProfileStatus(@PathVariable int userId, @RequestHeader("Authorization") String token) {
        if (this.userService.getUserById(userId)==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        String userName = this.userService.getUserById(userId).getUserName();
        ResponseEntity<?> responseEntity = jwtTokenProvider.validateToken(token,userName);
        if (responseEntity != null)
            return responseEntity;
        return ResponseEntity.ok(userService.updateProfileStatus(userId));
    }

    // End point to update questionnaire status of a user
    @PostMapping("questionnaire/{userId}")
    public ResponseEntity<?> updateQuestionnaireStatus(@PathVariable int userId, @RequestHeader("Authorization") String token) {
        if (this.userService.getUserById(userId)==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        String userName = this.userService.getUserById(userId).getUserName();
        ResponseEntity<?> responseEntity = jwtTokenProvider.validateToken(token,userName);
        if (responseEntity != null) return responseEntity;

        return ResponseEntity.ok(userService.updateQuestionnaireStatus(userId));
    }

    // End point to update questionnaire score of a user
    @PostMapping("score/{userId}/{score}")
    public ResponseEntity<?> updateQuestionnaireScore(@PathVariable int userId, @PathVariable int score, @RequestHeader("Authorization") String token) {
        if (this.userService.getUserById(userId)==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        String userName = this.userService.getUserById(userId).getUserName();
        ResponseEntity<?> responseEntity = jwtTokenProvider.validateToken(token,userName);
        if (responseEntity != null)
            return responseEntity;
        return ResponseEntity.ok(userService.updateQuestionnaireScore(userId, score));
    }

    // End point to update questionnaire game level of a user
    @PostMapping("level/{userId}/{gameLevel}")
    public ResponseEntity<?> updateGameLevel(@PathVariable int userId, @PathVariable int gameLevel, @RequestHeader("Authorization") String token) {
        if (this.userService.getUserById(userId)==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        String userName = this.userService.getUserById(userId).getUserName();
        ResponseEntity<?> responseEntity = jwtTokenProvider.validateToken(token,userName);
        if (responseEntity != null) return responseEntity;

        return ResponseEntity.ok(userService.updateGameLevel(userId, gameLevel));
    }

    // End point to update game coin value of a user
    @PostMapping("game/{userId}/{gameCoin}")
    public ResponseEntity<?> updateGameCoin(@PathVariable int userId, @PathVariable int gameCoin, @RequestHeader("Authorization") String token) {
        if (this.userService.getUserById(userId)==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        String userName = this.userService.getUserById(userId).getUserName();
        ResponseEntity<?> responseEntity = jwtTokenProvider.validateToken(token,userName);
        if (responseEntity != null) return responseEntity;

        return ResponseEntity.ok(userService.updateGameCoin(userId, gameCoin));
    }

    // End point to update energy coin value of a user
    @PostMapping("energy/{userId}/{energyCoin}")
    public ResponseEntity<?> updateEnergyCoin(@PathVariable int userId, @PathVariable int energyCoin, @RequestHeader("Authorization") String token) {
        if (this.userService.getUserById(userId)==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        String userName = this.userService.getUserById(userId).getUserName();
        ResponseEntity<?> responseEntity = jwtTokenProvider.validateToken(token,userName);
        if (responseEntity != null) return responseEntity;

        return ResponseEntity.ok(userService.updateEnergyCoin(userId, energyCoin));
    }

    // End point to get questionnaire game level of a user
    @GetMapping("/level/{userId}")
    public ResponseEntity<?> getGameLevel(@PathVariable int userId, @RequestHeader("Authorization") String token) {
        if (this.userService.getUserById(userId)==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        String userName = this.userService.getUserById(userId).getUserName();
        ResponseEntity<?> responseEntity = jwtTokenProvider.validateToken(token,userName);
        if (responseEntity != null) return responseEntity;

        Integer level = userService.getGameLevel(userId);
        return ResponseEntity.ok(level);
    }

    // End point to get game coin value of a user
    @GetMapping("/game/{userId}")
    public ResponseEntity<?> getGameCoin(@PathVariable int userId, @RequestHeader("Authorization") String token) {
        if (this.userService.getUserById(userId)==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        String userName = this.userService.getUserById(userId).getUserName();
        ResponseEntity<?> responseEntity = jwtTokenProvider.validateToken(token,userName);
        if (responseEntity != null) return responseEntity;

        Integer gameCoin = userService.getGameCoin(userId);
        return ResponseEntity.ok(gameCoin);
    }

    // End point to get energy coin value of a user
    @GetMapping("/energy/{userId}")
    public ResponseEntity<?> getEnergyCoin(@PathVariable int userId, @RequestHeader("Authorization") String token) {
        if (this.userService.getUserById(userId)==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        String userName = this.userService.getUserById(userId).getUserName();
        ResponseEntity<?> responseEntity = jwtTokenProvider.validateToken(token,userName);
        if (responseEntity != null) return responseEntity;

        Integer energyCoin = userService.getEnergyCoin(userId);
        return ResponseEntity.ok(energyCoin);
    }
}


