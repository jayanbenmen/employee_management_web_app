package com.project.firstSpringProject.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.PersistenceCreator;

import java.math.BigDecimal;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"id", "name", "startTime", "endTime", "multiplier"})
public class ShiftSummary {
    private int id;
    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private BigDecimal multiplier;

    @PersistenceCreator
    public ShiftSummary(int id, String name, LocalTime startTime, LocalTime endTime, BigDecimal multiplier){
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.multiplier = multiplier;
    }
}
