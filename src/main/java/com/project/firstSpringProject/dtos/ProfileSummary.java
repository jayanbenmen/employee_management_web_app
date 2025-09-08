package com.project.firstSpringProject.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProfileSummary {
    private int id;
    private int duration;
    private BigDecimal hourlyRate;
    private String nfc;
    private String username;
    private String jobName;
    private String shiftName;
}
