package com.preparationhub.helloworld.controller;

import com.preparationhub.helloworld.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
}
