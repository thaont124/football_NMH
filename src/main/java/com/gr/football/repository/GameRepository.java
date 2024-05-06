package com.gr.football.repository;

import com.gr.football.entity.Game;
import com.gr.football.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {
    @Query("SELECT g FROM Game g WHERE g.round.season.seasonId = :seasonId")
    List<Game> findAllBySeasonId(@Param("seasonId") Integer seasonId);

    @Query("SELECT gs FROM Ticket gs WHERE gs.game.gameId = :gameId")
    Ticket getGameStadiumByGame(@Param("gameId") Integer gameId);

    List<Game> findAllByTime(LocalDateTime time);
}
