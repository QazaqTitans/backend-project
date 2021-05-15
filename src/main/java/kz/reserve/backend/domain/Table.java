package kz.reserve.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "reserved_table")
public class Table {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String image_src;
    private int reserve_price;
    @Enumerated(EnumType.STRING)
    private Position position;
    private boolean is_for_children;
    private boolean is_tapchan;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Restaurant restaurant;

    public boolean isIs_for_children() {
        return is_for_children;
    }

    public void setIs_for_children(boolean is_for_children) {
        this.is_for_children = is_for_children;
    }

    public boolean isIs_tapchan() {
        return is_tapchan;
    }

    public void setIs_tapchan(boolean is_tapchan) {
        this.is_tapchan = is_tapchan;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Table() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_src() {
        return image_src;
    }

    public void setImage_src(String image_src) {
        this.image_src = image_src;
    }

    public int getReserve_price() {
        return reserve_price;
    }

    public void setReserve_price(int reserve_price) {
        this.reserve_price = reserve_price;
    }
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }



}
