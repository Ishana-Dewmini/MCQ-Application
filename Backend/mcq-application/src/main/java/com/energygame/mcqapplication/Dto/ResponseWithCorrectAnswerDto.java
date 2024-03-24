package com.energygame.mcqapplication.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseWithCorrectAnswerDto {

    private Integer userId;
    private Integer questionId;
    private String givenAnswer;
    private String correctAnswer;


}