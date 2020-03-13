package com.yifan.controller;

import com.yifan.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping(path = "/products")
    public String getProducts(Model model){
        model.addAttribute("products", productService.getProducts());
        return "product";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "/";
    }
}
