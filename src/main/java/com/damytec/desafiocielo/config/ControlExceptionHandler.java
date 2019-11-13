package com.damytec.desafiocielo.config;

import com.damytec.desafiocielo.model.dto.ResultData;
import com.damytec.desafiocielo.util.exception.StatusException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControlExceptionHandler {

    @ExceptionHandler(StatusException.class)
    public ResponseEntity<ResultData> handleStatusException(StatusException e) {
        return new ResponseEntity<>(new ResultData(e.getMessage()),e.getStatus());
    }
}
