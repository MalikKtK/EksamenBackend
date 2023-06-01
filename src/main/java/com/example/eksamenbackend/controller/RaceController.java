package com.example.eksamenbackend.controller;

import com.example.eksamenbackend.model.Race;
import com.example.eksamenbackend.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/races")
public class RaceController {
    private final RaceService raceService;

    @Autowired
    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Race> createRace(@RequestBody Race race) {
        return raceService.createRace(race);
    }

    @GetMapping
    public List<Race> getAllRaces() {
        return raceService.getAllRaces();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Race> getRaceById(@PathVariable Long id) {
        Race race = raceService.getRaceById(id).orElse(null);
        if (race != null) {
            return new ResponseEntity<>(race, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Race> updateRace(@PathVariable Long id, @RequestBody Race race) {
        return raceService.updateRace(race, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRace(@PathVariable Long id) {
        return raceService.deleteRace(id);
    }
}
