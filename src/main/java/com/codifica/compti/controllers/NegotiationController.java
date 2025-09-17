package com.codifica.compti.controllers;

import com.codifica.compti.models.negotiation.Negotiation;
import com.codifica.compti.services.NegotiationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/negotiations")
@RequiredArgsConstructor
public class NegotiationController {

    private final NegotiationService negotiationService;

    @GetMapping
    public Page<Negotiation> getNegotiations(
            @RequestParam(required = false) Long buyerId,
            @RequestParam(required = false) Long productId,
            @RequestParam(required = false) Boolean completed,
            @RequestParam(required = false) Integer rating,
            Pageable pageable
    ) {
        return negotiationService.findNegotiations(buyerId, productId, completed, rating, pageable);
    }

    @GetMapping("/{id}")
    public Negotiation getNegotiation(@PathVariable Long id) {
        return negotiationService.findById(id);
    }

    @PostMapping
    public Negotiation createNegotiation(@RequestBody Negotiation negotiation) {
        return negotiationService.save(negotiation);
    }

    @PutMapping("/{id}")
    public Negotiation updateNegotiation(@PathVariable Long id, @RequestBody Negotiation negotiation) {
        return negotiationService.update(id, negotiation);
    }

    @DeleteMapping("/{id}")
    public void deleteNegotiation(@PathVariable Long id) {
        negotiationService.delete(id);
    }
}