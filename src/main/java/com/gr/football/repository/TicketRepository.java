package com.gr.football.repository;

import com.gr.football.entity.Game;
import com.gr.football.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Ticket getGameStadiumByGame(Game game);

    @Query("SELECT g, COUNT(t), MAX(t.stadium.stadiumName) FROM Game g JOIN g.tickets t WHERE t.status = 'SOLD' GROUP BY g ORDER BY COUNT(t) DESC")
    List<Object[]> findGameAndStadiumNameOrderByTicketCountDesc();

}
