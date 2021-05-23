package kz.reserve.backend.controller;

import kz.reserve.backend.domain.Order;
import kz.reserve.backend.repository.OrderRepository;
import kz.reserve.backend.service.OrderClientService;
import kz.reserve.backend.service.OrderService;
import kz.reserve.backend.service.StripeService;
import kz.reserve.backend.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.Min;


@Controller
public class PaymentController {

    @Value("${stripe.key.public}")
    private String API_PUBLIC_KEY;

    private StripeService stripeService;

    private OrderClientService orderClientService;

    @Autowired


    public PaymentController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @GetMapping("/")
    public String homepage() {
        return "homepage";
    }



    @GetMapping("/api/charge")
    public String chargePage(Model model) {
        model.addAttribute("stripePublicKey", API_PUBLIC_KEY);
        return "charge";
    }

    @PostMapping("/api/create-charge")
    public @ResponseBody Response createCharge(String email, String token ) { //Long id


        if (token == null) {
            return new Response(false, "Stripe payment token is missing. please try again later.");
        }
       //Order _order = orderRepository.getOne(id);

        String chargeId = stripeService.createCharge(email, token,  509000);

        if (chargeId == null) {
            return new Response(false, "An error accurred while trying to charge.");
        }

        // You may want to store charge id along with order information

        return new Response(true, "Success your charge id is " + chargeId);
    }


}
