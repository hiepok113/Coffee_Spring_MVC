package com.example.demo.controller;

import java.util.List;

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
            List<User> users = this.userService.getAllUsersByEmail("2@gmail.com");
            System.out.println(users);
            model.addAttribute("userInfo", "test");
            return "hello";
        }
    @RequestMapping("/admin/user")
    public String getAdminUser(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users1", users);
        return "/admin/user/stable-user";
    }
      @RequestMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
         model.addAttribute("newUser", new User());
        return "/admin/user/create";
    }
    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
     public String createAdminUser(Model model , @ModelAttribute("newUser") User user) {
        this.userService.handleSaveUser(user);
        return "redirect:/admin/user";
}
}