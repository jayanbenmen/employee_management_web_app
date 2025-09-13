package com.project.firstSpringProject.controllers;

import com.project.firstSpringProject.dtos.AttendanceRecordDTO;
import com.project.firstSpringProject.dtos.AttendanceRecordSummary;
import com.project.firstSpringProject.entities.AttendanceRecord;
import com.project.firstSpringProject.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/attendance")
@RestController
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/record")
    public String recordAttendance(@RequestBody AttendanceRecordDTO attendanceRecordDTO){
        return attendanceService.record(attendanceRecordDTO);
    }

    @GetMapping("/view")
    public List<AttendanceRecordSummary> myAttendance(Authentication authentication){
        return attendanceService.viewMyAttendance(authentication);
    }
}
