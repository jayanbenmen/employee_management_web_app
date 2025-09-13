package com.project.firstSpringProject.controllers;

import com.project.firstSpringProject.dtos.UserRegistrationDTO;
import com.project.firstSpringProject.entities.Role;
import com.project.firstSpringProject.entities.User;
import com.project.firstSpringProject.entities.UserPrincipal;
import com.project.firstSpringProject.repositories.RoleRepository;
import com.project.firstSpringProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody UserRegistrationDTO userRegistrationDTO){
        if (userService.registerAdmin(userRegistrationDTO).equals(true)){
            return ResponseEntity.ok("Admin account registered successfully");
        }
        return ResponseEntity.badRequest().body(userService.registerAdmin(userRegistrationDTO));
    }

    @PostMapping("/register/staff")
    public ResponseEntity<?> registerStaff(@RequestBody UserRegistrationDTO userRegistrationDTO){
        if (userService.registerStaff(userRegistrationDTO).equals(true)){
            return ResponseEntity.ok("Staff account registered successfully");
        }
        return ResponseEntity.badRequest().body(userService.registerStaff(userRegistrationDTO));
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        return userService.verify(user);
    }

    @GetMapping("/me")
    public String currentUsername(Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return "Hello " + userPrincipal.getFirstName() + " " + userPrincipal.getLastName();
    }
}
