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
    public ResponseEntity<String> saveResponse(@PathVariable("userId") Integer userId, @RequestBody Integer[] responseArray) {
        try {
            responseService.saveResponse(userId, responseArray);
            return new ResponseEntity<>("Response saved successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to save response: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/responses/{userId}")
    public ResponseEntity<Integer[]> getResponse(@PathVariable("userId") Integer userId) {
        try {
            Integer[] response = responseService.getResponseByUserId(userId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

