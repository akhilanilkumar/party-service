package com.party.partyservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDTO {

    private Long id;

    @NotNull
    @Length(min = 5, max = 100, message = "Activity length should be with in 5 to 100 characters!")
    private String activity;

}
