package com.party.partyservice.services.impl;

import com.party.partyservice.entity.Party;
import com.party.partyservice.exceptions.NoSuchLeaderExistException;
import com.party.partyservice.exceptions.NoSuchPartyExistException;
import com.party.partyservice.models.DevelopmentDTO;
import com.party.partyservice.models.LeaderDTO;
import com.party.partyservice.models.PartyDTO;
import com.party.partyservice.repository.PartyRepository;
import com.party.partyservice.services.PartyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class PartyServiceImpl implements PartyService {

    @Autowired
    private PartyRepository partyRepository;

    /**
     * Register a political party as new
     *
     * @param partyDTO
     * @return
     */
    @Override
    public PartyDTO registerParty(PartyDTO partyDTO) {
        Party party = PartyUtility.convertToEntity(partyDTO);
        Party savedParty = partyRepository.save(party);
        return PartyUtility.convertToDTO(savedParty);
    }

    /**
     * Remove a leader from a political party
     *
     * @param partyId
     * @param leaderId
     * @return
     * @throws NoSuchLeaderExistException
     * @throws NoSuchPartyExistException
     */
    @Override
    public boolean removeLeaderFromParty(Long partyId, Long leaderId) throws NoSuchLeaderExistException, NoSuchPartyExistException {
        // Check if the party exists
        partyRepository
                .findById(partyId)
                .orElseThrow(() -> new NoSuchPartyExistException("No matching records found for a Party: " + partyId));

        // Check if the leader exists
        LeaderDTO leaderDTO = PartyUtility
                .findLeaderByPartyIdAndLeaderId(partyId, leaderId)
                .orElseThrow(() -> new NoSuchLeaderExistException("No matching records found for Leader: " + leaderId));
        log.info("Response from Leader Service {}", leaderDTO);
        // Delete leader if he exists
        return PartyUtility.deleteLeaderByPartyIdAndLeaderId(partyId, leaderId);
    }

    /**
     * Fetch all the development works assigned to a Political Leader.
     *
     * @param partyId
     * @param leaderId
     * @return
     * @throws NoSuchLeaderExistException
     * @throws NoSuchPartyExistException
     */
    @Override
    public List<DevelopmentDTO> getAllDevelopmentWorksAssignedToLeader(Long partyId, Long leaderId) throws NoSuchLeaderExistException, NoSuchPartyExistException {
        // Check if the Party exists or not
        partyRepository
                .findById(partyId)
                .orElseThrow(() -> new NoSuchPartyExistException("No matching records found for Leader: " + partyId));

        // Check if a Leader exists or not
        PartyUtility
                .findLeaderByPartyIdAndLeaderId(partyId, leaderId)
                .orElseThrow(() -> new NoSuchLeaderExistException("No matching records found for Leader: " + leaderId));

        // Call the Development Service
        return PartyUtility.getAllAssignedDevWorks(partyId, leaderId);
    }

    /**
     * Fetch all the Political Leaders registered with a party.
     *
     * @param partyId
     * @return
     */
    @Override
    public List<LeaderDTO> getAllPoliticalLeadersRegisteredWithParty(Long partyId) throws NoSuchPartyExistException {
        Party party = partyRepository
                .findById(partyId)
                .orElseThrow(() -> new NoSuchPartyExistException("No matching records found for Party: " + partyId));
        PartyDTO partyDTOResponse = PartyUtility.convertToDTO(party);
        return PartyUtility.findLeaderByPartyId(partyDTOResponse.getId());
    }

    /**
     * Get Party information by id
     *
     * @param partyId
     * @return
     */
    @Override
    public PartyDTO getPartyDetailsById(Long partyId) {
        return partyRepository.findById(partyId).map(PartyUtility::convertToDTO).orElse(null);
    }
}
