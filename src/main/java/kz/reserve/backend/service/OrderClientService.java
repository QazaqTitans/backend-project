package kz.reserve.backend.service;

import kz.reserve.backend.domain.*;
import kz.reserve.backend.payload.request.IOrderRequest;
import kz.reserve.backend.payload.request.OrderMealRequest;
import kz.reserve.backend.payload.request.OrderRequest;
import kz.reserve.backend.payload.request.OrderUserRequest;
import kz.reserve.backend.payload.response.DiscountResponse;
import kz.reserve.backend.payload.response.MessageResponse;
import kz.reserve.backend.payload.response.OrderListResponse;
import kz.reserve.backend.payload.response.OrderResponse;
import kz.reserve.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    @Autowired
    private MealRepository mealRepository;

    public ResponseEntity<?> makeOrderExistUser(OrderUserRequest orderRequest) {

        try {
            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime startTime = LocalDateTime.parse(orderRequest.getStartTime(), dateTimeFormat);
            LocalDateTime endTime = LocalDateTime.parse(orderRequest.getEndTime(), dateTimeFormat);

            if (startTime.isAfter(endTime))
                return ResponseEntity.badRequest().body(new MessageResponse("Time is not correct"));


//        checking if there is empty table іздеу керек осы уақытта бос па деп
            ReservedTable reservedTable = getEmptyTable(orderRequest.getPersonCount(), startTime,
                    endTime, orderRequest.getRestaurantId(), orderRequest.getPosition(), orderRequest.getChildren());

            if (reservedTable == null)
                return ResponseEntity.ok(new OrderResponse(null, null));

            User user = serviceUtils.getPrincipal();

            Order order = makeOrder(orderRequest, user, reservedTable, startTime, endTime);

            return ResponseEntity.ok(new OrderResponse(reservedTable, order));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    public ResponseEntity<?> makeOrderNewUser(OrderRequest orderRequest) {
        try {
            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime startTime = LocalDateTime.parse(orderRequest.getStartTime(), dateTimeFormat);
            LocalDateTime endTime = LocalDateTime.parse(orderRequest.getEndTime(), dateTimeFormat);
//        checking if there is empty table іздеу керек осы уақытта бос па деп
            ReservedTable reservedTable = getEmptyTable(orderRequest.getPersonCount(), startTime,
                    endTime, orderRequest.getRestaurantId(), orderRequest.getPosition(), orderRequest.getChildren());

            if (reservedTable == null)
                return ResponseEntity.ok(new OrderResponse(null, null));
//        creating new user
            User user = createUser(orderRequest);

//        creating order
            Order order = makeOrder(orderRequest, user, reservedTable, startTime, endTime);

            return ResponseEntity.ok(new OrderResponse(reservedTable, order));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    public Order makeOrder(IOrderRequest orderRequest, User user, ReservedTable reservedTable, LocalDateTime startTime,
                           LocalDateTime endTime) {
        Order order = new Order();
        Restaurant restaurant = restaurantRepository.getOne(orderRequest.getRestaurantId());

        order.setClient(user);
        order.setStartTime(startTime);
        order.setEndTime(endTime);
        order.setFeatures(orderRequest.getFeatures());
        order.setPersonCount(orderRequest.getPersonCount());
        order.setState(OrderState.pending);
        order.setRestaurant(restaurant);

        Set<ReservedTable> reservedTables = new HashSet<>();
        reservedTables.add(reservedTable);

        order.setReservedTables(reservedTables);

        orderRepository.save(order);

        return order;
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
                "Welcome to our site. To see data about your meal please sign in.\n" +
                "Your login: %s\n" +
                "Your password: %s\n", user.getName(), user.getEmail(), password);

        serviceUtils.sendMessageToUser(user, "Registration", message);

        return user;
    }

    private ReservedTable getEmptyTable(Integer personCount, LocalDateTime startTime, LocalDateTime endTime,
                                        Long restaurantId, Position position, Boolean children) {
        return tableRepository.findTableByConfigurations(startTime, endTime, personCount, position.name(), children, restaurantId);
    }

    public ResponseEntity<?> addMealForOrder(Long orderId, OrderMealRequest orderMealRequest) {
        try {
            Order order = orderRepository.getOne(orderId);
            Set<Meal> meals = new HashSet<>();

            for (Long mealId : orderMealRequest.getMealIds()) {
                Meal meal = mealRepository.getOne(mealId);
                meals.add(meal);
            }


            order.setMeals(meals);
            order.orderprice();

            orderRepository.save(order);

            return ResponseEntity.ok(new OrderResponse(null, order));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    public ResponseEntity<?> getOrders() {
        User user = serviceUtils.getPrincipal();
        List<Order> orderList = orderRepository.findAllByClient(user);

        return ResponseEntity.ok(new OrderListResponse(orderList));
    }
}
