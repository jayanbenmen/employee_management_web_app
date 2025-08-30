package com.project.firstSpringProject.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/helloAdmin")
    public String greetAdmin(){
        return "Hello Admin";
    }

    @GetMapping("/helloStaff")
    public String greetStaff(){
        return "Hello Staff";
    }
}
