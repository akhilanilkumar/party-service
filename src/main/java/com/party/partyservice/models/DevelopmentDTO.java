package com.party.partyservice.models;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class DevelopmentDTO implements Serializable {
    private Long id;

    private Long leaderId;

    private Long partyId;

    private String title;

    private Set<ActivityDTO> activityDTOS;

    private Double budget;

    private String state;

    private int activityMonth;

    private int activityYear;
}
