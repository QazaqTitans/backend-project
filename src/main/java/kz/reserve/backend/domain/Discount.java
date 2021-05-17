package kz.reserve.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Discount {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private double percentage;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Restaurant restaurant;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Meal meal;

    public Discount() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
}
