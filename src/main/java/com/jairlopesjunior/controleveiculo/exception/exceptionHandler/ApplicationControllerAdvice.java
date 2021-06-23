package com.jairlopesjunior.controleveiculo.exception.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleNotValidException( MethodArgumentNotValidException ex ){
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map( m -> m.getDefaultMessage() )
                .collect(Collectors.toList());
        return new ApiErrors(errors);
    }
}
