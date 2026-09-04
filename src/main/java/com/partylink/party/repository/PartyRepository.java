package com.partylink.party.repository;

import com.partylink.party.entity.Party;
import com.partylink.party.entity.PartyVisibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PartyRepository extends JpaRepository<Party, UUID> {

    List<Party> findByVisibility(PartyVisibility visibility);

    List<Party> findByHostId(UUID hostId);

}
