package com.codifica.compti.controllers;

import com.codifica.compti.models.sellerrating.SellerRating;
import com.codifica.compti.services.SellerRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller-ratings")
@RequiredArgsConstructor
public class SellerRatingController {

    private final SellerRatingService sellerRatingService;

    @GetMapping
    public Page<SellerRating> getSellerRatings(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Double maxRating,
            Pageable pageable
    ) {
        return sellerRatingService.findSellerRatings(userId, minRating, maxRating, pageable);
    }

    @GetMapping("/{id}")
    public SellerRating getSellerRating(@PathVariable Long id) {
        return sellerRatingService.findById(id);
    }

    @PostMapping
    public SellerRating createSellerRating(@RequestBody SellerRating sellerRating) {
        return sellerRatingService.save(sellerRating);
    }

    @PutMapping("/{id}")
    public SellerRating updateSellerRating(@PathVariable Long id, @RequestBody SellerRating sellerRating) {
        return sellerRatingService.update(id, sellerRating);
    }

    @DeleteMapping("/{id}")
    public void deleteSellerRating(@PathVariable Long id) {
        sellerRatingService.delete(id);
    }
}