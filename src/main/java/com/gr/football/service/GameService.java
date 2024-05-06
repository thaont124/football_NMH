package com.gr.football.service;

import com.gr.football.dto.response.GameRefereeResponse;
import com.gr.football.dto.response.RoundOfGameResponse;
import com.gr.football.dto.response.StatisticResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface GameService {
    List<GameRefereeResponse> getListGameWithReferee(Integer idSeason);

    List<GameRefereeResponse> getListGame(Integer idSeason);

    List<StatisticResponse> getStatistic(Integer idSeason);
}
