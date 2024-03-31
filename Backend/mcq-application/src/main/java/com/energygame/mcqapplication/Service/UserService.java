package com.energygame.mcqapplication.Service;

import com.energygame.mcqapplication.Model.User;
import com.energygame.mcqapplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.energygame.mcqapplication.Config.JwtTokenProvider;

import java.util.List;
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

    public String saveUser(String userName) {
        User existingUser = userRepository.findByUserName(userName);
        if (existingUser != null) {
            return jwtTokenProvider.generateToken(userName);
        } else {
            User user = new User();
            user.setUserName(userName);
            user.setProfileEdited(false);
            user.setQuestionnaireTaken(false);
            user.setQuestionnaireScore(0);
            userRepository.save(user);
            return jwtTokenProvider.generateToken(userName);
        }
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
