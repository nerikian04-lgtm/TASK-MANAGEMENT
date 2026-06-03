package com.tracker.tracking.controller;

import com.tracker.tracking.repository.TicketRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final TicketRepository ticketRepository;

    public AdminController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @GetMapping("")
    public String adminPage(HttpSession session, Model model) {

        if (session.getAttribute("email") == null) {
            return "redirect:/";
        }

        String email = (String) session.getAttribute("email");
        String name = (String) session.getAttribute("name");

        model.addAttribute("email", email);
        model.addAttribute("name", name);

        var requestTickets =
                ticketRepository.findByStaffEmailAndStatusNot(email, "Done");

        var historyTickets =
                ticketRepository.findByStaffEmailAndStatus(email, "Done");

        model.addAttribute("requestTickets", requestTickets);
        model.addAttribute("historyTickets", historyTickets);

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