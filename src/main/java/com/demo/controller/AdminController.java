package com.demo.controller;


import com.demo.model.*;
import com.demo.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    AccountRepo accountRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    OrderRepo orderRepo;
    @Autowired
    OrderDetailRepo orderDetailRepo;

    @GetMapping("/admin/category/index")
//    @PreAuthorize("hasRole('admin')")
    public String listCategory(Model model) {
        List<Category> categoryList = categoryRepo.findAll();
        model.addAttribute("category", categoryList);
        return "admin/category/list";
    }

    @PostMapping("/admin/category/create")
    public String createCategory(@RequestParam("name") String name, @RequestParam("id") String id, Model model) {
        Category category = Category.builder()
                .id(id)
                .name(name)
                .build();
        categoryRepo.save(category);
        return "redirect:/admin/category/index";
    }

    @GetMapping("/admin/category/edit/{id}")
    public String editCategory(@PathVariable("id") String id, Model model) {
        Category category = categoryRepo.findById(id).get();
        model.addAttribute("category", category);
        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("categorys", categories);
        return "admin/category/form";
    }

    @PostMapping("/admin/category/update/{id}")
    public String updateCategory(@RequestParam("id") String id, @RequestParam("name") String name) {
        Category category = Category.builder()
                .id(id)
                .name(name)
                .build();
        categoryRepo.save(category);
        return "redirect:/admin/category/index";
    }

    //  Product
    @GetMapping("/admin/product/index")
    public String listProduct(Model model) {
        List<Product> productList = productRepo.findAll();
        model.addAttribute("product", productList);
        List<Category> categoryList = categoryRepo.findAll();
        model.addAttribute("categoryList", categoryList);
        return "admin/product/list";
    }

    @PostMapping("/admin/product/create")
    public String createProduct(@RequestParam("name") String name,
                                @RequestParam("price") Integer price, @RequestParam("image") String image, @RequestParam("category") String categoryId) {
        Category category = categoryRepo.findById(categoryId).orElse(null);
        Product product = Product.builder()
                .name(name)
                .price(price)
                .image(image)
                .category(category)
                .build();
        productRepo.save(product);
        return "redirect:/admin/product/index";
    }

    @GetMapping("/admin/product/edit/{id}")
    public String editProduct(@PathVariable int id, Model model) {
        Product product = productRepo.findById(id).get();
        model.addAttribute("product", product);
        List<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "admin/product/form";
    }

    //  Account
    @GetMapping("/admin/account/index")
    public String listAccount(Model model) {
        List<Account> listAccount = accountRepo.findAll();
        model.addAttribute("accounts", listAccount);
        return "admin/account/list";
    }

    @PostMapping("/admin/account/create")
    public String createAccount(@RequestParam("username") String username, @RequestParam("password") String password,
                                @RequestParam("fullname") String fullname, @RequestParam("email") String email) {
        Account account = Account.builder()
                .username(username)
                .password(password)
                .fullname(fullname)
                .email(email)
                .build();
        accountRepo.save(account);
        return "redirect:/admin/account/index";
    }

    @GetMapping("/admin/account/edit/{username}")
    public String editAccount(@PathVariable("username") String username, Model model) {
        Account accounts = accountRepo.findById(username).get();
        model.addAttribute("account", accounts);
        List<Account> accounts1 = accountRepo.findAll();
        model.addAttribute("accounts", accounts1);
        return "admin/account/form";
    }

    @PostMapping("/admin/account/update/{username}")
    public String uodateAccount(@RequestParam("username") String username, @RequestParam("password") String password,
                                @RequestParam("fullname") String fullname, @RequestParam("email") String email) {
        Account account = Account.builder()
                .username(username)
                .password(password)
                .fullname(fullname)
                .email(email)
                .build();
        accountRepo.save(account);
        return "redirect:/admin/account/index";
    }

    @GetMapping("/admin/account/delete/{username}")
    public String deleteAccount(@PathVariable("username") String username, Model model) {
        accountRepo.deleteById(username);
        return "redirect:/admin/account/index";
    }

    @GetMapping("/admin/order/index")
    public String order(Model model) {
        List<OrderDetail> orderDetailList = orderDetailRepo.findAll();
        model.addAttribute("orderD", orderDetailList);
        return "admin/order/list";
    }
}
