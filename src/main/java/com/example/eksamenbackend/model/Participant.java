package com.example.eksamenbackend.model;

import jakarta.persistence.*;

@Entity
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race race;

    @ManyToOne
    @JoinColumn(name = "boat_id")
    private Boat boat;

    private int points;

    private int finishingPosition;

    private String finishingStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getFinishingPosition() {
        return finishingPosition;
    }

    public void setFinishingPosition(int finishingPosition) {
        this.finishingPosition = finishingPosition;
    }

    public String getFinishingStatus() {
        return finishingStatus;
    }

    public void setFinishingStatus(String finishingStatus) {
        this.finishingStatus = finishingStatus;
    }

    public Participant() {
    }
}