package com.tracker.tracking.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("")
    public String adminPage(HttpSession session, Model model) {

        if (session.getAttribute("email") == null) {
            return "redirect:/";
        }

        String name = (String) session.getAttribute("name");
        model.addAttribute("name", name);

        return "admin";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/about")
    public String adminabout() {
        return "admin-about";
    }
    
    @GetMapping("/service")
    public String servicePage() {
        return "service";
    }
}