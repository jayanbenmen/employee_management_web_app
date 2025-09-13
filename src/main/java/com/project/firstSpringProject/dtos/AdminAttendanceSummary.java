package com.project.firstSpringProject.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class AdminAttendanceSummary {
    private LocalDate dayDate;
    private LocalTime timeIn;
    private LocalTime timeOut;
    private String status;
    private String username;
}
