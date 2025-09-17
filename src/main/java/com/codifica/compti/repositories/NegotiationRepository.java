package com.codifica.compti.repositories;

import com.codifica.compti.models.negotiation.Negotiation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NegotiationRepository extends JpaRepository<Negotiation, Long>, JpaSpecificationExecutor<Negotiation> {
}