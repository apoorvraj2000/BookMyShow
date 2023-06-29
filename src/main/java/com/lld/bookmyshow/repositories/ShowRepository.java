package com.lld.bookmyshow.repositories;

import com.lld.bookmyshow.models.Show;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<Show,Long> {
    @Override
    Optional<Show> findById(Long aLong);
}
