package com.energygame.mcqapplication.Service;

import com.energygame.mcqapplication.Model.Response;
import com.energygame.mcqapplication.Repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class ResponseService {

    @Autowired
    private ResponseRepository responseRepository;

    public void saveResponse(Integer userId, Integer[] responseArray) {
        Response response = new Response();
        response.setUserId(userId);
        // Convert integer array to string
        String responseString = Arrays.toString(responseArray);
        response.setResponse(responseString);
        responseRepository.save(response);
    }

    public Integer[] getResponseByUserId(Integer userId) {
        Optional<Response> optionalResponse = responseRepository.findById(Long.valueOf(userId));
        if (optionalResponse.isPresent()) {
            String responseString = optionalResponse.get().getResponse();
            // Convert string back to integer array
            String[] parts = responseString.substring(1, responseString.length() - 1).split(", ");
            Integer[] responseArray = new Integer[parts.length];
            for (int i = 0; i < parts.length; i++) {
                responseArray[i] = Integer.parseInt(parts[i]);
            }
            return responseArray;
        } else {
            return null;
        }
    }
}
