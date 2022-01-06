package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ProductController {
    @Autowired
    private ProductService service;


    @PostMapping("/addProduct")
    public String addProduct(@RequestBody Product product){

        return service.addProduct(product);
    }

    @GetMapping("/products")
    public List<Product> getProducts(){

        return service.getProducts();
    }
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable int id){

        return service.getProduct(id);
    }
    @PutMapping("/products/{id}")
    public String updateProduct(@RequestBody Product product,@PathVariable int id) {

        return service.updateProduct(product,id);
    }
    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable int id){

        return service.deleteProduct(id);
    }

}
