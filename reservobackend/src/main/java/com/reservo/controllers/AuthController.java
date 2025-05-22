package com.reservo.controllers;

import com.reservo.Repository.ReservoUserRepository;
import com.reservo.services.ReservoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private ReservoUserService reservoUserService;

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            @RequestParam(value = "register=success", required = false) String registered,
                            Model model) {

        if (error != null) {
            model.addAttribute("error", "Incorrect username or password.");
        }

        if (logout != null) {
            model.addAttribute("success", "You have been logged out.");
        }

        if (registered != null) {
            model.addAttribute("success", "Registration successful! Please login.");
        }

        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String username,
            @RequestParam String password,
            RedirectAttributes redirectAttributes
    ) {

        // Check if user already exists
        if (reservoUserService.usernameExists(username)) {
            redirectAttributes.addFlashAttribute("error", "Username already exists!");
            return "redirect:/register";
        }

        if (reservoUserService.emailExists(email)) {
            redirectAttributes.addFlashAttribute("error", "Email already registered!");
            return "redirect:/register";
        }

        reservoUserService.registerUser(firstName, lastName, email, phone, username, password);
        return "redirect:/login?register=success";
    }

}
