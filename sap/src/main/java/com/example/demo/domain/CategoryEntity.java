package com.example.demo.domain;

import javax.persistence.*;

@Entity
@Table(name="CATEGORY")
public class CategoryEntity extends AbstractEntity{

    @Id
    @GeneratedValue
    @Column(name="CATEGORY_ID", precision=22)
    private long category_id;

    @Column(name="NAME")
    private String name;

    @Column(name="ADDITIONAL_DATA")
    private String additional_data;

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdditional_data() {
        return additional_data;
    }

    public void setAdditional_data(String additional_data) {
        this.additional_data = additional_data;
    }

    public CategoryEntity(String name) {
        this.name = name;
    }

    public CategoryEntity() {
    }

    @Override
    public String toString() {
        return "Category{" +
                "category_id=" + category_id +
                ", name='" + name + '\'' +
                ", additional_data='" + additional_data + '\'' +
                '}';
    }
}
