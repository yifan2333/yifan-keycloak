package com.yifan.controller;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yifan.service.ProductService;

@Controller
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/products")
    public String getProducts(Model model){
        model.addAttribute("products", productService.getProducts());
        return "product";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "index.html";
    }
}
