package com.tracker.tracking.repository;

import com.tracker.tracking.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByStatus(String status);

    List<Ticket> findByStaffEmailAndStatusNot(String staffEmail, String status);

    List<Ticket> findByStaffEmailAndStatus(String staffEmail, String status);
}