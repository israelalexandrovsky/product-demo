package com.example.demo.dao;

import com.example.demo.domain.CategoryEntity;
import com.example.demo.domain.ProductEntity;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

public class ProductDao extends AbstractDao{


    @Autowired
    CategoryDao categoryDao;

    @Transactional(readOnly=true)
    public List<ProductEntity> getAllProducts() {
        TypedQuery<ProductEntity> query = em.createQuery("select a from ProductEntity a", ProductEntity.class);
        return query.getResultList();
    }

    @Transactional(readOnly=true)
    public int getAllProductCount() {
        TypedQuery<ProductEntity> query = em.createQuery("select a from ProductEntity a", ProductEntity.class);
        return query.getResultList().size();
    }


    @Transactional(readOnly=true)
    public ProductEntity getProductById(long productId) {
        ProductEntity productEntity = em.find(ProductEntity.class, productId);
        return productEntity;
    }

    @Transactional(propagation= Propagation.MANDATORY)
    public ProductEntity addProduct(ProductEntity productEntity) {
            CategoryEntity categoryEntity = categoryDao.getCategoryByName(productEntity.getCategory().getName());
            productEntity.setCategory(categoryEntity);
            return insertObject(productEntity,true);
    }


    @Transactional(propagation= Propagation.MANDATORY)
    public void deleteProductById(long productId) {
        ProductEntity productEntity = em.find(ProductEntity.class, productId);
        if(productEntity!=null){
            em.remove(productEntity);
        }
    }


    @Transactional(propagation= Propagation.MANDATORY)
    public ProductEntity updateProduct(long id,Product product) {
        ProductEntity updatedEntity = getProductById(id);
        if(updatedEntity!=null) {
            updatedEntity.setName(product.getName());
            updatedEntity.setPrice(product.getPrice());
            CategoryEntity categoryEntity = updatedEntity.getCategory();
            categoryEntity.setCategory_id(product.getCategory().getId());
            categoryEntity.setName(product.getCategory().getName());
            categoryEntity.setAdditional_data(product.getCategory().getAdditional_data());
            updatedEntity.setCategory(categoryEntity);
            updatedEntity = em.merge(updatedEntity);
            return updatedEntity;
        }
        return updatedEntity;
    }


    public ProductDao() {
    }
}
