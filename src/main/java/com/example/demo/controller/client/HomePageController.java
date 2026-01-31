package com.example.demo.controller.client;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.domain.DTO.RegisterDTO;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomePageController {
    
       private final ProductService productService;
       private final UserService userService;
       private final PasswordEncoder passwordEncoder;

        public HomePageController(ProductService productService, UserService userService, PasswordEncoder passwordEncoder) {
            this.productService = productService;
            this.userService = userService;
            this.passwordEncoder = passwordEncoder;
        }

    @GetMapping("/")
    public String home(Model model) {

        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("products", products);
        return "client/homepage/show";

    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerDTO", new RegisterDTO());
        return "client/auth/register";
    }
    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("registerDTO") @Valid RegisterDTO registerDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "client/auth/register";
        }
        User user = this.userService.registerDTOtoUser(registerDTO);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user.setRole(this.userService.getRoleByName("USER"));
        this.userService.handleSaveUser(user);
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String loginPage(Model model ) {
        return "client/auth/login";
    }

    @GetMapping("/access-denied")
    public String getAccessDenied() {
        return "client/auth/access_denied";
    }

}
