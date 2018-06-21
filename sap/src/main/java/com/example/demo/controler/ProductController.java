package com.example.demo.controler;

import com.example.demo.dao.ProductDao;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.ProductStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductStorageService productStorageService;


    @PostConstruct
    public void createCategories(){
        Category fruit = new Category("Fruit","Addition Data");
        Category vegetable = new Category("Vegetable","Addition Data");
        Category cosmetics = new Category("Cosmetics","Addition Data");
        Category electronics = new Category("Electronics","Addition Data");
        Category mobile = new Category("Mobile","Addition Data");

        productStorageService.addCategory(fruit);
        productStorageService.addCategory(vegetable);
        productStorageService.addCategory(cosmetics);
        productStorageService.addCategory(electronics);
        productStorageService.addCategory(mobile);

        Product apple = new Product("Apple",fruit,5.5);
        Product tomato = new Product("Tomato",vegetable,4.5);
        Product perfume = new Product("Perfume",cosmetics,110);
        Product iphone = new Product("IPhone",mobile,2500);
        Product playStation = new Product("Play Station",electronics,2000);

        productStorageService.addProduct(apple);
        productStorageService.addProduct(tomato);
        productStorageService.addProduct(perfume);
        productStorageService.addProduct(iphone);
        productStorageService.addProduct(playStation);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView getAllProducts(HttpServletRequest request){
        List<Product> productList = productStorageService.getAllProducts();
        request.setAttribute("mode","VIEW");
        return new ModelAndView("products", "products", productList);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addProduct(HttpServletRequest request){
        Product product = new Product();
        request.setAttribute("mode","NEW");
        List<Category> categories = productStorageService.getAllCategories();
        request.setAttribute("categories",categories);
        request.setAttribute("selected",categories.get(0).getId());
        return new ModelAndView("products", "product", product);

    }


    @RequestMapping(value = "/products/delete/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable String id){
        productStorageService.deleteProduct(Long.valueOf(id));
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public ModelAndView updateProduct(@PathVariable String id, HttpServletRequest request){
        Product product = productStorageService.getProductById(Long.valueOf(id));
        request.setAttribute("mode","EDIT");
        List<Category> categories = productStorageService.getAllCategories();
        request.setAttribute("categories",categories);
        request.setAttribute("selected",product.getCategory().getId());
        return new ModelAndView("products", "product", product);
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@ModelAttribute  Product product, HttpServletResponse response) throws IOException {
        Category category = productStorageService.getCategoryById(product.getCategory().getId());
        product.setCategory(category);
        productStorageService.deleteProduct((product.getId()));
        product = productStorageService.addProduct(product);
        response.sendRedirect("/products");
    }

    @RequestMapping(value = "/saveNew", method = RequestMethod.POST)
    public void saveNew(@ModelAttribute  Product product, HttpServletResponse response) throws IOException {
        Category category = productStorageService.getCategoryById(product.getCategory().getId());
        product.setCategory(category);
        product = productStorageService.addProduct(product);
        response.sendRedirect("/products");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public void deleteProduct(@PathVariable String id, HttpServletResponse response) throws IOException {
        productStorageService.deleteProduct((Long.valueOf(id)));
        response.sendRedirect("/products");

    }

}
