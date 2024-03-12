package com.energygame.mcqapplication.Controller;

import com.energygame.mcqapplication.Dto.ResponseDto;
import com.energygame.mcqapplication.Model.Response;
import com.energygame.mcqapplication.Service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/responses")
public class ResponseController {

    private final ResponseService responseService;

    @Autowired
    public ResponseController(ResponseService responseService) {
        this.responseService = responseService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveResponse(@RequestBody ResponseDto responseDto) {
        try {
            responseService.saveResponse(responseDto);
            return new ResponseEntity<>("Response saved successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error saving response: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
