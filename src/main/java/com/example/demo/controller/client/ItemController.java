package com.example.demo.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemController {
    @GetMapping("/product/{id}")
    public String getItems(Model model, @PathVariable Long id) {
        return "client/product/detail";
    }
}
