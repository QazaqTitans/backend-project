package kz.reserve.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "reserved_table")
public class ReservedTable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String imageSrc;
    private int reservePrice;
    @Enumerated(EnumType.STRING)
    private Position position;
    private boolean isForChildren;
    private Integer personCount;
//    private boolean isTapchan;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Restaurant restaurant;

    public ReservedTable() {}

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

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public int getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(int reservePrice) {
        this.reservePrice = reservePrice;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isForChildren() {
        return isForChildren;
    }

    public void setForChildren(boolean forChildren) {
        isForChildren = forChildren;
    }

//    public boolean isTapchan() {
//        return isTapchan;
//    }
//
//    public void setTapchan(boolean tapchan) {
//        isTapchan = tapchan;
//    }


    public Integer getPersonCount() {
        return personCount;
    }

    public void setPersonCount(Integer personCount) {
        this.personCount = personCount;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
