package com.example.eksamenbackend.config;

import com.example.eksamenbackend.model.Boat;
import com.example.eksamenbackend.model.BoatType;
import com.example.eksamenbackend.model.Participant;
import com.example.eksamenbackend.repository.BoatRepository;
import com.example.eksamenbackend.repository.BoatTypeRepository;
import com.example.eksamenbackend.model.Race;
import com.example.eksamenbackend.repository.ParticipantRepository;
import com.example.eksamenbackend.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.List;

@Component
public class initData implements CommandLineRunner {

    private final BoatTypeRepository boatTypeRepository;
    private final RaceRepository raceRepository;
    private final BoatRepository boatRepository;
    private final ParticipantRepository participantRepository;

    private static final boolean CREATE_INSTANCES = false;

    @Autowired
    public initData(BoatTypeRepository boatTypeRepository, RaceRepository raceRepository, BoatRepository boatRepository, ParticipantRepository participantRepository) {
        this.boatTypeRepository = boatTypeRepository;
        this.raceRepository = raceRepository;
        this.boatRepository = boatRepository;
        this.participantRepository = participantRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (CREATE_INSTANCES) {
            createRaces();
            createBoatTypes();
            createBoats();
            createParticipants();
        }
    }

    private void createBoatTypes() {
        BoatType boatType1 = new BoatType();
        boatType1.setName("40fod");
        boatTypeRepository.save(boatType1);

        BoatType boatType2 = new BoatType();
        boatType2.setName("<25fod");
        boatTypeRepository.save(boatType2);

        BoatType boatType3 = new BoatType();
        boatType3.setName("25-40fod");
        boatTypeRepository.save(boatType3);
    }

    private void createBoats() {
        BoatType boatType1 = boatTypeRepository.findByName("40fod");
        BoatType boatType2 = boatTypeRepository.findByName("<25fod");
        BoatType boatType3 = boatTypeRepository.findByName("25-40fod");

        Boat boat1 = new Boat();
        boat1.setName("Boat 1");
        boat1.setBoatType(boatType1);
        boatRepository.save(boat1);

        Boat boat2 = new Boat();
        boat2.setName("Boat 2");
        boat2.setBoatType(boatType2);
        boatRepository.save(boat2);

        Boat boat3 = new Boat();
        boat3.setName("Boat 3");
        boat3.setBoatType(boatType3);
        boatRepository.save(boat3);
    }

    private void createParticipants() {
        List<Race> races = raceRepository.findAll();
        if (races.isEmpty()) {
            throw new IllegalStateException("No races found in the database.");
        }

        Race race = races.get(0); // Get the first race from the list

        Boat boat1 = boatRepository.findById(1L).orElseThrow();
        Boat boat2 = boatRepository.findById(2L).orElseThrow();
        Boat boat3 = boatRepository.findById(3L).orElseThrow();

        Participant participant1 = new Participant();
        participant1.setName("Participant 1");
        participant1.setRace(race);
        participant1.setBoat(boat1);
        participant1.setPoints(0);
        participant1.setFinishingPosition(0);
        participant1.setFinishingStatus("");
        participantRepository.save(participant1);

        Participant participant2 = new Participant();
        participant2.setName("Participant 2");
        participant2.setRace(race);
        participant2.setBoat(boat2);
        participant2.setPoints(0);
        participant2.setFinishingPosition(0);
        participant2.setFinishingStatus("");
        participantRepository.save(participant2);

        Participant participant3 = new Participant();
        participant3.setName("Participant 3");
        participant3.setRace(race);
        participant3.setBoat(boat3);
        participant3.setPoints(0);
        participant3.setFinishingPosition(0);
        participant3.setFinishingStatus("");
        participantRepository.save(participant3);
    }


    private void createRaces() {
        LocalDate startDate = LocalDate.of(2023, 5, 1);
        LocalDate endDate = LocalDate.of(2023, 10, 1);

        LocalDate raceDate = startDate;
        int raceCount = 0;

        while (!raceDate.isAfter(endDate)) {
            if (raceDate.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
                Race race = new Race();
                race.setDate(raceDate);
                raceRepository.save(race);
                raceCount++;
            }
            raceDate = raceDate.plusDays(1);
        }

        int numRaces = raceCount * 3; // 3 times the number of Wednesdays
        System.out.println("Created " + numRaces + " races.");
    }


    private int countWednesdays(LocalDate startDate, LocalDate endDate) {
        int count = 0;
        while (startDate.isBefore(endDate) || startDate.isEqual(endDate)) {
            if (startDate.getDayOfWeek() == DayOfWeek.WEDNESDAY) {
                count++;
            }
            startDate = startDate.plusDays(1);
        }
        return count;
    }
}
