package com.project.firstSpringProject.repositories;

import com.project.firstSpringProject.dtos.AdminAttendanceSummary;
import com.project.firstSpringProject.dtos.AttendanceRecordSummary;
import com.project.firstSpringProject.entities.AttendanceRecord;
import com.project.firstSpringProject.entities.User;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Integer> {
    @Query("select r from AttendanceRecord r where r.user.id = :userId and r.dayDate = :date")
    AttendanceRecord findByUserAndDate(@Param("userId") int userId, @Param("date") LocalDate date);

    @Query("select r.dayDate as dayDate, r.timeIn as timeIn, r.timeOut as timeOut, r.status as status from AttendanceRecord r join r.user u where r.user.id = :userId")
    List<AttendanceRecordSummary> findByUserId(@Param("userId") int userId);

    @Query("select r.dayDate as dayDate, r.timeIn as timeIn, r.timeOut as timeOut, r.status as status, u.username as username " +
            "from AttendanceRecord r join r.user u")
    List<AdminAttendanceSummary> findAllRecords();

    @Query("select r.dayDate as dayDate, r.timeIn as timeIn, r.timeOut as timeOut, r.status as status, u.username as username from AttendanceRecord r " +
            "join r.user u " +
            "join u.profile p " +
            "join p.shift s " +
            "where s.name = :shiftName")
    List<AdminAttendanceSummary> findByShift(@Param("shiftName") String shiftName);

    @Query("select r.dayDate as dayDate, r.timeIn as timeIn, r.timeOut as timeOut, r.status as status, u.username as username from AttendanceRecord r " +
            "join r.user u " +
            "join u.profile p " +
            "join p.shift s " +
            "where s.name = :shiftName " +
            "and r.dayDate = :dayDate")
    List<AdminAttendanceSummary> findByShiftAndDate(@Param("shiftName") String shiftName, @Param("dayDate") LocalDate dayDate);

    @Query("select r.dayDate as dayDate, r.timeIn as timeIn, r.timeOut as timeOut, r.status as status, u.username as username " +
            "from AttendanceRecord r join r.user u " +
            "join u.profile p " +
            "join p.jobTitle j " +
            "join j.department d " +
            "where d.name = :departmentName")
    List<AdminAttendanceSummary> findByDepartment(@Param("departmentName") String departmentName);

    @Query("select r.dayDate as dayDate, r.timeIn as timeIn, r.timeOut as timeOut, r.status as status, u.username as username " +
            "from AttendanceRecord r join r.user u " +
            "join u.profile p " +
            "join p.jobTitle j " +
            "join j.department d " +
            "where d.name = :departmentName " +
            "and r.dayDate = :dayDate")
    List<AdminAttendanceSummary> findByDepartmentAndDate(@Param("departmentName") String departmentName, @Param("dayDate") LocalDate dayDate);

    @Query("select r.dayDate as dayDate, r.timeIn as timeIn, r.timeOut as timeOut, r.status as status, u.username as username from AttendanceRecord r " +
            "join r.user u " +
            "join u.profile p " +
            "join p.jobTitle j " +
            "where j.name = :jobName")
    List<AdminAttendanceSummary> findByJobTitle(@Param("jobName") String jobName);

    @Query("select r.dayDate as dayDate, r.timeIn as timeIn, r.timeOut as timeOut, r.status as status, u.username as username from AttendanceRecord r " +
            "join r.user u " +
            "join u.profile p " +
            "join p.jobTitle j " +
            "where j.name = :jobName " +
            "and r.dayDate = :dayDate")
    List<AdminAttendanceSummary> findByJobTitleAndDate(@Param("jobName") String jobName, @Param("dayDate") LocalDate dayDate);

    @Query("select r.dayDate as dayDate, r.timeIn as timeIn, r.timeOut as timeOut, r.status as status, u.username as username from AttendanceRecord r join r.user u where r.dayDate = :dayDate")
    List<AdminAttendanceSummary> findByDayDate(@Param("dayDate") LocalDate dayDate);
}
