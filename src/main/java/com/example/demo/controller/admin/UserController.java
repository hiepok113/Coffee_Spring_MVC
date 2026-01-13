package com.example.demo.controller.admin;


import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.User;
import com.example.demo.service.UploadService;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController{
    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UploadService uploadService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/admin/user/{id}")
    public String getUserDetail(Model model , @PathVariable Long id) {
    User user = userService.getUserById(id);
    model.addAttribute("user", user);
    return "/admin/user/userdetail";
    }
    @GetMapping("/admin/user")
    public String getAdminUser(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users1", users);
        return "/admin/user/stable-user";
    }
    @GetMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
         model.addAttribute("newUser", new User());
        return "/admin/user/create";
    }


    @PostMapping("/admin/user/create")
     public String createAdminUser(Model model , @ModelAttribute("newUser") @Valid User user ,BindingResult bindingResult,
        @RequestParam("avatarFile") MultipartFile avatarFile) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                model.addAttribute(error.getField() + "Error", error.getDefaultMessage());
            }
            if(bindingResult.hasErrors()) {
                return "/admin/user/create";
            }

        String targetFolder = "avatar";
        String hashPasswordString = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPasswordString);
        this.uploadService.handleUploadFile(avatarFile, targetFolder);
        user.setAvatarString(targetFolder);
        user.setPassword(hashPasswordString);
        user.setRole(this.userService.getRoleByName(user.getRole().getName()));
        this.userService.handleSaveUser(user);
        return "redirect:/admin/user";
    }
    @GetMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(@PathVariable Long id, Model model) {
    User user = userService.getUserById(id);
    model.addAttribute("user", user);
    return "/admin/user/update";
}
    @PostMapping("/admin/user/update")
    public String updateUser(@ModelAttribute User user) {
    User updatedUser = this.userService.getUserById(user.getId());
    if(updatedUser != null) {
        updatedUser.setFullName(user.getFullName());
        updatedUser.setAddress(user.getAddress());
        updatedUser.setNumber(user.getNumber());
        this.userService.handleSaveUser(updatedUser);
    }
    else {
        // Handle user not found case
    }
    return "redirect:/admin/user";
}
    @GetMapping("/admin/user/delete/{id}")
    public String getDeletePage(@PathVariable Long id, Model model) {
    User user = userService.getUserById(id);
    model.addAttribute("user", user);
    return "/admin/user/delete";
}
    @PostMapping("/admin/user/delete")
    public String deleteUser(@RequestParam Long id) {
    userService.deleteUserById(id);
    return "redirect:/admin/user";
}

}