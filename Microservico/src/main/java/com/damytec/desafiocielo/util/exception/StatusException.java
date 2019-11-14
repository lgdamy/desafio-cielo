package com.damytec.desafiocielo.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class StatusException extends RuntimeException{
    private String message;
    private HttpStatus status;
}
