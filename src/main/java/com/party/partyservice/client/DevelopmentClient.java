package com.party.partyservice.client;

import com.party.partyservice.exceptions.NoSuchLeaderExistException;
import com.party.partyservice.exceptions.NoSuchPartyExistException;
import com.party.partyservice.models.DevelopmentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@FeignClient(url = "${uri.development-service}", name = "development-service")
public interface DevelopmentClient {
    @GetMapping(value = "works/{partyId}/{leaderId}")
    public List<DevelopmentDTO> getDevelopmentDetails(@PathVariable Long partyId, @PathVariable Long leaderId);

    @PostMapping(value = "assign-work")
    public DevelopmentDTO assignDevWorks(@Valid @RequestBody DevelopmentDTO developmentDTO) throws NoSuchLeaderExistException, NoSuchPartyExistException;
}
