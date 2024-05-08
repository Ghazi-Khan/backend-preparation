package com.preparationhub.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class MainController {
    @RequestMapping("/")
    public String helloWorld() {
        return "Hello world";
    }
}
