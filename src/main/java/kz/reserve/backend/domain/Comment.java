package kz.reserve.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "admin"})
    @ManyToOne(fetch = FetchType.EAGER)
    private Restaurant restaurant;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "restaurant"})
    @ManyToOne(fetch = FetchType.EAGER)
    private User client;
    private int star;
    private String text;

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
