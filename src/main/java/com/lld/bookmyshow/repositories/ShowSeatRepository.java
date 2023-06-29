package com.lld.bookmyshow.repositories;

import com.lld.bookmyshow.models.Seat;
import com.lld.bookmyshow.models.Show;
import com.lld.bookmyshow.models.ShowSeat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long> {

    List<ShowSeat> findAllBySeatInAndShow(List<Seat> seats, Show show);

    ShowSeat save(ShowSeat showSeat);
}
