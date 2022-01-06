package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;


    //Add Product(Post)
    public String addProduct(Product product){
        repository.save(product);
        return "Product Created";
     }



     //Show Products(Get)
    public List<Product> getProducts(){

        return repository.findAll();
    }

    //Show Product By ID(Get)
    public Product getProduct(int id){
       Optional<Product> temp=repository.findById(id);
        if(temp.isPresent())
            return repository.findById(id).get();
        else
            throw new NoSuchElementException("PRODUCT NOT FOUND");
    }

    //Delete Product(Delete)
    public String deleteProduct(int id){
        Optional<Product> temp=repository.findById(id);
        if(temp.isPresent()){
           repository.deleteById(id);
           return "PRODUCT REMOVED";
        }
        else
            throw new NoSuchElementException("No Product Found");
    }

    //Update Product(Put)
    public String updateProduct(Product product,int id){
        Optional<Product> temp=repository.findById(id);
        if(temp.isPresent()){
            Product e=repository.getById(id);
            String s="";
            if(!e.getName().equals(product.getName())){
                e.setName(product.getName());
                repository.save(e);
                s="Updated Name.\n";
            }
            if(!e.getInternal_memory().equals(product.getInternal_memory())){
                e.setInternal_memory(product.getInternal_memory());
                repository.save(e);
                s=s+"Updated Product Internal Memory.\n";
            }
            if(!e.getPrice().equals(product.getPrice())){
                e.setPrice(product.getPrice());
                repository.save(e);
                s=s+"Updated Product Price.";
            }
            return s;
        }
        else
            throw new NoSuchElementException("NO Employee Found Here");
    }

}
