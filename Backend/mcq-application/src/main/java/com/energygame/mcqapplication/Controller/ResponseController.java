package com.energygame.mcqapplication.Controller;

import com.energygame.mcqapplication.Dto.ResponseDto;
import com.energygame.mcqapplication.Service.ResponseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/energy-quest/responses")
@AllArgsConstructor
public class ResponseController {

    private ResponseService responseService;

    // add a response to database
    @PostMapping("/save")
    public ResponseEntity<?> saveResponse(@RequestBody ResponseDto responseDto) {
        try {
            ResponseDto savedResponse = responseService.saveAnswers(responseDto);
            return ResponseEntity.ok(savedResponse);
        } catch (Exception e) {
            String errorMessage = "Error saving response: " + e.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    // get all response of a selected user
//    @GetMapping("/all/{userId}")
//    public ResponseEntity<List<ResponseWithCorrectAnswerDto>> getAllResponsesForUser(@PathVariable Long userId) {
//        try {
//            List<ResponseWithCorrectAnswerDto> responses = responseService.getAllResponsesForUser(userId);
//            return new ResponseEntity<>(responses, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//
//    // get the mcq score of a selected user
//    @GetMapping("/mcqScore/{user_id}")
//    public ResponseEntity<Integer> calculateScore(@PathVariable("user_id") Long userId) {
//        // Logic to check if questionnaire_score is already calculated for the user
//        // If it's already calculated, return it to the frontend
//        Integer score = userService.getQuestionnaireScore(userId);
//        if (score != null) {
//            return ResponseEntity.ok(score);
//        }
//
//        // If questionnaire_score is not calculated and questionnaire_taken is true
//        // Calculate the score, store it, and then return it to the frontend
//        boolean questionnaireTaken = userService.isUserQuestionnaireTaken(userId);
//        if (questionnaireTaken) {
//            score = userService.calculateAndStoreQuestionnaireScore(userId);
//            return ResponseEntity.ok(score);
//        }
//
//        // If questionnaire_taken is false or null, return an error response
//        return ResponseEntity.badRequest().build();
//    }


}
