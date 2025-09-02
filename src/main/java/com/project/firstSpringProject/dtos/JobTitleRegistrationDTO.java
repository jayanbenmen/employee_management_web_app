package com.project.firstSpringProject.dtos;

import com.project.firstSpringProject.entities.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class JobTitleRegistrationDTO {
    private String name;
    private String departmentName;
}
