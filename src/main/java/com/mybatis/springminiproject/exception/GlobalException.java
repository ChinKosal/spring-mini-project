package com.mybatis.springminiproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail notFoundException(NotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                e.getMessage()
        );
        problemDetail.setTitle("Not Found");
        problemDetail.setProperty("dateTime", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) { // handle args
        Map<String, String> errors = new HashMap<>();
        for(var fieldErrors : e.getBindingResult().getFieldErrors()){
            errors.put(fieldErrors.getField(),fieldErrors.getDefaultMessage());
        }
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Bad Request");
        problemDetail.setProperty("error",errors);
        return problemDetail;
    }


    //handle number
    @ExceptionHandler(HandlerMethodValidationException.class)
    public  ProblemDetail handlerMethodValidationException(HandlerMethodValidationException e){
        Map<String,String> errors = new HashMap<>();
        for(var paraError : e.getAllValidationResults()){ //loop catch error
            String getParaError = paraError.getMethodParameter().getParameterName();
            for(var error : paraError.getResolvableErrors()){ // loop message error
                errors.put(getParaError,error.getDefaultMessage());
            }
        }

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Bad Request");
        problemDetail.setProperty("error",errors);
        return problemDetail;
    }
}
