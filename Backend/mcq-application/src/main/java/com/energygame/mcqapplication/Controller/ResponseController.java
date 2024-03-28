package com.energygame.mcqapplication.Controller;

import com.energygame.mcqapplication.Service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/energy-quest")
public class ResponseController {

    @Autowired
    private ResponseService responseService;

    @PostMapping("/responses/{userId}")
    public ResponseEntity<String> saveResponse(@PathVariable("userId") Integer userId, @RequestBody String responseJson) {
        try {
            responseService.saveResponse(userId, responseJson);
            return new ResponseEntity<>("Response saved successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to save response: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/responses/{userId}")
    public ResponseEntity<String> getResponse(@PathVariable("userId") Integer userId) {
        try {
            String response = responseService.getResponseByUserId(userId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve response: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
