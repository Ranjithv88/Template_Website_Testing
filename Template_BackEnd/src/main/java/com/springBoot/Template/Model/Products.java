package com.springBoot.Template.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.*;

// products Model for Table
@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // this is Image Column
    @NotNull(message = " Image is Mandatory ......! ")
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;

    @Column(unique=true)
    @NotBlank(message = " Name is Mandatory ......! ")
    @Size(min = 2,message = " Name Must Be Between 2 & 30 Characters .......! ", max = 40)
    private String name;

    @NotNull(message = " Price is Mandatory ......! ")
    @Positive(message = " Price Must Be Greater Than Zero ......! ")
    private int price;

    private Date updateOn;

    // ManyToMany For cart to Products
    @ManyToMany(mappedBy = "products",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Cart> carts = new LinkedList<>();

    // Getter and Setter For Private Variables
    public Products() {
        this.carts = new LinkedList<>();
    }

    public Products(long id, byte[] image, String name, int price, Date updateOn) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.updateOn = updateOn;
        this.carts = new LinkedList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotNull(message = " Image is Mandatory ......! ")
    public byte[] getImage() {
        return image;
    }

    public void setImage(@NotNull(message = " Image is Mandatory ......! ") byte[] image) {
        this.image = image;
    }

    public @NotBlank(message = " Name is Mandatory ......! ") @Size(min = 2, message = " Name Must Be Between 2 & 30 Characters .......! ", max = 40) String getName() {
        return name;
    }

    public void setName(@NotBlank(message = " Name is Mandatory ......! ") @Size(min = 2, message = " Name Must Be Between 2 & 30 Characters .......! ", max = 40) String name) {
        this.name = name;
    }

    @NotNull(message = " Price is Mandatory ......! ")
    @Positive(message = " Price Must Be Greater Than Zero ......! ")
    public int getPrice() {
        return price;
    }

    public void setPrice(@NotNull(message = " Price is Mandatory ......! ") @Positive(message = " Price Must Be Greater Than Zero ......! ") int price) {
        this.price = price;
    }

    public Date getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(Date updateOn) {
        this.updateOn = updateOn;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

}

