package com.party.partyservice.models;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Data
public class PartyDTO {
    private Long id;

    @NotNull(message = "Party Name is required!")
    @Length(min = 5, max = 100, message = "Party Name length should be with in 5 to 100 characters!")
    private String name;

    @NotNull(message = "Founder Name is required!")
    @Length(min = 5, max = 100, message = "Founder length should be with in 5 to 100 characters!")
    private String founderName;

    @NotNull(message = "Founder Year is required!")
    @Digits(integer = 4, fraction = 0, message = "Year accepts only integers")
    private int founderYear;
}
