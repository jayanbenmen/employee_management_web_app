package com.project.firstSpringProject.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class AttendanceRecordSummary {
    private LocalDate dayDate;
    private LocalTime timeIn;
    private LocalTime timeOut;
    private String status;
}
