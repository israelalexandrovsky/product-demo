package com.example.demo.service;

import com.example.demo.domain.ProductEntity;
import com.example.demo.model.Category;
import com.example.demo.model.Product;

import java.util.List;

public interface IProductStorageService {

    List<Product> getAllProducts();

    int getAllProductsCount();

    Product getProductById(long productId);

    Category getCategoryById(long categoryId);

    Product addProduct(Product product);

    Category addCategory(Category category);

    void deleteProduct(long id);

    Product updateProduct(long productId,Product product);

    List<Category> getAllCategories();

    Category getCategoryByName(String name);

    void deleteCategory(long id);



}
