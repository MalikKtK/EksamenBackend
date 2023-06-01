package com.example.eksamenbackend.service;


import com.example.eksamenbackend.model.Boat;
import com.example.eksamenbackend.model.BoatType;
import com.example.eksamenbackend.repository.BoatRepository;
import com.example.eksamenbackend.repository.BoatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoatService {

    @Autowired
    BoatRepository boatRepository;

    @Autowired
    BoatTypeRepository boatTypeRepository;

    public ResponseEntity<Boat> postBoat(Boat boat) {
        if (doesBoatExist(boat)) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        Boat postedBoat = boatRepository.save(boat);
        return new ResponseEntity<>(postedBoat, HttpStatus.OK);
    }

    public List<Boat> getBoats() {
        return boatRepository.findAll();
    }

    public Optional<Boat> getBoat(Long id) {
        return boatRepository.findById(id);
    }

    public ResponseEntity<Boat> putBoat(Boat boat, long shopId) {
        if (doesBoatExist(shopId)) {
            boat.setId(shopId);
            Boat updatedBoat = boatRepository.save(boat);
            return new ResponseEntity<>(updatedBoat, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<Boat> deleteBoat(long boatId) {
        Optional<Boat> optionalBoat = boatRepository.findById(boatId);
        if (doesBoatExist(boatId)) {
            Boat deletedBoat = optionalBoat.get();
            boatRepository.deleteById(boatId);
            return new ResponseEntity<>(deletedBoat, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public Boat saveBoat(Boat boat) {
        return boatRepository.save(boat);
    }

    private boolean doesBoatExist(Boat boat) {
        Long boatId = boat.getId();
        return doesBoatExist(boatId);
    }

    private boolean doesBoatExist(Long id) {
        boolean isIdSet = id != null;
        return isIdSet && boatRepository.existsById(id);
    }

    public ResponseEntity<Boat> putBoats(List<Boat> boats) {
        for (Boat boat : boats) {
            if (doesBoatExist(boat)) boatRepository.save(boat);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<BoatType> getBoatTypes() {
        return boatTypeRepository.findAll();
    }
}
