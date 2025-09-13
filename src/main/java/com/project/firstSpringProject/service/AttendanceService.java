package com.project.firstSpringProject.service;

import com.project.firstSpringProject.dtos.AttendanceRecordDTO;
import com.project.firstSpringProject.dtos.AttendanceRecordSummary;
import com.project.firstSpringProject.entities.*;
import com.project.firstSpringProject.repositories.AttendanceRecordRepository;
import com.project.firstSpringProject.repositories.ProfileRepository;
import com.project.firstSpringProject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRecordRepository attendanceRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public String record(AttendanceRecordDTO attendanceRecordDTO){
        String nfc = attendanceRecordDTO.getNfc();
        LocalDate date = attendanceRecordDTO.getDate();
        LocalTime time = attendanceRecordDTO.getTime();

        boolean verify = verifyNfc(nfc);
        if(!verify){
            return "User does not exist";
        }

        User user = getUserWithNfc(nfc);
        Shift shift = getShiftWithUser(user);
        int userId = user.getId();

        if(!withinShift(shift, time)){
            return "Not within shift";
        }

        AttendanceRecord attendanceRecord = attendanceRecordRepository.findByUserAndDate(userId, date);

        if(attendanceRecord == null){
            return createNewRecord(date, time, shift, user);
        }

        if(attendanceRecord.getTimeOut() == null){
            if(isTimeOutAllowed(shift, time)){
                attendanceRecord.setTimeOut(time);
                attendanceRecordRepository.save(attendanceRecord);
                return "Time-out recorded successfully";
            }
            return "Attendance already recorded";
        }
        return "Time-out already recorded";
    }

    public boolean verifyNfc(String nfc){
        Profile profile = profileRepository.findByNfcLike(nfc);
        return profile != null;
    }

    public boolean withinShift(Shift shift, LocalTime time){
        LocalTime shiftStart = shift.getStartTime();
        LocalTime shiftEnd = shift.getEndTime();
        LocalTime allowedTime = shiftStart.minusHours(1);
        LocalTime shiftExtend = shiftEnd.plusHours(1);

        if(shift.getName().equals("Night")){
            return time.isAfter(allowedTime) || time.isBefore(shiftExtend);
        }
        return time.isAfter(allowedTime) && time.isBefore(shiftExtend);
    }

    public User getUserWithNfc(String nfc){
        Profile profile = profileRepository.findByNfcLike(nfc);
        return userRepository.findById(profile.getUser().getId());
    }

    public Shift getShiftWithUser(User user){
        Profile profile = profileRepository.findProfileByUserId(user.getId());
        return profile.getShift();
    }

    public String checkStatus(Shift shift, LocalTime time){
        LocalTime shiftStart = shift.getStartTime();
        LocalTime onTime = shiftStart.plusMinutes(5);
        if(time.isAfter(onTime)){
            return "Late";
        }
        return "On-time";
    }

    public String createNewRecord(LocalDate date, LocalTime time, Shift shift, User user){
        AttendanceRecord newRecord = new AttendanceRecord();
        newRecord.setDayDate(date);
        newRecord.setTimeIn(time);
        newRecord.setTimeOut(null);
        newRecord.setStatus(checkStatus(shift, time));
        newRecord.setUser(user);
        attendanceRecordRepository.save(newRecord);
        return "Attendance recorded successfully";
    }

    public boolean isTimeOutAllowed(Shift shift, LocalTime time){
        LocalTime endTime = shift.getEndTime();
        LocalTime allowedTimeOut = endTime.minusMinutes(15);

        return time.isAfter(allowedTimeOut);
    }

    public List<AttendanceRecordSummary> viewMyAttendance(Authentication authentication){
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        int userId = userPrincipal.getId();
        return attendanceRecordRepository.findByUserId(userId);
    }
}
