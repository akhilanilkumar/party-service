package com.party.partyservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartyLeaderDTO {

    private LeaderDTO leaderDTO;

    private PartyDTO partyDTO;
}
