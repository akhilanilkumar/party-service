package com.party.partyservice.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class DevelopmentDTO implements Serializable {
    private Long id;

    private Long leaderId;

    private Long partyId;

    private String title;

    private String activity;

    private Double budget;

    private String state;

    private int activityMonth;

    private int activityYear;
}
