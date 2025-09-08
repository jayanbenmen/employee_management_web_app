package com.project.firstSpringProject.repositories;

import com.project.firstSpringProject.dtos.ShiftSummary;
import com.project.firstSpringProject.entities.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Integer> {
    Shift findByNameLike(String name);
    ShiftSummary findById(int id);

    @Query("select s.id as id, s.name as name, s.startTime as startTime, s.endTime as endTime, s.multiplier as multiplier from Shift s")
    List<ShiftSummary> findShifts();

    @Query("select s from Shift s where s.id = :id")
    Shift findShiftById(@Param("id") int id);


}
