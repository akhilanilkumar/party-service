package com.party.partyservice.services;

import com.party.partyservice.exceptions.NoSuchLeaderExistException;
import com.party.partyservice.exceptions.NoSuchPartyExistException;
import com.party.partyservice.models.DevelopmentDTO;
import com.party.partyservice.models.LeaderDTO;
import com.party.partyservice.models.PartyDTO;

import java.util.List;

public interface PartyService {

    /**
     * Register a political party as new
     *
     * @param partyDTO
     * @return
     */
    PartyDTO registerParty(PartyDTO partyDTO);

    /**
     * Remove a leader from a political party
     *
     * @param partyId
     * @param leaderId
     * @return
     * @throws NoSuchLeaderExistException
     * @throws NoSuchPartyExistException
     */
    boolean removeLeaderFromParty(Long partyId, Long leaderId) throws NoSuchLeaderExistException, NoSuchPartyExistException;

    /**
     * Fetch all the development works assigned to a Political Leader.
     *
     * @param partyId
     * @param leaderId
     * @return
     * @throws NoSuchLeaderExistException
     * @throws NoSuchPartyExistException
     */
    List<DevelopmentDTO> getAllDevelopmentWorksAssignedToLeader(Long partyId, Long leaderId) throws NoSuchLeaderExistException, NoSuchPartyExistException;

    /**
     * Fetch all the Political Leaders registered with a party.
     *
     * @param id
     * @return
     * @throws NoSuchPartyExistException
     */
    List<LeaderDTO> getAllPoliticalLeadersRegisteredWithParty(Long id) throws NoSuchPartyExistException;

    /**
     * Get Party details by id
     *
     * @param partyId
     * @return
     */
    PartyDTO getPartyDetailsById(Long partyId);
}
