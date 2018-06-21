package com.example.demo.dao;

import com.example.demo.domain.CategoryEntity;
import com.example.demo.domain.ProductEntity;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

public class CategoryDao extends AbstractDao{

    @Transactional(readOnly=true)
    public List<CategoryEntity> getAllCategories() {
        TypedQuery<CategoryEntity> query = em.createQuery("select a from CategoryEntity a", CategoryEntity.class);
        return query.getResultList();
    }


    @Transactional(readOnly=true)
    public CategoryEntity getCategoryByName(String categoryName) {
        TypedQuery<CategoryEntity> query = em.createQuery("select a from CategoryEntity a where a.name= :name", CategoryEntity.class);
        query.setParameter("name",categoryName);
        List<CategoryEntity> resultList = query.getResultList();
        CategoryEntity categoryEntity = null;
        if(resultList.size()>0) {
            categoryEntity = resultList.get(0);
        }
        return  categoryEntity;
    }

    @Transactional(readOnly=true)
    public CategoryEntity getCategoryById(long categoryId) {
        CategoryEntity categoryEntity = em.find(CategoryEntity.class, categoryId);
        return categoryEntity;
    }

    @Transactional(propagation= Propagation.MANDATORY)
    public CategoryEntity addCategory(CategoryEntity categoryEntity) {
        TypedQuery<CategoryEntity> query = em.createQuery("select a from CategoryEntity a where a.name= :name", CategoryEntity.class);
        query.setParameter("name",categoryEntity.getName());
        List<CategoryEntity> resultList = query.getResultList();
        CategoryEntity existEntity = null;
        if(resultList.size()==0) {
            return insertObject(categoryEntity,true);
        }else {
            existEntity = resultList.get(0);
        }
        return existEntity;
    }

    @Transactional(propagation= Propagation.MANDATORY)
    public void deleteCategoryById(long categoryId) {
        CategoryEntity categoryEntity = em.find(CategoryEntity.class, categoryId);
        if(categoryEntity!=null){
            em.remove(categoryEntity);
        }
    }


    @Transactional(propagation= Propagation.MANDATORY)
    public CategoryEntity updateCategory(long id,Category category) {
        CategoryEntity updatedEntity = getCategoryById(id);
        if(updatedEntity!=null){
            updatedEntity.setName(category.getName());
            updatedEntity.setAdditional_data(category.getAdditional_data());
            updatedEntity = em.merge(updatedEntity);
        }
        return updatedEntity;
    }


    public CategoryDao() {
    }
}
