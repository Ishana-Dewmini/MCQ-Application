package com.energygame.mcqapplication.Service;
import com.energygame.mcqapplication.Model.User;
import com.energygame.mcqapplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.energygame.mcqapplication.Config.JwtTokenProvider;
import java.util.HashMap;
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

    // Method to save a user
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
            user.setGameLevel(1);
            user.setEnergyCoin(0);
            user.setGameCoin(0);
            user.setLands(0);
            userRepository.save(user);
            int userID = userRepository.findByUserName(userName).getUserId();
            response.put("userID", userID);
            response.put("token", jwtTokenProvider.generateToken(userName));
        }
        return response;
    }

    // Method to find a user by userId
    public User getUserById(long user_id) {
        Optional<User> optionalUser = userRepository.findById(user_id);
        return optionalUser.orElse(null);
    }

    // Method to update profile updating status of a user
    public User updateProfileStatus(long user_id) {
        // Fetch the user from the repository
        User user = userRepository.findById(user_id).orElse(null);
        assert user != null;
        // Update the profile_edited field
        user.setProfileEdited(true);
        // Save the updated user
        userRepository.save(user);
        // Return the updated user
        return user;
    }

    // Method to update questionnaire status of a user
    public User updateQuestionnaireStatus(long user_id) {
        User user = userRepository.findById(user_id).orElse(null);
        assert user != null;
        user.setQuestionnaireTaken(true);
        userRepository.save(user);
        return user;

    }

    //Method to update energy coin value of a user
    public User updateEnergyCoin(long user_id, int energyCoin) {
        User user = userRepository.findById(user_id).orElse(null);
        assert user != null;
        user.setEnergyCoin(energyCoin);
        userRepository.save(user);
        return user;

    }

    //Method to update game level of a user
    public User updateGameLevel(long user_id, int gameLevel) {
        User user = userRepository.findById(user_id).orElse(null);
        assert user != null;
        user.setGameLevel(gameLevel);
        userRepository.save(user);
        return user;

    }

    //Method to update game coin value of a user
    public User updateGameCoin(long user_id, int gameCoin) {
        User user = userRepository.findById(user_id).orElse(null);
        assert user != null;
        user.setGameCoin(gameCoin);
        userRepository.save(user);
        return user;

    }

    // Method to update questionnaire score of  a user
    public User updateQuestionnaireScore(long user_id, int score) {
        User user = userRepository.findById(user_id).orElse(null);
        assert user != null;
        user.setQuestionnaireScore(score);
        userRepository.save(user);
        return user;

    }

    // Method to update Lands  of a user
    public User updateLands(long user_id, int lands) {
        User user = userRepository.findById(user_id).orElse(null);
        assert user != null;
        user.setLands(lands);
        userRepository.save(user);
        return user;

    }

    //Method to get questionnaire score of a user
    public Integer getQuestionnaireScore(int userId) {
        User user = userRepository.findById((long) userId).orElse(null);
        assert user != null;
        return user.getQuestionnaireScore();
    }

    //Method to get game coin value of a user
    public Integer getGameCoin(int userId) {
        User user = userRepository.findById((long) userId).orElse(null);
        assert user != null;
        return user.getGameCoin();
    }

    // Method to get game level of a user
    public Integer getGameLevel(int userId) {
        User user = userRepository.findById((long) userId).orElse(null);
        assert user != null;
        return user.getGameLevel();
    }

    // Method to get energy coin value of a user
    public Integer getEnergyCoin(int userId) {
        User user = userRepository.findById((long) userId).orElse(null);
        assert user != null;
        return user.getEnergyCoin();
    }

    // Method to get energy coin value of a user
    public Integer getLands(int userId) {
        User user = userRepository.findById((long) userId).orElse(null);
        assert user != null;
        return user.getLands();
    }


}
