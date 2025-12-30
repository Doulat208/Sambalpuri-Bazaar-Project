package com.business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.business.entities.User;
import com.business.repositories.UserRepository;
import com.business.services.UserServices;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private UserRepository userRepository;

    // LOGIN
    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, HttpSession session, Model model) {

        User loggedUser = userServices.validateUser(
                user.getUemail(),
                user.getUpassword()
        );

        if (loggedUser == null) {
            model.addAttribute("error", "Invalid email or password");
            return "Login";
        }

        session.setAttribute("loggedUser", loggedUser);
        return "redirect:/home";
    }

    // SHOW REGISTER PAGE
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "Register";
    }

    // HANDLE REGISTER
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {

        if (user.getUemail() == null || user.getUpassword() == null) {
            model.addAttribute("error", "Fields cannot be empty");
            return "Register";
        }

        if (userRepository.findByUemail(user.getUemail()) != null) {
            model.addAttribute("error", "Email already registered!");
            return "Register";
        }

        userRepository.save(user);
        return "redirect:/login";
    }

    // LOGOUT
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
