package com.example.springboot.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CdrRepository extends JpaRepository<CdrEntity, Long> {

    List<CdrEntity> findByVehicleId(String vehicleId);

    List<CdrEntity> findByVehicleId(String vehicleId, Sort sort);

    Page<CdrEntity> findByVehicleId(String vehicleId, Pageable pageable);

}
