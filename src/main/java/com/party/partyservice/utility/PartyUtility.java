package com.party.partyservice.utility;

import com.party.partyservice.entity.Party;
import com.party.partyservice.models.PartyDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.client.RestTemplate;

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

}
