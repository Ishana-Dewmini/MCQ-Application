package com.energygame.mcqapplication.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "response")
public class Response {

    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "response", columnDefinition = "TEXT")
    private String response;


}
