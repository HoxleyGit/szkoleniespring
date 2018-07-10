package com.example.szkolenie;

import com.example.szkolenie.entities.Status;
import com.example.szkolenie.entities.Ticket;
import com.example.szkolenie.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Component
public class Loader {
    private final TicketRepository repository;

    @Autowired
    public Loader(TicketRepository repository) {
        Objects.requireNonNull(repository);
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        List<Ticket> tickets = new ArrayList<>();
        Random random = new Random();

        for(int i = 0; i<10; i++){
            tickets.add(
                    new Ticket(
                            new Date(System.currentTimeMillis() - 1_000_000),
                            new Date(System.currentTimeMillis()),
                            Status.values()[random.nextInt(4)],
                            "ticket" + i
                            )
            );
        }
        repository.saveAll(tickets);
    }
}
