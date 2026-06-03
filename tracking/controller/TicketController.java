package com.tracker.tracking.controller;

import com.tracker.tracking.model.Ticket;
import com.tracker.tracking.repository.TicketRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin("*")
public class TicketController {

    private final TicketRepository ticketRepository;

    public TicketController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    private static final String CHARLES = "charles@digidesk.com";
    private static final String KIAN = "kian@digidesk.com";
    private static final String PAMELA = "pamela@digidesk.com";
    private static final String ANGELICA = "angelica@digidesk.com";
    private static final String ACE = "ace@digidesk.com";

    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {

        String staffEmail;

        switch (ticket.getTask()) {
            case "Data loss": staffEmail = CHARLES; break;
            case "Software Compatibility": staffEmail = KIAN; break;
            case "Server Downtime":
            case "Wi-Fi Not Connecting": staffEmail = PAMELA; break;
            case "Account Locked Out": staffEmail = ANGELICA; break;
            case "Hacked Account": staffEmail = ACE; break;
            default: staffEmail = CHARLES;
        }

        ticket.setStaffEmail(staffEmail);

        String prefix;

        switch (ticket.getTask()) {
            case "Account Locked Out": prefix = "ACC"; break;
            case "Server Downtime":
            case "Wi-Fi Not Connecting": prefix = "NET"; break;
            case "Data loss": prefix = "DAT"; break;
            case "Software Compatibility": prefix = "SFT"; break;
            case "Hacked Account": prefix = "SEC"; break;
            default: prefix = "GEN";
        }

        String number = String.valueOf(
                ThreadLocalRandom.current().nextInt(1000, 10000)
        );

        ticket.setTicketNumber(prefix + "-" + number);
        ticket.setStatus("Pending");

        return ticketRepository.save(ticket);
    }

    // ✅ NEW: STATUS UPDATE API
    @PutMapping("/{id}/status")
    public Ticket updateStatus(@PathVariable Long id,
                               @RequestParam String status) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticket.setStatus(status);

        return ticketRepository.save(ticket);
    }
    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
}