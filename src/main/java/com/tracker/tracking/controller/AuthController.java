package com.tracker.tracking.controller;

import com.tracker.tracking.model.User;
import com.tracker.tracking.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    // SHOW LOGIN PAGE
    @GetMapping("/")
    public String showLogin(HttpSession session) {

        if (session.getAttribute("active_form") == null) {
            session.setAttribute("active_form", "login");
        }

        return "login";
    }

    // REGISTER
    @PostMapping("/register")
    public String register(@ModelAttribute User user, HttpSession session) {

        String result = service.register(user);

        if (!result.equals("success")) {

            session.setAttribute("register_error", result);
            session.setAttribute("active_form", "register");

        } else {

            session.setAttribute("register_error", "");
            session.setAttribute("login_error", "");
            session.setAttribute("active_form", "login");
        }

        return "redirect:/";
    }

    // LOGIN
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session) {

        User user = service.login(email, password);

        if (user != null) {

            session.setAttribute("name", user.getName());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("role", user.getRole());

            session.setAttribute("login_error", "");
            session.setAttribute("register_error", "");

            if ("admin".equals(user.getRole())) {
                return "redirect:/admin";
            } else {
                return "redirect:/user/page";
            }
        }

        session.setAttribute("login_error", "Incorrect email or password");
        session.setAttribute("active_form", "login");

        return "redirect:/";
    }

    // LOGOUT
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/";
    }
}