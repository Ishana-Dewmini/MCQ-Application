//package com.energygame.mcqapplication.Controller;
//
//
//import com.energygame.mcqapplication.Model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@CrossOrigin
//@RestController
//@RequestMapping("/energy-quest/user")
//public class UserController {
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    // add a user to the database
//    @PostMapping
//    public User saveUser(@RequestBody User user) {
//        return userService.saveUser(user);
//    }
//
//    // get the user from the database with selected JWT token
//    @GetMapping("/token/{jwtToken}")
//    public User getUserByToken(@PathVariable String jwtToken) {
//        return userService.getUserByToken(jwtToken);
//    }
//
//    // get the user from the database with selected ID
//    @GetMapping("/id/{user_id}")
//    public User getUserById(@PathVariable long user_id) {
//        return userService.getUserById(user_id);
//    }
//
//    // save the profile update status of a selected user in the database
//    @PostMapping("profile/{user_id}")
//    public User updateProfileStatus(@PathVariable int user_id) {
//        return userService.updateProfileStatus(user_id);
//    }
//
//
//    // save the questionnaire status of a selected user in the database
//    @PostMapping("questionnaire/{user_id}")
//    public User updateQuestionnaireStatus(@PathVariable int user_id) {
//        return userService.updateQuestionnaireStatus(user_id);
//    }
//}
//

