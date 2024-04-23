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

    @Column(name = "user_name ")
    private String userName;

    @Column(name = "profile_edited")
    private boolean profileEdited;

    @Column(name = "questionnaire_taken")
    private boolean questionnaireTaken;

    @Column(name = "questionnaire_score")
    private Integer questionnaireScore;

    @Column(name = "game_level")
    private Integer gameLevel;

    @Column(name = "energy_coin")
    private Integer energyCoin;

    @Column(name = "game_coin")
    private Integer gameCoin;

    @Column(name = "lands")
    private Integer lands;
}
