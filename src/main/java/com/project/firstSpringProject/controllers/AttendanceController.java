package com.project.firstSpringProject.controllers;

import com.project.firstSpringProject.dtos.AttendanceRecordDTO;
import com.project.firstSpringProject.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/attendance")
@RestController
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/record")
    public String recordAttendance(@RequestBody AttendanceRecordDTO attendanceRecordDTO){
        return attendanceService.record(attendanceRecordDTO);
    }
}
