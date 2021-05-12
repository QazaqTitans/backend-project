package kz.reserve.backend.service;

import kz.reserve.backend.domain.*;
import kz.reserve.backend.payload.request.IOrderRequest;
import kz.reserve.backend.payload.request.OrderRequest;
import kz.reserve.backend.payload.response.DiscountResponse;
import kz.reserve.backend.payload.response.MessageResponse;
import kz.reserve.backend.payload.response.OrderResponse;
import kz.reserve.backend.repository.OrderRepository;
import kz.reserve.backend.repository.RestaurantRepository;
import kz.reserve.backend.repository.TableRepository;
import kz.reserve.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderClientService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ServiceUtils serviceUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> makeOrderNewUser(OrderRequest orderRequest) {
//        checking if there is empty table іздеу керек осы уақытта бос па деп
        ReservedTable reservedTable = tableRepository.getOne((long)25);
//        creating new user
        User user = createUser(orderRequest);

//        creating order
        makeOrder(orderRequest, user, reservedTable);

        return ResponseEntity.ok(new OrderResponse(reservedTable));
    }

    public void makeOrder(IOrderRequest orderRequest, User user, ReservedTable reservedTable) {
        Order order = new Order();
        Restaurant restaurant = restaurantRepository.getOne(orderRequest.getRestaurantId());

        order.setClient(user);
        order.setStartTime(orderRequest.getStartTime());
        order.setEndTime(orderRequest.getEndTime());
        order.setFeatures(orderRequest.getFeatures());
        order.setPersonCount(orderRequest.getPersonCount());
        order.setState(OrderState.pending);
        order.setRestaurant(restaurant);

        Set<ReservedTable> reservedTables = new HashSet<>();
        reservedTables.add(reservedTable);

        order.setReservedTables(reservedTables);

        orderRepository.save(order);
    }

    public User createUser(OrderRequest orderRequest) {
        Optional<User> byEmail = userRepository.findByEmail(orderRequest.getEmail());

        if (byEmail.isPresent())
            return byEmail.get();

        User user = new User();

        String password = serviceUtils.generatePassword(6);
        Set<Role> roles = new HashSet<>();
        roles.add(Role.client);

        user.setName(orderRequest.getName());
        user.setPhoneNumber(orderRequest.getPhone());
        user.setEmail(orderRequest.getEmail());
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roles);

        userRepository.save(user);

        String message = String.format("Hello %s! \n" +
                "Welcome to our site.\n" +
                "Your login: %s\n" +
                "Your password: %s\n", user.getName(), user.getEmail(), password);

        serviceUtils.sendMessageToUser(user, "Registration", message);

        return user;
    }
}
