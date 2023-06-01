package com.example.eksamenbackend.service;

import com.example.eksamenbackend.model.Race;
import com.example.eksamenbackend.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RaceService {

    private final RaceRepository raceRepository;

    @Autowired
    public RaceService(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    public ResponseEntity<Race> createRace(Race race) {
        Race createdRace = raceRepository.save(race);
        return new ResponseEntity<>(createdRace, HttpStatus.CREATED);
    }

    public List<Race> getAllRaces() {
        return raceRepository.findAll();
    }

    public Optional<Race> getRaceById(Long id) {
        return raceRepository.findById(id);
    }

    public ResponseEntity<Race> updateRace(Race race, Long id) {
        if (raceRepository.existsById(id)) {
            race.setId(id);
            Race updatedRace = raceRepository.save(race);
            return new ResponseEntity<>(updatedRace, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Void> deleteRace(Long id) {
        if (raceRepository.existsById(id)) {
            raceRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
