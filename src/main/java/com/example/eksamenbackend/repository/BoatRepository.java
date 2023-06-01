package com.example.eksamenbackend.repository;

import com.example.eksamenbackend.model.Boat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoatRepository extends JpaRepository<Boat, Long> {
}
