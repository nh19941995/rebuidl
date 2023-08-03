package model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "type", nullable = false)
    private TransactionsType type;

    @Column(name = "quantity", nullable = false)
    private Float quantity;

    @Lob
    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "date_creat", nullable = false)
    private Instant dateCreat;

    @Column(name = "date_update", nullable = false)
    private Instant dateUpdate;

    @Column(name = "flag", nullable = false)
    private Integer flag;

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

    public TransactionsType getType() {
        return type;
    }

    public void setType(TransactionsType type) {
        this.type = type;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public Transaction() {
    }

    public Transaction(Person person, TransactionsType type, Float quantity, String comment, Instant dateCreat, Instant dateUpdate, Integer flag) {
        this.person = person;
        this.type = type;
        this.quantity = quantity;
        this.comment = comment;
        this.dateCreat = dateCreat;
        this.dateUpdate = dateUpdate;
        this.flag = flag;
    }

    public void setVisible(boolean b) {
    }
}