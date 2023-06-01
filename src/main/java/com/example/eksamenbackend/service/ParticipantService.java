package com.example.eksamenbackend.service;

import com.example.eksamenbackend.model.Participant;
import com.example.eksamenbackend.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {
    private final ParticipantRepository participantRepository;

    @Autowired
    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    public Optional<Participant> getParticipantById(Long id) {
        return participantRepository.findById(id);
    }

    public Participant createParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    public Optional<Participant> updateParticipant(Long id, Participant participant) {
        Optional<Participant> existingParticipant = participantRepository.findById(id);
        if (existingParticipant.isPresent()) {
            participant.setId(id);
            Participant updatedParticipant = participantRepository.save(participant);
            return Optional.of(updatedParticipant);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Participant> deleteParticipant(Long id) {
        Optional<Participant> existingParticipant = participantRepository.findById(id);
        if (existingParticipant.isPresent()) {
            Participant deletedParticipant = existingParticipant.get();
            participantRepository.deleteById(id);
            return Optional.of(deletedParticipant);
        } else {
            return Optional.empty();
        }
    }

}
