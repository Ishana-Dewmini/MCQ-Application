package com.energygame.mcqapplication.Service;

import com.energygame.mcqapplication.Model.Response;
import com.energygame.mcqapplication.Repository.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    private final ResponseRepository responseRepository;

    @Autowired
    public ResponseService(ResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
    }

    public void saveResponse(Response response) {
        responseRepository.save(response);
    }


}
