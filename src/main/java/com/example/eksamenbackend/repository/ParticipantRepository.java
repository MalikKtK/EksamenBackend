package com.example.eksamenbackend.repository;

import com.example.eksamenbackend.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findByRaceId(Long raceId);
}
