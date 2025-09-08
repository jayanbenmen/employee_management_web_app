package com.project.firstSpringProject.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
public class AttendanceRecordDTO {
    private String nfc;
    private LocalDate date;
    private LocalTime time;
}
