package com.emin.yuce.learning.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;


@Entity
public class Product {

    @Id
    @GeneratedValue
    private int id;
    private String description;
    private BigDecimal price;
    private String imageUrl;

    public Product() {
    }

    public Product(int id, String description, BigDecimal price, String imageUrl) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
