package com.energygame.mcqapplication.Service;

import com.energygame.mcqapplication.Model.User;
import com.energygame.mcqapplication.Repository.UserRepository;
import io.swagger.v3.core.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.energygame.mcqapplication.Config.JwtTokenProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public Map<String, Object> saveUser(String token) {
        String userName = jwtTokenProvider.decodeToken(token);
        User existingUser = userRepository.findByUserName(userName);
        Map<String, Object> response = new HashMap<>();
        if (existingUser != null) {
            int userID = existingUser.getUserId();
            response.put("userID", userID);
            response.put("token", jwtTokenProvider.generateToken(userName));
        } else {
            User user = new User();
            user.setUserName(userName);
            user.setProfileEdited(false);
            user.setQuestionnaireTaken(false);
            user.setQuestionnaireScore(0);
            userRepository.save(user);
            int userID = userRepository.findByUserName(userName).getUserId();
            response.put("userID", userID);
            response.put("token", jwtTokenProvider.generateToken(userName));
        }
        return response;
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

    public User updateQuestionnaireScore(long user_id, int score) {
        // Fetch the user from the repository
        User user = userRepository.findById(user_id).orElse(null);

        // Check if the user exists
        if (user != null) {
            // Update the profile_edited field
            user.setQuestionnaireScore(score);

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











}
