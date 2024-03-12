package com.energygame.mcqapplication.Service;

import com.energygame.mcqapplication.Dto.ResponseDto;
import com.energygame.mcqapplication.Model.Quiz;
import com.energygame.mcqapplication.Model.Response;
import com.energygame.mcqapplication.Model.User;
import com.energygame.mcqapplication.Repository.ResponseRepository;
import com.energygame.mcqapplication.Repository.UserRepository;
import com.energygame.mcqapplication.Repository.QuizRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    private final ResponseRepository responseRepository;
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;

    @Autowired
    public ResponseService(ResponseRepository responseRepository, UserRepository userRepository, QuizRepository quizRepository) {
        this.responseRepository = responseRepository;
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;
    }

    public void saveResponse(ResponseDto responseDto) {
        User user = userRepository.findById(Long.valueOf(responseDto.getUserId()))
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + responseDto.getUserId()));

        Quiz question = quizRepository.findById(Long.valueOf(responseDto.getQuestionId()))
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + responseDto.getQuestionId()));

        Response response = new Response();
        response.setUser(user);
        response.setQuestion(question);
        response.setGivenAnswer(responseDto.getGivenAnswer());

        responseRepository.save(response);
    }



}
