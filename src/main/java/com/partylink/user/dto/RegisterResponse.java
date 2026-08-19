package com.partylink.user.dto;

import java.time.LocalDate;
import java.util.UUID;

public record RegisterResponse(
         UUID id,
         String name,
         String email,
         String phone,
         LocalDate dateOfbirth

) {
}
