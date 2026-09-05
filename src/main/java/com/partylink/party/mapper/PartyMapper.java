package com.partylink.party.mapper;

import com.partylink.party.dto.PartyRequest;
import com.partylink.party.dto.PartyResponse;
import com.partylink.party.entity.Party;
import com.partylink.user.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PartyMapper {
    public static Party toParty(PartyRequest partyRequest, User host){
        return Party
                .builder()
                .title(partyRequest.title())
                .description(partyRequest.description())
                .eventDate(partyRequest.eventDate())
                .location(partyRequest.location())
                .visibility(partyRequest.visibility())
                .host(host)
                .build();
    }

    public static PartyResponse toPartyResponse(Party party){
        return PartyResponse
                .builder()
                .id(party.getId())
                .title(party.getTitle())
                .description(party.getDescription())
                .eventDate(party.getEventDate())
                .location(party.getLocation())
                .visibility(party.getVisibility())
                .hostId(party.getHost().getId())
                .createdAt(party.getCreatedAt())
                .updatedAt(party.getUpdatedAt())
                .build();
    }
}
