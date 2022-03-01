package com.example.demo2.controller;


import com.example.demo2.entity.Product;
import com.example.demo2.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/")
    public String showHomePage(){
        return "DashBoard";
    }

    //GetAllProducts
    @RequestMapping("/products")
    public String listProducts(Model model){
        List<Product> listProducts=productService.listAll();
        model.addAttribute("listProducts",listProducts);
        return "Home";
    }


    @RequestMapping("/new")
    public String addProduct(Model model){
        Product product=new Product();
        model.addAttribute("product",product);
        return "newProduct";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product){
        productService.save(product);
        return "redirect:/products";
    }
    @RequestMapping("/edit/{id}")
    public ModelAndView editProduct(@PathVariable Long id){
        ModelAndView mav=new ModelAndView("editProduct");
        Product product=productService.get(id);
        mav.addObject("product",product);
        return mav;
    }
    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/products";
    }
    @GetMapping("/productsList")
    public String productsList(Model model){
        List<Product> listProducts=productService.listAll();
        model.addAttribute("listProducts",listProducts);
        return "productsList";
    }
    @RequestMapping("/contact")
    public String contactPage(){
        return "contact";
    }
}
