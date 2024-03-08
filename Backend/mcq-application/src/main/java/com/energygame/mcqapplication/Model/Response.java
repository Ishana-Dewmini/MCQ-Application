package com.energygame.mcqapplication.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "response")
@IdClass(ResponseId.class)
public class Response {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Quiz question;

    @Column(name = "given_answer", nullable = false)
    private String givenAnswer;

    // Getters and setters
}

