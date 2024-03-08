package com.energygame.mcqapplication.Controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

    @PostMapping("/receiveUserId")
    public ResponseEntity<String> receiveUserId(@RequestBody Map<String, Object> payload) {
        try {
            // Extract user_id from the payload
            int userId = (int) payload.get("user_id");
            return ResponseEntity.ok("User_id received successfully");

            // Now you can use the userId as needed (e.g., save it to the database, perform actions, etc.)

            // Return a success response
            
        } catch (Exception e) {
            // Handle any exceptions or errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing user_id");
        }
    }
}


