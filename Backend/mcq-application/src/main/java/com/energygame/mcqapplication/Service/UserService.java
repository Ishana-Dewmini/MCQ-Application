package com.energygame.mcqapplication.Service;

import com.energygame.mcqapplication.Dto.ResponseWithCorrectAnswerDto;
import com.energygame.mcqapplication.Model.User;
import com.energygame.mcqapplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ResponseService responseService;

    @Autowired
    public UserService(UserRepository userRepository, ResponseService responseService) {
        this.userRepository = userRepository;
        this.responseService = responseService;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByToken(String jwtToken) {
        Optional<User> optionalUser = userRepository.findByJwtToken(jwtToken);
        return optionalUser.orElse(null);
    }

    public User getUserById(long user_id) {
        Optional<User> optionalUser = userRepository.findById(user_id);
        return optionalUser.orElse(null);
    }


    public User updateProfileStatus(long user_id) {
        // Fetch the user from the repository
        User user = userRepository.findById(user_id).orElse(null);

        // Check if the user exists
        if (user != null) {
            // Update the profile_edited field
            user.setProfileEdited(true);

            // Save the updated user
            userRepository.save(user);

            // Return the updated user
            return user;
        } else {
            // Handle the case where the user is not found (return null or throw an exception)
            return null;
        }
    }

    public User updateQuestionnaireStatus(long user_id) {
        // Fetch the user from the repository
        User user = userRepository.findById(user_id).orElse(null);

        // Check if the user exists
        if (user != null) {
            // Update the profile_edited field
            user.setQuestionnaireTaken(true);

            // Save the updated user
            userRepository.save(user);

            // Return the updated user
            return user;
        } else {
            // Handle the case where the user is not found (return null or throw an exception)
            return null;
        }
    }

    public Integer getQuestionnaireScore(Long userId) {
        // Retrieve the user by userId
        User user = userRepository.findById(userId).orElse(null);

        // Check if the user exists and if the questionnaire_score is calculated
        if (user != null && user.getQuestionnaireScore() != null) {
            return user.getQuestionnaireScore();
        }

        // Return null if the user or questionnaire_score is not found
        return null;
    }

    public boolean isUserQuestionnaireTaken(Long userId) {
        // Retrieve the user by userId
        User user = userRepository.findById(userId).orElse(null);

        // Check if the user exists and if the questionnaire_taken flag is true
        return user != null && user.isQuestionnaireTaken();
    }

    public int calculateAndStoreQuestionnaireScore(Long userId) {
        // Retrieve all responses for the user
        List<ResponseWithCorrectAnswerDto> responses = responseService.getAllResponsesForUser(userId);

        // Calculate the questionnaire score
        int score = calculateQuestionnaireScore(responses);

        // Update the user's questionnaire score if it's greater than 0
        if (score > 0) {
            User user = userRepository.findById(userId).orElse(null);
            if (user != null) {
                user.setQuestionnaireScore(score);
                userRepository.save(user);
            }
        }

        return score;
    }

    private int calculateQuestionnaireScore(List<ResponseWithCorrectAnswerDto> responses) {
        int score = 0;
        for (ResponseWithCorrectAnswerDto response : responses) {
            if (response.getGivenAnswer().equals(response.getCorrectAnswer())) {
                score++;
            }
        }
        return score;
    }
}
