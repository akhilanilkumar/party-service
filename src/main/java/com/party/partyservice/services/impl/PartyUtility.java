package com.party.partyservice.services.impl;

import com.party.partyservice.entity.Party;
import com.party.partyservice.models.DevelopmentDTO;
import com.party.partyservice.models.LeaderDTO;
import com.party.partyservice.models.PartyDTO;
import lombok.extern.log4j.Log4j2;
import org.bouncycastle.util.Arrays;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public final class PartyUtility {

    private static final RestTemplate restTemplate = new RestTemplate();

    private static final String DEV_SERVICE_URI = "http://localhost:9093/development/";
    private static final String LEADER_SERVICE_URI = "http://localhost:9092/leader/";

    private PartyUtility() {

    }

    public static Party convertToEntity(PartyDTO partyDTO) {
        Party party = new Party();
        party.setId(partyDTO.getId());
        party.setName(partyDTO.getName());
        party.setFounderName(partyDTO.getFounderName());
        party.setFounderYear(partyDTO.getFounderYear());
        return party;
    }

    public static PartyDTO convertToDTO(Party party) {
        PartyDTO partyDTO = new PartyDTO();
        partyDTO.setId(party.getId());
        partyDTO.setName(party.getName());
        partyDTO.setFounderName(party.getFounderName());
        partyDTO.setFounderYear(party.getFounderYear());
        return partyDTO;
    }

    public static List<DevelopmentDTO> getAllAssignedDevWorks(Long partyId, Long leaderId) {
        final String URI = DEV_SERVICE_URI + "works/" + partyId + "/" + leaderId;
        ResponseEntity<DevelopmentDTO[]> devTemplateObj = restTemplate.getForEntity(URI, DevelopmentDTO[].class);
        List<DevelopmentDTO> developmentDTOs = new ArrayList<>();
        if (!Arrays.isNullOrEmpty(devTemplateObj.getBody())) {
            developmentDTOs = List.of(devTemplateObj.getBody());
        }
        return developmentDTOs;
    }

    /**
     * Get the details of all leaders under a Political Party
     *
     * @param partyId
     * @return
     */
    public static List<LeaderDTO> findLeaderByPartyId(Long partyId) {
        LeaderDTO[] leadersArr = restTemplate.getForObject(LEADER_SERVICE_URI + "get-all-leaders/" + partyId, LeaderDTO[].class);
        return Arrays.isNullOrEmpty(leadersArr) ? new ArrayList<>() : List.of(leadersArr);
    }

    /**
     * Check if a leader exists at a party
     *
     * @param partyId
     * @param leaderId
     * @return
     */
    public static Optional<LeaderDTO> findLeaderByPartyIdAndLeaderId(Long partyId, Long leaderId) {
        final String URI = LEADER_SERVICE_URI + "find/" + partyId + "/" + leaderId;
        Optional<LeaderDTO> optionalLeaderDTO = Optional.ofNullable(restTemplate.getForObject(URI, LeaderDTO.class));
        log.info("Response from {}: {}", URI, optionalLeaderDTO.get());
        return optionalLeaderDTO;
    }

    /**
     * Delete a leader by party id and leader id
     *
     * @param partyId
     * @param leaderId
     * @return
     */
    public static boolean deleteLeaderByPartyIdAndLeaderId(Long partyId, Long leaderId) {
        final String URI = LEADER_SERVICE_URI + "delete/" + partyId + "/" + leaderId;
        restTemplate.delete(URI, Boolean.class);
        return true;
    }
}
