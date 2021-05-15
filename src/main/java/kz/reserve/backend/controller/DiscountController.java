package kz.reserve.backend.controller;

import kz.reserve.backend.payload.request.DiscountRequest;
import kz.reserve.backend.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/discount")
@PreAuthorize("hasAuthority('restaurantAdmin')")
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    @GetMapping()
    public ResponseEntity<?> getDiscounts(){return discountService.getDiscounts();}

    @PostMapping()
    public ResponseEntity<?> addDiscount(@Valid @ModelAttribute DiscountRequest discountRequest) {
        return discountService.addDiscount(discountRequest);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDiscount(@Valid @Min(1) @PathVariable Long id, @Valid @ModelAttribute DiscountRequest discountRequest) {
        return discountService.updateDiscount(id, discountRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDiscount(@Valid @Min(1) @PathVariable Long id) {
        return discountService.deleteDiscount(id);
    }


}

