package com.project.firstSpringProject.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
public class ShiftCreationDTO {
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private BigDecimal multiplier;
}
