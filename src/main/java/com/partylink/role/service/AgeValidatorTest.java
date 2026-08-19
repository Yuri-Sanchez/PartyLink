package com.partylink.role.service;

import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class AgeValidatorTest {

    private final AgeValidator ageValidator = new AgeValidator();

    @Test
    void shouldAcceptUserWithExactly18Years() {
        LocalDate dateOfBirth = LocalDate.now().minusYears(18);

        assertTrue(ageValidator.isAdult(dateOfBirth));
    }

    @Test
    void shouldAcceptUserOlderThan18() {
        LocalDate dateOfBirth = LocalDate.now().minusYears(25);

        assertTrue(ageValidator.isAdult(dateOfBirth));
    }

    @Test
    void shouldRejectUserYoungerThan18() {
        LocalDate dateOfBirth = LocalDate.now().minusYears(17);

        assertFalse(ageValidator.isAdult(dateOfBirth));
    }

    @Test
    void shouldRejectFutureDate() {
        LocalDate dateOfBirth = LocalDate.now().plusDays(1);

        assertFalse(ageValidator.isAdult(dateOfBirth));
    }

    @Test
    void shouldRejectNullDate() {
        assertFalse(ageValidator.isAdult(null));
    }
}
