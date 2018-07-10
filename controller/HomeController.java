package com.example.szkolenie.controller;

import com.example.szkolenie.entities.Status;
import com.example.szkolenie.entities.Ticket;
import com.example.szkolenie.repositories.TicketRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.sql.Date;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

    private final TicketRepository repository;

    public HomeController(TicketRepository repository) {
        Objects.requireNonNull(repository);
        this.repository = repository;
    }

    @GetMapping("/login")
    public String login(Principal principal){
        return  principal != null ? "redirect:/" : "login";
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("tickets", repository.findAll());
        return "index";
    }

    @GetMapping("/createTicket")
    public String createTicket(Model model){
        model.addAttribute("ticket", new Ticket());
        return "create_ticket";
    }

    @PostMapping("/createTicket")
    public String createTicket(Ticket ticket, Model model){
        if(ticket.getId() != null){
            Ticket saved = repository.save(ticket);
            setAtributesToModel(model, saved, false, "Ticket updated");
            return "ticket_result";
        }
        Optional<Ticket> ticketFromDb = repository.findByTitle(ticket.getTitle());

        ticketFromDb.ifPresent(t -> {
            setAtributesToModel(model, t, true, "Ticket already exists!");
        });

        if(!ticketFromDb.isPresent()){
            ticket.setStartDate(new Date(System.currentTimeMillis()));
            ticket.setStatus(Status.OPEN);
            ticket.setEndDate(new Date(0));
            ticket = repository.save(ticket);
            setAtributesToModel(model, ticket, false, "Ticket added");
        }
        return "ticket_result";
    }

    private void setAtributesToModel(Model model, Ticket saved, boolean b, String s) {
        model.addAttribute("error", b)
                .addAttribute("message", s)
                .addAttribute("ticket", saved);
    }

    @GetMapping("/editTicket/{id}")
    public String editTicket(@PathVariable("id") Ticket ticket, Model model){
        model.addAttribute("ticket", ticket);
        return "edit_ticket";
    }
}
