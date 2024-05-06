package com.gr.football.repository;

import com.gr.football.entity.Game;
import com.gr.football.entity.GameReferee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GameRefereeRepository extends JpaRepository<GameReferee, Integer> {

    List<GameReferee> getGameRefereeByGame(Game game);

    @Transactional
    @Modifying
    @Query("DELETE FROM GameReferee gr WHERE gr.game.gameId = :idGame")
    void deleteByGame(@Param("idGame") Integer idGame);
}
