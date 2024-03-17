package com.energygame.mcqapplication.Model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "jwt_token")
    private String jwtToken;

    @Column(name = "profile_edited")
    private boolean profileEdited;

    @Column(name = "questionnaire_taken")
    private boolean questionnaireTaken;

    @Column(name = "questionnaire_score")
    private Integer questionnaireScore = null;


}
