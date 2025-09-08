package com.project.firstSpringProject.repositories;

import com.project.firstSpringProject.entities.AttendanceRecord;
import com.project.firstSpringProject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Integer> {
    @Query("select r from AttendanceRecord r where r.user.id = :userId and r.dayDate = :date")
    AttendanceRecord findByUserAndDate(@Param("userId") int userId, @Param("date") LocalDate date);
}
