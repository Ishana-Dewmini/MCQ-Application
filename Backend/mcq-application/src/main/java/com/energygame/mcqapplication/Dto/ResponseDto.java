package com.energygame.mcqapplication.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto {

    private Integer userId;
    private Integer questionId;
    private String givenAnswer;

    // Getters and setters
}
