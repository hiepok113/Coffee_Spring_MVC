package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Controller
public class UserController{
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
        public String getUser(Model model){
            model.addAttribute("userInfo", "test");
            return "hello";
        }
    @RequestMapping("/admin/user")
    public String getAdminUser(Model model) {
        model.addAttribute("newUser", new User());
        return "/admin/user/create";
    }
    @RequestMapping(value = "/admin/user/create1", method = RequestMethod.POST)
     public String createAdminUser(Model model , @ModelAttribute("newUser") User user) {
        System.out.println(user);
        this.userService.handleSaveUser(user);
        return "hello";
    }
}
