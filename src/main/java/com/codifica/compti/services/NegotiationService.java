package com.codifica.compti.services;

import com.codifica.compti.models.negotiation.Negotiation;
import com.codifica.compti.repositories.NegotiationRepository;
import com.codifica.compti.specifications.NegotiationSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NegotiationService {

    private final NegotiationRepository negotiationRepository;

    // 🔍 Buscar negociações com filtros dinâmicos
    public Page<Negotiation> findNegotiations(Long buyerId, Long productId, Boolean completed, Integer rating, Pageable pageable) {
        Specification<Negotiation> spec = (root, query, cb) -> cb.conjunction();

        if (buyerId != null) {
            spec = spec.and(NegotiationSpecifications.hasBuyerId(buyerId));
        }
        if (productId != null) {
            spec = spec.and(NegotiationSpecifications.hasProductId(productId));
        }
        if (completed != null) {
            spec = spec.and(NegotiationSpecifications.isCompleted(completed));
        }
        if (rating != null) {
            spec = spec.and(NegotiationSpecifications.hasRating(rating));
        }

        return negotiationRepository.findAll(spec, pageable);
    }

    // ➕ Criar negociação
    public Negotiation save(Negotiation negotiation) {
        return negotiationRepository.save(negotiation);
    }

    // 🔍 Buscar por ID
    public Negotiation findById(Long id) {
        return negotiationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Negociação não encontrada com ID: " + id));
    }

    // ✏️ Atualizar
    public Negotiation update(Long id, Negotiation updatedNegotiation) {
        Negotiation negotiation = findById(id);
        negotiation.setBuyer(updatedNegotiation.getBuyer());
        negotiation.setProduct(updatedNegotiation.getProduct());
        negotiation.setClickDate(updatedNegotiation.getClickDate());
        negotiation.setCompleted(updatedNegotiation.getCompleted());
        negotiation.setRating(updatedNegotiation.getRating());
        negotiation.setComment(updatedNegotiation.getComment());
        return negotiationRepository.save(negotiation);
    }

    // 🗑️ Deletar
    public void delete(Long id) {
        negotiationRepository.delete(findById(id));
    }
}