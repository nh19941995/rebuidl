package model;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "dish_type", uniqueConstraints = {@UniqueConstraint(columnNames = "type")})
public class DishType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "type", nullable = false, length = 500, unique = true)
    private String type;

    @Column(name = "flag", nullable = false)
    private Integer flag;

    @OneToMany(mappedBy = "type")
    private Set<Dish> dishes = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }


    public DishType() {
    }

    public DishType(String type, Integer flag, Set<Dish> dishes) {
        this.type = type;
        this.flag = flag;
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "DishType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", flag=" + flag +
                ", dishes="  +
                '}';
    }

    public void add(Dish dish){
        if (dishes == null){
            dishes = new LinkedHashSet<>();
        }
        dishes.add(dish);
        dish.setType(this);
    }
}