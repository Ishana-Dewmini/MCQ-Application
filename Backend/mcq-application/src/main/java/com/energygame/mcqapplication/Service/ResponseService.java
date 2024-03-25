package com.energygame.mcqapplication.Service;

import com.energygame.mcqapplication.Dto.ResponseDto;
import com.energygame.mcqapplication.Model.Response;
import com.energygame.mcqapplication.Repository.ResponseRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResponseService{

    private ResponseRepository responseRepository;
    private ModelMapper modelMapper;

    public ResponseDto saveAnswers(ResponseDto responseDto)
    {
        Response response = modelMapper.map(responseDto, Response.class);
        Response savedResponse = responseRepository.save(response);
        return modelMapper.map(savedResponse, ResponseDto.class);
    }

}
