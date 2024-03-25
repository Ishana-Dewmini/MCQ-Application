package com.energygame.mcqapplication.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.spi.Mapping;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "response")
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_no", nullable = false)
    private Integer question;

    @Column(name = "answer", nullable = false)
    private boolean answer;

    @Column(name = "selectedAnswer", nullable = false)
    private String selectedAnswer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

