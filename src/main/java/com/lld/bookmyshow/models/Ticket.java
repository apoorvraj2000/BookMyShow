package com.lld.bookmyshow.models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Ticket extends BaseModel {
    private int amount;
    private Date timeOfBooking;
    @ManyToMany
    private List<Seat> seats;
    @ManyToOne
    private User bookedBy;
    @ManyToOne
    private Show show;
     @OneToMany(mappedBy = "ticket")
    private List<Payment> payments;
    @Enumerated(EnumType.ORDINAL)
    private TicketStatus ticketStatus;
}
