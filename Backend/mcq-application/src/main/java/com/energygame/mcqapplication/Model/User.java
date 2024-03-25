package com.energygame.mcqapplication.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "api_key", nullable = false)
    private String apiKey;

    @Column(name = "profile_completed")
    private boolean profileCompleted;

    @Column(name = "questionnaire_completed")
    private boolean questionnaireCompleted;

    @Column(name = "user_marks")
    private Integer userMarks;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Response> responses;

}
