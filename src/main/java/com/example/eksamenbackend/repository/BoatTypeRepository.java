package com.example.eksamenbackend.repository;

import com.example.eksamenbackend.model.BoatType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoatTypeRepository extends JpaRepository<BoatType, Long> {
    BoatType findByName(String s);
}
