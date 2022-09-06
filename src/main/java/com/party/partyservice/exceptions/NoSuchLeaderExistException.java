package com.party.partyservice.exceptions;

public class NoSuchLeaderExistException extends Exception {
    public NoSuchLeaderExistException(Long leaderId) {
        super(String.format("No matching records found for Leader: %d", leaderId));
    }
}
