package com.pie.planingjakartaee.dao.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="user_id")
    private int id;
    @Column(length = 50, nullable=false)
    private String pseudo;
    @Column(unique = true, length = 75, nullable=false)
    private String email;
    @Column(name="last_name", length = 50, nullable=false)
    private String lastName;
    @Column(name="first_name", length = 50, nullable=false)
    private String firstName;
    @Column(length = 50)
    private String avatar;
    @Temporal(TemporalType.DATE)
    @Column(nullable=false)
    private LocalDate birthdate;
    @Column(length = 15, nullable=false)
    private String phone;
    @Column(nullable=false)
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    //, nullable=false,  columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable=false
    //@CreationTimestamp
    private LocalDateTime createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    //,  columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false
    //@UpdateTimestamp
    private LocalDateTime modifyDate;

    @Column(nullable=false)
    private boolean activate;
    @Column(length = 100, nullable=false)
    private String street;
    @Column(length = 50, nullable=false)
    private String city;
    @Column(length = 50, nullable=false)
    private String country;
    @Column(length = 32, nullable=false)
    private String zip;

    @JoinColumn(name="role_id", nullable = false)
    @ManyToOne
    private Role role;

    public User(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
