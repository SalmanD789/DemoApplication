package com.example.demo3.Controller;

import com.example.demo3.Model.Product;
import com.example.demo3.Service.ProductService;
import com.example.demo3.Service.UserService;
import com.example.demo3.web.dto.UserRegistrationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Controller
public class ProductController {

    Logger logger = LoggerFactory.getLogger(ProductController.class);


    @Autowired
    private ProductService productService;

    private UserService userService;

    public ProductController(UserService userService){
        this.userService=userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }
    @GetMapping("/registration")
    public String showRegistrationForm() {
        logger.trace("Getting Registration Page...");
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        userService.save(registrationDto);
        return "redirect:/registration?success";
    }

    @GetMapping("/login")
    public String login() {
        logger.trace("Getting Login Page...");
        return "login";
    }


    @GetMapping("/")
    public String showHomepage(){
        logger.trace("Getting Home Page...");
        return "dashboard";
    }
    //All Products On Home Page
    @RequestMapping("/products")
    public String listProducts(Model model){
        List<Product> listProducts=productService.listAll();
        model.addAttribute("listProducts",listProducts);
        logger.trace("Getting Products Page...");
        return "home";
    }

    @RequestMapping("/new")
    public String addProduct(Model model){
        Product product=new Product();
        model.addAttribute("product",product);
        logger.trace("Getting new Product adding page...");
        return "newProduct";
    }
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product){
        productService.save(product);
        logger.trace("Saving new product...");
        return "redirect:/new?success";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editProduct(@PathVariable Long id){
        ModelAndView mav=new ModelAndView("editProduct");
        Product product=productService.get(id);
        mav.addObject("product",product);
        return mav;
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("product") Product product) {
        productService.save(product);
        logger.trace("Updating Product...");
        return "redirect:/products?success";
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        logger.trace("Deleting Product");
        return "redirect:/products?danger";
    }
    @GetMapping("/listProducts")
    public String productsList(Model model){
        List<Product> listProducts=productService.listAll();
        model.addAttribute("listProducts",listProducts);
        return "listProduct";
    }
    @RequestMapping("/contact")
    public String contactPage(){
        logger.trace("Getting Contact Page");
        return "contact";
    }
}
