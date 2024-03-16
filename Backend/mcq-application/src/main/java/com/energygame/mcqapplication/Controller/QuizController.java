package com.energygame.mcqapplication.Controller;

import com.energygame.mcqapplication.Dto.QuizDto;
import com.energygame.mcqapplication.Model.Quiz;
import com.energygame.mcqapplication.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/energy-quest/quizzes")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    // get all questions from the database
    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }


    // add a question to database
    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody QuizDto quizDto) {
        // Assume QuizDto is a DTO (Data Transfer Object) representing the question details
        // You can create a QuizDto class with fields like question, correctAnswer, otherOptions, etc.

        // Perform any necessary validation or business logic

        // Call the service to save the question
        quizService.saveQuestion(quizDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Question added successfully");
    }
}
