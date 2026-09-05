package com.partylink.party.service;

import com.partylink.party.dto.PartyRequest;
import com.partylink.party.entity.Party;
import com.partylink.party.entity.PartyVisibility;
import com.partylink.party.mapper.PartyMapper;
import com.partylink.party.repository.PartyRepository;
import com.partylink.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PartyService {

    private final PartyRepository partyRepository;

    @Transactional
    public Party save(PartyRequest request, User host){
        Party party = PartyMapper.toParty(request, host);

        return partyRepository.save(party);
    }

    @Transactional(readOnly = true)
    public List<Party> listPublicParties(){
        return partyRepository.findByVisibility(PartyVisibility.PUBLIC);
    }

    @Transactional(readOnly = true)
    public List<Party> listPartiesByHost(UUID hostId){
        return partyRepository.findByHostId(hostId);
    }
}
