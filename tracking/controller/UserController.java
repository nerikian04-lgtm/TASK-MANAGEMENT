package com.tracker.tracking.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/page")
    public String userPage(HttpSession session, Model model) {

        if (session.getAttribute("email") == null) {
            return "redirect:/";
        }

        model.addAttribute("name", session.getAttribute("name"));

        return "user_page";
    }

    @GetMapping("/form")
    public String formPage() {

        return "form";
    }

    @GetMapping("/help")
    public String helpPage() {

        return "help";
    }

    @GetMapping("/about")
    public String userabout() {
        return "about";
    }

    @GetMapping("/user-service")
    public String userservice() {
        return "user-service";
    }

    @GetMapping("/manageAcc")
    public String usermanAcc(HttpSession session, Model model){
        model.addAttribute("name", session.getAttribute("name"));
        return "manageAcc";
    }
}