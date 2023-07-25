package model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "dish", uniqueConstraints = {@UniqueConstraint(columnNames = "dish_name")})
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "dish_name", nullable = false, length = 500, unique = true)
    private String dishName;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "date_creat")
    private Instant dateCreat;

    @Column(name = "date_update")
    private Instant dateUpdate;

    @Column(name = "flag", nullable = false)
    private Integer flag;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
    })
    @JoinColumn(name = "type_id", nullable = false)
    private DishType type;

    @OneToMany(mappedBy = "dish")
    private Set<Menu> menus = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Instant getDateCreat() {
        return dateCreat;
    }

    public void setDateCreat(Instant dateCreat) {
        this.dateCreat = dateCreat;
    }

    public Instant getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Instant dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public DishType getType() {
        return type;
    }

    public void setType(DishType type) {
        this.type = type;
    }


    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public Dish() {
    }

    public Dish(String dishName, Integer price, Instant dateCreat, Instant dateUpdate, Integer flag, DishType type, Set<Menu> menus) {
        this.dishName = dishName;
        this.price = price;
        this.dateCreat = dateCreat;
        this.dateUpdate = dateUpdate;
        this.flag = flag;
        this.type = type;
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", dishName='" + dishName + '\'' +
                ", price=" + price +
                ", dateCreat=" + dateCreat +
                ", dateUpdate=" + dateUpdate +
                ", flag=" + flag +
                ", type=" + type +
                ", menus=" + menus +
                '}';
    }
    public void add(DishType dishType){
        if (dishType.getType() == null){
            this.setType(dishType);
        }
        this.getType().add(this);
    }
}