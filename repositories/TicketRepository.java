package com.example.szkolenie.repositories;

import com.example.szkolenie.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByTitle(String title);
}
