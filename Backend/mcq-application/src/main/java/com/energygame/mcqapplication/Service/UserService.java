package com.energygame.mcqapplication.Service;

import com.energygame.mcqapplication.Model.User;
import com.energygame.mcqapplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
