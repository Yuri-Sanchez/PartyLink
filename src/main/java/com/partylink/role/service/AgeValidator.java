package com.partylink.role.service;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
public class AgeValidator {

    private static final int MINIMUM_AGE = 18;

    public boolean isAdult(LocalDate dateOfBirth){
        if (dateOfBirth == null){
            return false;
        }
        if (dateOfBirth.isAfter(LocalDate.now())){
            return false;
        }

        return Period.between(dateOfBirth, LocalDate.now()).getYears() >= MINIMUM_AGE;
    }
}
