package com.example.demo.service;

import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.ProductDao;
import com.example.demo.domain.ProductEntity;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductStorageService implements IProductStorageService {

    @Autowired
    ProductDao productDao;

    @Autowired
    CategoryDao categoryDao;

    @Override
    @Transactional
    public List<Product> getAllProducts() {
        return Utils.fromDomainList(productDao.getAllProducts());
    }

    @Override
    @Transactional
    public int getAllProductsCount() {
        return productDao.getAllProductCount();
    }

    @Override
    @Transactional
    public Product getProductById(long productId) {
        return Utils.fromDomain(productDao.getProductById(productId));
    }

    @Override
    public Category getCategoryById(long categoryId) {
        return Utils.fromCategoryDomain(categoryDao.getCategoryById(categoryId));
    }

    @Override
    @Transactional
    public Product addProduct(Product product) {
        return Utils.fromDomain(productDao.addProduct(Utils.toDomain(product)));
    }

    @Override
    @Transactional
    public Category addCategory(Category category) {
        return Utils.fromCategoryDomain((categoryDao.addCategory(Utils.toCategoryDomain(category))));
    }

    @Override
    @Transactional
    public void deleteProduct(long id) {
        productDao.deleteProductById(id);
    }


    @Override
    @Transactional
    public Product updateProduct(long productId,Product product) {
        return Utils.fromDomain(productDao.updateProduct(productId,product));
    }



    @Override
    @Transactional
    public List<Category> getAllCategories() {
        return Utils.fromCategoryDomainList(categoryDao.getAllCategories());
    }

    @Override
    public Category getCategoryByName(String name) {
        return Utils.fromCategoryDomain((categoryDao.getCategoryByName(name)));
    }

    @Override
    public void deleteCategory(long id) {

    }

    public ProductStorageService() {
    }
}
