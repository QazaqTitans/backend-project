package kz.reserve.backend.domain;


import javax.persistence.*;

@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int admin_id;
    private Long map_coordination;


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

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public Long getMap_coordination() {
        return map_coordination;
    }

    public void setMap_coordination(Long map_coordination) {
        this.map_coordination = map_coordination;
    }


    public Restaurant(){}





}
