package com.springBoot.Template.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.springBoot.Template.Annotations.BooleanToStringConverter;
import com.springBoot.Template.Annotations.Decimal;
import com.springBoot.Template.Model.Enum.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Date;
import java.util.List;

// User Madel For User Table
@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique=true)
    @NotBlank(message = "UserName is Mandatory ......! ")
    @Size(min = 2, message = "UserName Must Be Between 2 & 30 Characters .......! ", max = 40)
    private String userName;

    @NotBlank(message = "Password is Mandatory ......! ")
    @Size(min = 8, message = "Too Weak .......! ")
    private String password;

    // this is Age Column
    @Decimal
    private int age;

    @NotBlank(message = "Email is Mandatory ......! ")
    @Email(message = "Email is Invalid .......! ")
    private String email;

    // this for Email Number Status Stored as a Boolean value
    @Convert(converter = BooleanToStringConverter.class)
    private boolean emailStatus;

    @NotNull(message = "Phone Number is Mandatory ......! ")
    @Size(min = 10, max = 10, message = "Phone Number is Invalid ......! ")
    private String phoneNumber;

    // this for Phone Number Status Stored as a Boolean value
    @Convert(converter = BooleanToStringConverter.class)
    private boolean phoneNumberStatus;

    private Date createdOn;

    private Date modifyingDate;

    // this is Role for Enum Stored as a String
    @Enumerated(EnumType.STRING)
    private Role role;

    // OneToOne RelationShip For User to Cart
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    @JsonManagedReference
    private Cart cart;


    // Default constructor For User
    public User() {
    }

    // All-arguments constructor For User
    public User(long id, String userName, String password, int age, String email, boolean emailStatus,
                String phoneNumber, boolean phoneNumberStatus, Date createdOn, Date modifyingDate,
                Role role, Cart cart) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.email = email;
        this.emailStatus = emailStatus;
        this.phoneNumber = phoneNumber;
        this.phoneNumberStatus = phoneNumberStatus;
        this.createdOn = createdOn;
        this.modifyingDate = modifyingDate;
        this.role = role;
        this.cart = cart;
    }


    // Getter and Setter For Private Variables
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(boolean emailStatus) {
        this.emailStatus = emailStatus;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isPhoneNumberStatus() {
        return phoneNumberStatus;
    }

    public void setPhoneNumberStatus(boolean phoneNumberStatus) {
        this.phoneNumberStatus = phoneNumberStatus;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifyingDate() {
        return modifyingDate;
    }

    public void setModifyingDate(Date modifyingDate) {
        this.modifyingDate = modifyingDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }


    // Implement Method for User Details
    @Override
    public String getUsername() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

