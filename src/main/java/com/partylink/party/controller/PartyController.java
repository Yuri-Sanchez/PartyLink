package com.partylink.party.controller;

import com.partylink.party.dto.PartyRequest;
import com.partylink.party.dto.PartyResponse;
import com.partylink.party.entity.Party;
import com.partylink.party.mapper.PartyMapper;
import com.partylink.party.service.PartyService;
import com.partylink.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parties")
@RequiredArgsConstructor
public class PartyController {

    private final PartyService partyService;

    @PostMapping
    public ResponseEntity<PartyResponse> createParty(@RequestBody @Valid PartyRequest request, @AuthenticationPrincipal User host){
        Party party = partyService.save(request, host);
        PartyResponse response = PartyMapper.toPartyResponse(party);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/public")
    public ResponseEntity<List<PartyResponse>> listPublicParties(){
        List<PartyResponse> parties = partyService.listPublicParties().stream()
                .map(PartyMapper::toPartyResponse)
                .toList();
        return ResponseEntity.ok(parties);
    }
}
