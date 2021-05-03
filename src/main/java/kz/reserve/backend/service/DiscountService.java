package kz.reserve.backend.service;

import kz.reserve.backend.domain.Discount;
import kz.reserve.backend.domain.Restaurant;
import kz.reserve.backend.domain.User;
import kz.reserve.backend.payload.request.DiscountRequest;
import kz.reserve.backend.payload.response.DiscountResponse;
import kz.reserve.backend.payload.response.MessageResponse;
import kz.reserve.backend.repository.DiscountRepository;
import kz.reserve.backend.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DiscountService {
    @Autowired
    private ServiceUtils serviceUtils;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private DiscountRepository discountRepository;
    public ResponseEntity<?> getDiscounts() {
        List<Discount> discountList = discountRepository.findAll();
        return ResponseEntity.ok(new DiscountResponse(discountList));
    }
    public ResponseEntity<?> addDiscount(DiscountRequest discountRequest) {
        try {
            Discount discount = new Discount();

            User user = serviceUtils.getPrincipal();

            discount.setRestaurant(user.getRestaurant());
            discountCreator(discountRequest, discount);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("Success"));
    }
    private void discountCreator(DiscountRequest discountRequest, Discount discount) {
        Restaurant restaurant = restaurantRepository.getOne(discountRequest.getRestaurant_id());
        discount.setRestaurant(restaurant);
        discount.setDescription(discountRequest.getDescription());
        discount.setPercentage(discountRequest.getPercentage());
        discountRepository.save(discount);
    }
    public ResponseEntity<?> updateDiscount(Long discountId, DiscountRequest discountRequest) {
        try {
            Discount discount = discountRepository.getOne(discountId);
            discountCreator(discountRequest,discount);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("Success"));
    }
    public ResponseEntity<?> deleteDiscount(Long discountId) {
        try {
            discountRepository.deleteById(discountId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("Success"));
    }

}
