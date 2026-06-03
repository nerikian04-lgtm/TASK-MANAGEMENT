package com.tracker.tracking.service;

import com.tracker.tracking.model.Ticket;
import com.tracker.tracking.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicket(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public void updateStatus(Long id, String status) {

        Ticket ticket = ticketRepository.findById(id).orElse(null);

        if (ticket != null) {
            ticket.setStatus(status);
            ticketRepository.save(ticket);
        }
    }
}