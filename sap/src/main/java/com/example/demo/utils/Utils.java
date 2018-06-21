package com.example.demo.utils;

import com.example.demo.dao.ProductDao;
import com.example.demo.domain.CategoryEntity;
import com.example.demo.domain.ProductEntity;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class Utils{

    @Autowired
    ProductDao productDao;

    public static List<Product> fromDomainList(List<ProductEntity> productEntityList){
        List<Product> productList = null;
        if(productEntityList !=null){
            productList = new ArrayList<Product>();
            for (ProductEntity productEntity:productEntityList) {
               productList.add(fromDomain(productEntity));
            }
        }
        return productList;
    }

    public static Product fromDomain(ProductEntity productEntity){
        Product product = null;
        if(productEntity !=null){
            Category category = new Category(productEntity.getCategory().getName(),productEntity.getCategory().getAdditional_data());
            category.setId(productEntity.getCategory().getCategory_id());
            product = new Product(productEntity.getName(),category,productEntity.getPrice());
            product.setId(productEntity.getId());
        }
        return product;
    }


    public static List<ProductEntity> toDomainList(List<Product> productList){
        List<ProductEntity> productEntityList = null;
        if(productList !=null){
            productEntityList = new ArrayList<ProductEntity>();
            for (Product product:productList) {
                productEntityList.add(toDomain(product));
            }
        }
        return productEntityList;
    }

    public static ProductEntity toDomain(Product product){
        ProductEntity productEntity = null;
        if(product !=null){
            CategoryEntity categoryEntity = new CategoryEntity(product.getCategory().getName());
            productEntity = new ProductEntity(product.getName(),categoryEntity,product.getPrice());
        }
        return productEntity;
    }


    public static CategoryEntity toCategoryDomain(Category category){
        CategoryEntity categoryEntity = null;
        if(category !=null){
            categoryEntity = new CategoryEntity(category.getName());
            categoryEntity.setAdditional_data(category.getAdditional_data());
        }
        return categoryEntity;
    }

    public static Category fromCategoryDomain(CategoryEntity categoryEntity){
        Category category = null;
        if(categoryEntity !=null){
            category = new Category(categoryEntity.getName(),categoryEntity.getAdditional_data());
            category.setId(categoryEntity.getCategory_id());
        }
        return category;
    }

    public static List<Category> fromCategoryDomainList(List<CategoryEntity> categoryEntityList){
        List<Category> categoryList = null;
        if(categoryEntityList !=null){
            categoryList = new ArrayList<Category>();
            for (CategoryEntity categoryEntity:categoryEntityList) {
                categoryList.add(fromCategoryDomain(categoryEntity));
            }
        }
        return categoryList;
    }

}
