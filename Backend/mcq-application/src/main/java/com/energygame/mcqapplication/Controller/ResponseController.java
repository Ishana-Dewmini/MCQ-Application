package com.energygame.mcqapplication.Controller;
import com.energygame.mcqapplication.Config.JwtTokenProvider;
import com.energygame.mcqapplication.Service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.energygame.mcqapplication.Service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/energy-quest")
public class ResponseController {

    @Autowired
    private ResponseService responseService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    // End point to save the responses for questionnaire of a user
    @PostMapping("/responses/{userId}")
    public ResponseEntity<?> saveResponse(@PathVariable("userId") Integer userId, @RequestBody Integer[] responseArray, @RequestHeader("Authorization") String token) {
        try {
            if (this.userService.getUserById(userId)==null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            String userName = this.userService.getUserById(userId).getUserName();
            ResponseEntity<?> responseEntity = jwtTokenProvider.validateToken(token,userName);
            if (responseEntity != null) return responseEntity;

            responseService.saveResponse(userId, responseArray);
            return new ResponseEntity<>("Response saved successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to save response: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // End point to get the responses for questionnaire of a user
    @GetMapping("/responses/{userId}")
    public ResponseEntity<?> getResponse(@PathVariable("userId") Integer userId, @RequestHeader("Authorization") String token) {
        try {
            if (this.userService.getUserById(userId)==null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            String userName = this.userService.getUserById(userId).getUserName();
            ResponseEntity<?> responseEntity = jwtTokenProvider.validateToken(token,userName);
            if (responseEntity != null) return responseEntity;

            Integer[] response = responseService.getResponseByUserId(userId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

