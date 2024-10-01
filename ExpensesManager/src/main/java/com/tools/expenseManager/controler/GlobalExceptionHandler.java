package com.tools.expenseManager.controler;

import com.tools.expenseManager.exceptions.NullFieldException;
import com.tools.expenseManager.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullFieldException.class)
    public ResponseEntity<String> handleIncomingFieldsEmptyException(NullFieldException nullFieldException){
        return  new ResponseEntity<>(nullFieldException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<String> entityNotFoundException(ObjectNotFoundException objectNotFoundException){
        return  new ResponseEntity<>(objectNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
