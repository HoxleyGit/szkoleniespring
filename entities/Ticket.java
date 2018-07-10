package com.example.szkolenie.entities;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Data
@Entity
public class Ticket {

    @Id
    @GeneratedValue
    private Long id;
    private Date  startDate;
    private Date  endDate;
    @Enumerated
    private Status status;
    private String title;


    public Ticket() {
    }

    @java.beans.ConstructorProperties({"startDate", "endDate", "status", "title"})
    public Ticket(Date startDate, Date endDate, Status status, String title) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.title = title;
    }
}
