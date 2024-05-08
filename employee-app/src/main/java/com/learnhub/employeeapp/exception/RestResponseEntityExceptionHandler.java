package com.learnhub.employeeapp.exception;


import com.learnhub.employeeapp.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error employeeNotFoundHandler(EmployeeNotFoundException employeeNotFoundException) {
        return new Error(
                HttpStatus.NOT_FOUND,
                employeeNotFoundException.getMessage()
        );
    }
}
