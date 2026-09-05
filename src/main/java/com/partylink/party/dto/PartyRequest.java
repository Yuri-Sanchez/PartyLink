package com.partylink.party.dto;

import com.partylink.party.entity.PartyVisibility;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

public record PartyRequest(
                        @NotBlank(message = "Party title is required")
                        @Size(max = 100, message = "Party title must not exceed 100 characters")
                        String title,

                        String description,

                        @NotNull(message = "Event date is required")
                        @Future(message = "Event date must be in the future")
                        OffsetDateTime eventDate,

                        @NotBlank(message = "Location is required")
                        @Size(max = 255, message = "Location must not exceed 255 characters")
                        String location,

                        @NotNull(message = "Party visibility is required")
                        PartyVisibility visibility
                    ) {
}
