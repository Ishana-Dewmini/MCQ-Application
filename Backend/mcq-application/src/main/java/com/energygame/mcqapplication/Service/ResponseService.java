package com.energygame.mcqapplication.Service;

import com.energygame.mcqapplication.Model.Response;
import com.energygame.mcqapplication.Repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResponseService {

    @Autowired
    private ResponseRepository responseRepository;

    public void saveResponse(Integer userId, String responseJson) {
        Response response = new Response();
        response.setUserId(userId);
        response.setResponseJson(responseJson);
        responseRepository.save(response);
    }
    public String getResponseByUserId(Integer userId) {
        Optional<Response> optionalResponse = responseRepository.findById(Long.valueOf(userId));
        return optionalResponse.map(Response::getResponseJson).orElse(null);
    }
}
