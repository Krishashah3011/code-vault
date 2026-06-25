package com.codevault.snippets.controller;

import com.codevault.snippets.entity.User;
import com.codevault.snippets.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // show register page
    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user,
                            @RequestParam String confirmPassword,
                            Model model) {
        // ✅ DEBUG PRINT (important for you)
        System.out.println("EMAIL: " + user.getEmail());
        // ✅ CHECK NULL FIRST
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            model.addAttribute("error", "Email is required");
            return "register";
        }
        // ✅ EMAIL VALIDATION (SAFE)
        if (!user.getEmail().contains("@")) {
            model.addAttribute("error", "Invalid email format");
                return "register";
            }
        // ✅ PASSWORD MATCH
        if (!user.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "register";
        }
        try {
            userService.saveUser(user);
            return "redirect:/login";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
            }
        }

    // show login page (Spring Security handles actual login)
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}