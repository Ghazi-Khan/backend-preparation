package com.learnhub.employeeapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Error {
    private HttpStatusCode statusCode;
    private String errorMessage;
}
