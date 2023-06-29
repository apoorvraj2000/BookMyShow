package com.lld.bookmyshow.dtos;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookTicketResponseDto {
    private int amount;
    private Long ticketId;
    private List<String> seatNumber;
    private String auditoriumName;
//    private String status;
//    private String message;

}
