package com.business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.business.entities.User;
import com.business.repositories.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

    // user dashboard, profile, orders (later)
}
