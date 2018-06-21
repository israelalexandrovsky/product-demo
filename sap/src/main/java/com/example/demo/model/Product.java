package com.example.demo.model;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;

public class Product implements Serializable{

    private final static long serialVersionUID = 1L;

    private long id;
    private String name;
    Category category;
    private double price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product(String name,Category category,double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Product() {
    }
}
