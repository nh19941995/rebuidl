package model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "table_list")
public class TableList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "seating_capacity", nullable = false)
    private Integer seatingCapacity;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "type", nullable = false)
    private TableType type;

    @Column(name = "flag", nullable = false)
    private Integer flag;

    @OneToMany(mappedBy = "table")
    private Set<Booking> bookings = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(Integer seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public TableType getType() {
        return type;
    }

    public void setType(TableType type) {
        this.type = type;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Set<Booking> getBookings() {
        return bookings;
    }


    public void setBookings(Set<Booking> bookings) {
        this.bookings = bookings;
    }

    public TableList() {
    }

    public TableList(Integer seatingCapacity, TableType type, Integer flag, Set<Booking> bookings) {
        this.seatingCapacity = seatingCapacity;
        this.type = type;
        this.flag = flag;
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "TableList{" +
                "id=" + id +
                ", seatingCapacity=" + seatingCapacity +
                ", type=" + type.toString() +
                ", flag=" + flag +
                ", bookings=" + bookings.toString() +
                '}';
    }
}