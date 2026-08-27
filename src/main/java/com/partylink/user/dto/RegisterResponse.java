package com.partylink.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.UUID;

public record RegisterResponse(
         UUID id,
         String name,
         String email,
         String phone,
         @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
         LocalDate dateOfBirth

) {
}
