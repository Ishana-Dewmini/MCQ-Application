package com.energygame.mcqapplication.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseDto {

    private Integer question;
    private boolean answer;
    private String selectedAnswer;
    // Getters and setters
}
