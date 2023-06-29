package com.lld.bookmyshow.services;

import com.lld.bookmyshow.exception.InvalidArgumentException;
import com.lld.bookmyshow.exception.SeatNotAvailableException;
import com.lld.bookmyshow.models.*;
import com.lld.bookmyshow.repositories.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    private SeatRepository seatRepository;
    private ShowSeatRepository showSeatRepository;
    private ShowRepository showRepository;
    private UserRepository userRepository;
    private TicketRepository ticketRepository;
    @Autowired
    public TicketService(SeatRepository seatRepository, ShowSeatRepository showSeatRepository, ShowRepository showRepository, UserRepository userRepository, TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
        this.seatRepository = seatRepository;
        this.showSeatRepository = showSeatRepository;
        this.showRepository = showRepository;
        this.userRepository = userRepository;
    }

    public Ticket bookTicket(List<Long> seatIds, Long userId, Long showId) throws Exception {
        // 1. for these seatIds get the correponding showseats getSeatsForIds(ids)
        // 2. Check the status of all the showseats getShowSeatsForSeats(seats)
        // 2.a: every seat is available
        // 3.a: lock every seat (set the status to be locked)
        // 4.a: Create ticket obj and return it
        // 2.b some of the seats are not available
        // 3.b: throw an exception

        List<Seat> seats = seatRepository.findAllByIdIn(seatIds);
        Optional<Show> showOptional =showRepository.findById(showId);
        if(showOptional.isEmpty()){
            throw new InvalidArgumentException("Show by-> "+showId+" is not exist");
        }
        List<ShowSeat> showSeats = showSeatRepository.findAllBySeatInAndShow(seats,showOptional.get());

        for(ShowSeat showSeat :showSeats){
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                throw new SeatNotAvailableException();
            }
        }

        List<ShowSeat> savedShowSeats = new ArrayList<>();

        for(ShowSeat showSeat :showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.LOCKED);
            showSeat.setLockedAt(new Date());
            ShowSeat savedSeat = showSeatRepository.save(showSeat);
            savedShowSeats.add(savedSeat);
        }
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new InvalidArgumentException("User with id->"+userId+" does not exist");
        }
        Ticket ticket = new Ticket();
        ticket.setBookedBy(userOptional.get());
        ticket.setTicketStatus(TicketStatus.PROCESSING);
        ticket.setShow(showOptional.get());
        ticket.setSeats(seats);
        ticket.setAmount(0);
        ticket.setTimeOfBooking(new Date());

        ticketRepository.save(ticket);
        return ticket;
    }
}
