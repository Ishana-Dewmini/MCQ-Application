package com.energygame.mcqapplication.Controller;

import com.energygame.mcqapplication.Dto.ResponseDto;
import com.energygame.mcqapplication.Dto.ResponseWithCorrectAnswerDto;
import com.energygame.mcqapplication.Model.Response;
import com.energygame.mcqapplication.Service.ResponseService;
import com.energygame.mcqapplication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/energy-quest/responses")
public class ResponseController {

    private final ResponseService responseService;
    private final UserService userService;

    @Autowired
    public ResponseController(ResponseService responseService, UserService userService) {
        this.responseService = responseService;
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveResponse(@RequestBody ResponseDto responseDto) {
        try {
            responseService.saveResponse(responseDto);
            return new ResponseEntity<>("Response saved successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error saving response: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<List<ResponseWithCorrectAnswerDto>> getAllResponsesForUser(@PathVariable Long userId) {
        try {
            List<ResponseWithCorrectAnswerDto> responses = responseService.getAllResponsesForUser(userId);
            return new ResponseEntity<>(responses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/mcqScore/{user_id}")
    public ResponseEntity<Integer> calculateScore(@PathVariable("user_id") Long userId) {
        // Logic to check if questionnaire_score is already calculated for the user
        // If it's already calculated, return it to the frontend
        Integer score = userService.getQuestionnaireScore(userId);
        if (score != null) {
            return ResponseEntity.ok(score);
        }

        // If questionnaire_score is not calculated and questionnaire_taken is true
        // Calculate the score, store it, and then return it to the frontend
        boolean questionnaireTaken = userService.isUserQuestionnaireTaken(userId);
        if (questionnaireTaken) {
            score = userService.calculateAndStoreQuestionnaireScore(userId);
            return ResponseEntity.ok(score);
        }

        // If questionnaire_taken is false or null, return an error response
        return ResponseEntity.badRequest().build();
    }


}
