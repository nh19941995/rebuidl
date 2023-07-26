package model;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "last_name", nullable = false, length = 150)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private Instant dateOfBirth;

    @Column(name = "address", nullable = false, length = 300)
    private String address;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "username", nullable = false, length = 150)
    private String username;

    @Column(name = "password", nullable = false, length = 150)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "permission", nullable = false)
    private Permission permission;

    @Column(name = "email", nullable = false, length = 250)
    private String email;

    @Column(name = "date_creat", nullable = false)
    private Instant dateCreat;

    @Column(name = "date_update", nullable = false)
    private Instant dateUpdate;

    @Column(name = "flag", nullable = false)
    private Integer flag;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Instant dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Person() {
    }

    public Person(String name, String lastName, Instant dateOfBirth, String address, String phone, String username, String password, Permission permission, String email, Instant dateCreat, Instant dateUpdate, Integer flag) {
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.permission = permission;
        this.email = email;
        this.dateCreat = dateCreat;
        this.dateUpdate = dateUpdate;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", permission=" + permission +
                ", email='" + email + '\'' +
                ", dateCreat=" + dateCreat +
                ", dateUpdate=" + dateUpdate +
                ", flag=" + flag +
                '}';
    }
}