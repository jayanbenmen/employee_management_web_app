package com.project.firstSpringProject.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"id", "username", "firstName", "lastName", "email"})
public class UserSummary {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
