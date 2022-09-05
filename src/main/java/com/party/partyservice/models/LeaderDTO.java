package com.party.partyservice.models;

import lombok.Data;

@Data
public class LeaderDTO {
    private Long id;

    private Long partyId;

    private String leaderName;

    private String leaderState;
}
