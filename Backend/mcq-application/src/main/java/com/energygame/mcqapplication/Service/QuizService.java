package com.energygame.mcqapplication.Service;

import com.energygame.mcqapplication.Dto.QuizDto;
import com.energygame.mcqapplication.Model.Quiz;
import com.energygame.mcqapplication.Repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuizService {

    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }


    public void saveQuestion(QuizDto quizDto) {
        // Convert QuizDto to Quiz entity
        Quiz quiz = new Quiz();
        quiz.setQuestionText(quizDto.getQuestionText());
        quiz.setCorrectAnswer(quizDto.getCorrectAnswer());
        quiz.setOtherOptions(String.join(",", quizDto.getOtherOptions()));

        // Save the question using the repository
        quizRepository.save(quiz);
    }
}
