package model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "food_unit_types")
public class FoodUnitType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private Integer name;

    @Column(name = "flag")
    private Integer flag;

    @OneToMany(mappedBy = "unit")
    private Set<FoodInventory> foodInventories = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Set<FoodInventory> getFoodInventories() {
        return foodInventories;
    }

    public void setFoodInventories(Set<FoodInventory> foodInventories) {
        this.foodInventories = foodInventories;
    }

}