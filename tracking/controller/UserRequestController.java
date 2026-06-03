package com.tracker.tracking.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserRequestController {

    @GetMapping("/user_request")
    public String userRequest(HttpSession session) {

        // optional security check (recommended)
        if (session.getAttribute("email") == null) {
            return "redirect:/";
        }

        return "user_request";
    }
}