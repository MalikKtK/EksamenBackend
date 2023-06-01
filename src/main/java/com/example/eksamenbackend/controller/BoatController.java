package com.example.eksamenbackend.controller;

import com.example.eksamenbackend.model.Boat;
import com.example.eksamenbackend.model.BoatType;
import com.example.eksamenbackend.service.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/boats")
public class BoatController {
    private final BoatService boatService;

    @Autowired
    public BoatController(BoatService boatService) {
        this.boatService = boatService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Boat> postBoat(@RequestBody Boat boat) {
        return boatService.postBoat(boat);
    }

    @GetMapping
    public List<Boat> getBoats() {
        return boatService.getBoats();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boat> getBoat(@PathVariable Long id) {
        Boat boat = boatService.getBoat(id).orElse(null);
        if (boat != null) {
            return new ResponseEntity<>(boat, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boat> putBoat(@PathVariable Long id, @RequestBody Boat boat) {
        return boatService.putBoat(boat, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boat> deleteBoat(@PathVariable Long id) {
        return boatService.deleteBoat(id);
    }
    @GetMapping("/types")
    public List<BoatType> getBoatTypes() {
        return boatService.getBoatTypes();
    }
}
