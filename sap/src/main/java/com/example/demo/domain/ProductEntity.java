package com.example.demo.domain;

import javax.persistence.*;

@Entity
@Table(name="PRODUCTS")
public class ProductEntity extends AbstractEntity{

    @Id
    @GeneratedValue
    @Column(name="ID", precision=22)
    private long id;

    @Column(name="NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name="CATEGORY_ID", insertable=true, updatable=true)
    CategoryEntity category;

    @Column(name="PRICE")
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

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", price=" + price +
                '}';
    }

    public ProductEntity() {
    }

    public ProductEntity(String name, CategoryEntity category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }
}
