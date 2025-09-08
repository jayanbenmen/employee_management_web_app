package com.project.firstSpringProject.dtos;

import com.project.firstSpringProject.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
public class ProfileCreationDTO {
    private int duration;
    private BigDecimal hourlyRate;
    private String nfc;
    private String jobName;
    private String shiftName;
}
