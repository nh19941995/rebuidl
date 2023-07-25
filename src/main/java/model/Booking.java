package model;

import jakarta.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "info_id", nullable = false)
    private BookingsInfo info;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "table_id", nullable = false)
    private TableList table;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "menu_name_id", nullable = false)
    private MenuName menuName;

    @Column(name = "flag", nullable = false)
    private Integer flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BookingsInfo getInfo() {
        return info;
    }

    public void setInfo(BookingsInfo info) {
        this.info = info;
    }

    public TableList getTable() {
        return table;
    }

    public void setTable(TableList table) {
        this.table = table;
    }

    public MenuName getMenuName() {
        return menuName;
    }

    public void setMenuName(MenuName menuName) {
        this.menuName = menuName;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Booking() {
    }

    public Booking(BookingsInfo info, TableList table, MenuName menuName, Integer flag) {
        this.info = info;
        this.table = table;
        this.menuName = menuName;
        this.flag = flag;
    }



    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", info=" + info +
                ", table=" + table.getId() +
                ", menuName=" + menuName.getName() +
                ", flag=" + flag +
                '}';
    }

//    public void add(DishType dishType){
//        if (dishType.getType() == null){
//            this.setType(dishType);
//        }
//        this.getType().add(this);
//    }
    public void add(BookingsInfo info){
        if (this.getInfo() == null){
            this.setInfo(info);
        }
        this.getInfo().add(this);
    }

}