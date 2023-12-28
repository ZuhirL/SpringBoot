package com.example.springboot.dao;

import lombok.Data;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@Table(name = "CDR")
public class CdrEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CDR_SEQ")
    @SequenceGenerator(name = "CDR_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "vehicle_id")
    private String vehicleId;

    @Column(name = "start_time")
    private Instant startTime;

    @Column(name = "end_time")
    private Instant endTime;

    @Column(name = "total_cost")
    private BigDecimal totalCost;

}
