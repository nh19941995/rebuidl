package model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "food_types")
public class FoodType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "type_name", nullable = false)
    private Integer typeName;

    @Column(name = "flag", nullable = false)
    private Integer flag;

    @OneToMany(mappedBy = "foodType")
    private Set<FoodInventory> foodInventories = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeName() {
        return typeName;
    }

    public void setTypeName(Integer typeName) {
        this.typeName = typeName;
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