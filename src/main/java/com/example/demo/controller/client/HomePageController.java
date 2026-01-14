package com.example.demo.controller.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;

@Controller
public class HomePageController {
       private final ProductService productService;

        public HomePageController(ProductService productService) {
            this.productService = productService;
        }

    @GetMapping("/")
    public String home(Model model) {

        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("products", products);
        return "client/homepage/show";

    }
}
