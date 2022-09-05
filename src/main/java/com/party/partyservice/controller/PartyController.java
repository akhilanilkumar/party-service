package com.party.partyservice.controller;

import com.party.partyservice.exceptions.NoSuchLeaderExistException;
import com.party.partyservice.exceptions.NoSuchPartyExistException;
import com.party.partyservice.models.DevelopmentDTO;
import com.party.partyservice.models.LeaderDTO;
import com.party.partyservice.models.PartyDTO;
import com.party.partyservice.services.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "party")
public class PartyController {

    @Autowired
    PartyService partyService;

    @PostMapping(value = "register")
    public PartyDTO registerAParty(@Valid @RequestBody PartyDTO partyDTO) {
        return partyService.registerParty(partyDTO);
    }

    @DeleteMapping(value = "remove-leader/{partyId}/{leaderId}")
    public boolean deleteLeaderFromParty(@PathVariable Long partyId, @PathVariable Long leaderId) throws NoSuchLeaderExistException, NoSuchPartyExistException {
        return partyService.removeLeaderFromParty(partyId, leaderId);
    }

    @GetMapping(value = "{partyId}")
    public PartyDTO getPartyDetailsById(@PathVariable Long partyId) {
        return partyService.getPartyDetailsById(partyId);
    }

    @GetMapping(value = "leaders/{partyId}")
    public List<LeaderDTO> getLeadersUnderParty(@PathVariable Long partyId) throws NoSuchPartyExistException {
        return partyService.getAllPoliticalLeadersRegisteredWithParty(partyId);
    }

    @GetMapping(value = "leader/works/{partyId}/{leaderId}")
    public List<DevelopmentDTO> getAssignedWorks(@PathVariable Long partyId, @PathVariable Long leaderId) throws NoSuchLeaderExistException, NoSuchPartyExistException {
        return partyService.getAllDevelopmentWorksAssignedToLeader(partyId, leaderId);
    }
}
