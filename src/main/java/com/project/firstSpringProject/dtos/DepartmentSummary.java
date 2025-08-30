package com.project.firstSpringProject.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"id", "name"})
public class DepartmentSummary {
    private int id;
    private String name;
}
