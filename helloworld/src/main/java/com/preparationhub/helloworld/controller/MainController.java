package com.preparationhub.helloworld.controller;

import com.preparationhub.helloworld.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class MainController {

    int visitors = 0;
    @RequestMapping("/")
    public String helloWorld() {
        visitors++;
        return "Hello world - you are the " + visitors + " visitor.";
    }

    @GetMapping("/user")
    public User user() {
        return new User(101, "Ghazi khan", "ghazi@gmail.com");
    }


    @GetMapping("/{id}")
    public String pathVariableExample1(@PathVariable String id) {
        return "The path variable is => " + id;
    }

//    if we want to use different name for variable then the
//    path-variable name then we need to pass the path-variable
//    name inside @PathVariable annotation
    @GetMapping("/user/{myId}")
    String pathVariableExample2(@PathVariable("myId") String id) {
        return "The path variable is => " + id;
    }
}
