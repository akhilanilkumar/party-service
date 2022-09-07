package com.party.partyservice.services.impl;

import com.party.partyservice.client.DevelopmentClient;
import com.party.partyservice.client.LeaderClient;
import com.party.partyservice.models.DevelopmentDTO;
import com.party.partyservice.models.LeaderDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Log4j2
public class RemoteServiceHelper {

    @Autowired
    private LeaderClient leaderClient;

    @Autowired
    private DevelopmentClient developmentClient;

    public List<DevelopmentDTO> getAllAssignedDevWorks(Long partyId, Long leaderId) {
        List<DevelopmentDTO> developmentDetails = developmentClient.getDevelopmentDetails(partyId, leaderId);
        log.debug("Response from Dev Service: {}", developmentDetails);
        return developmentDetails;
    }

    /**
     * Get the details of all leaders under a Political Party
     *
     * @param partyId
     * @return
     */
    public List<LeaderDTO> findLeaderByPartyId(Long partyId) {
        List<LeaderDTO> leadersByPartyId = leaderClient.getLeadersByPartyId(partyId);
        log.debug("Response from Leader Service: {}", leadersByPartyId);
        return leadersByPartyId;
    }

    /**
     * Check if a leader exists at a party
     *
     * @param partyId
     * @param leaderId
     * @return
     */
    public Optional<LeaderDTO> findLeaderByPartyIdAndLeaderId(Long partyId, Long leaderId) {
        LeaderDTO leaderDTO = leaderClient.getLeaderByPartyIdAndLeaderId(partyId, leaderId);
        Optional<LeaderDTO> optionalLeaderDTO = Optional.ofNullable(leaderDTO);
        log.debug("Response from Leader Service: {}", leaderDTO);
        return optionalLeaderDTO;
    }

    /**
     * Delete a leader by party id and leader id
     *
     * @param partyId
     * @param leaderId
     * @return
     */
    public boolean deleteLeaderByPartyIdAndLeaderId(Long partyId, Long leaderId) {
        boolean status = leaderClient.deleteLeaderByPartyIdAndLeaderId(partyId, leaderId);
        log.debug("Response from Leader Service: {}", status);
        return status;
    }
}
