package kz.reserve.backend.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "restaurant"})
    @OneToOne(fetch = FetchType.EAGER)
    private User admin;

    private String mapCoordination;

    @Column(columnDefinition="TEXT")
    private String description;

    private String address;

    public String imageSrc;

    public Integer minPrice;

    public Integer maxPrice;

    public Restaurant() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public String getMapCoordination() {
        return mapCoordination;
    }

    public void setMapCoordination(String mapCoordination) {
        this.mapCoordination = mapCoordination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }
}
