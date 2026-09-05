package com.partylink.party.dto;

import com.partylink.party.entity.Party;
import com.partylink.party.entity.PartyVisibility;
import lombok.Builder;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
public record PartyResponse(
        UUID id,
        String title,
        String description,
        OffsetDateTime eventDate,
        String location,
        PartyVisibility visibility,
        UUID hostId,
        Instant createdAt,
        Instant updatedAt
) {
}
