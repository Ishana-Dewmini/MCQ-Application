package com.energygame.mcqapplication.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizDto {

    private String questionText;
    private String correctAnswer;
    private String[] otherOptions;


}