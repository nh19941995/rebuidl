package model;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "bookings_info")
public class BookingsInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "info", nullable = false, length = 500)
    private String info;

    @Column(name = "date_creat", nullable = false)
    private Instant dateCreat;

    @Column(name = "start", nullable = false)
    private Instant start;

    @Column(name = "end", nullable = false)
    private Instant end;

    @Column(name = "deposit", nullable = true)
    private Double deposit;

    @Column(name = "flag", nullable = true)
    private Integer flag;

    @OneToMany(mappedBy = "info")
    private Set<Booking> bookings = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Instant getDateCreat() {
        return dateCreat;
    }

    public void setDateCreat(Instant dateCreat) {
        this.dateCreat = dateCreat;
    }

    public Instant getStart() {
        return start;
    }

    public void setStart(Instant start) {
        this.start = start;
    }

    public Instant getEnd() {
        return end;
    }

    public void setEnd(Instant end) {
        this.end = end;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
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

    public BookingsInfo() {
    }

    public BookingsInfo(Person person, String info, Instant dateCreat, Instant start, Instant end, Double deposit, Integer flag, Set<Booking> bookings) {
        this.person = person;
        this.info = info;
        this.dateCreat = dateCreat;
        this.start = start;
        this.end = end;
        this.deposit = deposit;
        this.flag = flag;
        this.bookings = bookings;
    }



    @Override
    public String toString() {
        return "BookingsInfo{" +
                "id=" + id +
                ", person=" + person +
                ", info='" + info + '\'' +
                ", dateCreat=" + dateCreat +
                ", start=" + start +
                ", end=" + end +
                ", deposit=" + deposit +
                ", flag=" + flag +
                ", bookings=" + bookings +
                '}';
    }
//    public void add(Dish dish){
//        if (dishes == null){
//            dishes = new LinkedHashSet<>();
//        }
//        dishes.add(dish);
//        dish.setType(this);
//    }
    public void add(Booking booking){
        if (bookings == null){
            bookings = new LinkedHashSet<>();
        }
        bookings.add(booking);
        booking.setInfo(this);
    }
}