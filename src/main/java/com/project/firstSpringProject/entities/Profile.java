package com.project.firstSpringProject.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int duration;

    @Column(name = "hourly_rate", precision = 100, scale = 2)
    private BigDecimal hourlyRate;

    @Column(name = "nfc")
    private String nfc;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private JobTitle jobTitle;

    @ManyToOne
    @JoinColumn(name = "shift_id")
    private Shift shift;
}
