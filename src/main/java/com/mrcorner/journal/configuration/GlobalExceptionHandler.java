package com.mrcorner.journal.configuration;

import com.mrcorner.journal.exceptions.AuthenticationErrorException;
import com.mrcorner.journal.exceptions.DataNotFoundException;
import com.mrcorner.journal.exceptions.InvalidDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException ex){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    } // handleDataNotFoundException

    @ExceptionHandler(AuthenticationErrorException.class)
    @ResponseBody
    public ResponseEntity<Object> handleAuthenticationErrorException(AuthenticationErrorException ex){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.UNAUTHORIZED.value());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    } // handleDataNotFoundException

    @ExceptionHandler(InvalidDataException.class)
    @ResponseBody
    public ResponseEntity<Object> handleInvalidDataException(InvalidDataException ex){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    } // handleDataNotFoundException

} // GlobalExceptionHandler
