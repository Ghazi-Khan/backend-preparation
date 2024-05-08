package com.learnhub.employeeapp.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties({"email"})
public class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
}
