package kz.reserve.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "restaurant"})
    @ManyToOne(fetch = FetchType.EAGER)
    private User client;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "admin"})
    @ManyToOne(fetch = FetchType.EAGER)
    private Restaurant restaurant;

    private Integer personCount;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String features;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "table_id"))
    private Set<ReservedTable> reservedTables;

    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id"))
    private Set<Meal> meals;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Integer getPersonCount() {
        return personCount;
    }

    public void setPersonCount(Integer personCount) {
        this.personCount = personCount;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Set<ReservedTable> getReservedTables() {
        return reservedTables;
    }

    public void setReservedTables(Set<ReservedTable> reservedTables) {
        this.reservedTables = reservedTables;
    }

    public Set<Meal> getMeals() {
        return meals;
    }

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }
    public double orderprice() {
        double price = 0;
        if (!meals.isEmpty()) {
            for (Iterator<Meal> meal = meals.iterator(); meal.hasNext();) {
                Meal m=meal.next();
                price=price+m.getPrice();
            }
        }
        return price;
    }


}

