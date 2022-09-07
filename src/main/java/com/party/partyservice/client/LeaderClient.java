package com.party.partyservice.client;

import com.party.partyservice.models.LeaderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "${uri.leader-service}", name = "leader-service")
public interface LeaderClient {

    @GetMapping(value = "get-all-leaders/{partyId}")
    List<LeaderDTO> getLeadersByPartyId(@PathVariable Long partyId);

    @GetMapping(value = "find/{partyId}/{leaderId}")
    LeaderDTO getLeaderByPartyIdAndLeaderId(@PathVariable Long partyId, @PathVariable Long leaderId);

    @DeleteMapping(value = "delete/{partyId}/{leaderId}")
    boolean deleteLeaderByPartyIdAndLeaderId(@PathVariable Long partyId, @PathVariable Long leaderId);
}
