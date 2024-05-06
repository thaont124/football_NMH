package com.gr.football.service.impl;

import com.gr.football.dto.response.*;
import com.gr.football.entity.*;
import com.gr.football.repository.*;
import com.gr.football.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private RefereeRepository refereeRepository;
    @Autowired
    private GameRefereeRepository gameRefereeRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private RoundRepository roundRepository;

    @Override
    public List<GameRefereeResponse> getListGameWithReferee(Integer idSeason) {
        List<Round> rounds = roundRepository.findBySeasonSeasonId(idSeason);

        for (Round round : rounds) {
            List<Game> games = round.getGames();
            for (Game game : games) {
                gameRefereeRepository.deleteByGame(game.getGameId());
            }
        }


        List<Game> games = gameRepository.findAll();
        List<Referee> referees = refereeRepository.findAll();

        Map<LocalDate, List<Game>> gamesByDate = games.stream()
                .collect(Collectors.groupingBy(game -> game.getTime().toLocalDate()));

        // Random phân công trọng tài cho các trận cùng ngày
        List<GameReferee> gameRefereeList = new ArrayList<>();
        for (List<Game> gamesSameDate : gamesByDate.values()) {
            List<Referee> allReferee = new ArrayList<>(referees);
            for (Game game : gamesSameDate) {
                List<Referee> suitableReferees = filterSuitableReferees(game.getHomeTeam().getHometown(), game.getAwayTeam().getHometown(), allReferee);
                List<Referee> suitableMainReferees = filterSuitableMainReferees(game.getHomeTeam().getHometown(), game.getAwayTeam().getHometown(), suitableReferees);
                Collections.shuffle(suitableReferees);
                Collections.shuffle(suitableMainReferees);
                Referee mainReferee = suitableMainReferees.get(0);
                Referee lineman1 = suitableReferees.get(1).equals(mainReferee) ? suitableReferees.get(4) : suitableReferees.get(1);
                Referee lineman2 = suitableReferees.get(2).equals(mainReferee) ? suitableReferees.get(4) : suitableReferees.get(2);

                Referee other = suitableReferees.get(3).equals(mainReferee) ? suitableReferees.get(4) : suitableReferees.get(3);
                gameRefereeList.add(new GameReferee(null, game, mainReferee, "main"));
                gameRefereeList.add(new GameReferee(null, game, lineman1, "lineman1"));
                gameRefereeList.add(new GameReferee(null, game, lineman2, "lineman2"));
                gameRefereeList.add(new GameReferee(null, game, other, "other"));

//                suitableMainReferees.remove(mainReferee);
                allReferee.remove(mainReferee);
                allReferee.remove(lineman1);
                allReferee.remove(lineman2);
                allReferee.remove(other);

            }
        }

        // Lưu thông tin trọng tài đã được phân công
        List<GameReferee> savedGameReferee = gameRefereeRepository.saveAll(gameRefereeList);

        // Xây dựng danh sách phản hồi
        List<GameRefereeResponse> gameRefereeListResponse = new ArrayList<>();
        for (Game game : games) {
            GameRefereeResponse gameRefereeResponse = new GameRefereeResponse();
            List<GameReferee> gameReferees = gameRefereeRepository.getGameRefereeByGame(game);

            // Thiết lập thông tin trọng tài
            List<RefereeResponse> otherReferee = new ArrayList<>();
            for (GameReferee gameReferee : gameReferees) {
                Referee referee = gameReferee.getReferee();
                if (gameReferee.getRefereePosition().equals("main")) {
                    gameRefereeResponse.setMainReferee(new RefereeResponse(referee.getRefereeCode(), referee.getRefereeName(), gameReferee.getRefereePosition()));
                } else {
                    otherReferee.add(new RefereeResponse(referee.getRefereeCode(), referee.getRefereeName(), gameReferee.getRefereePosition()));
                }
            }
            gameRefereeResponse.setOtherReferee(otherReferee);

            // Thiết lập thông tin trận đấu
            Ticket ticket = game.getTickets().get(0);
            GameResponse gameResponse = new GameResponse();
            gameResponse.setStadium(ticket.getStadium().getStadiumName());
            gameResponse.setHomeTeamName(game.getHomeTeam().getTeamName());
            gameResponse.setAwayTeamName(game.getAwayTeam().getTeamName());
            gameResponse.setIdGame(ticket.getGame().getGameId());
            gameRefereeResponse.setGameResponse(gameResponse);

            // Thiết lập thông tin vòng đấu
            String round = "Vòng " + ticket.getGame().getRound().getRoundNumber() + " - " + ticket.getGame().getTime();
            gameRefereeResponse.setRound(round);

            gameRefereeListResponse.add(gameRefereeResponse);
        }
        return gameRefereeListResponse;
    }


    private List<Referee> filterSuitableReferees(String homeTeamHometown, String awayTeamHometown, List<Referee> referees) {
        return referees.stream()
                .filter(referee -> !referee.getHometown().equals(homeTeamHometown) && !referee.getHometown().equals(awayTeamHometown))
                .collect(Collectors.toList());
    }
    private List<Referee> filterSuitableMainReferees(String homeTeamHometown, String awayTeamHometown, List<Referee> referees) {
        return referees.stream()
                .filter(referee -> referee.getYearExperience() >= 5 && referee.getStatus() == 1 &&
                        !referee.getHometown().equals(homeTeamHometown) && !referee.getHometown().equals(awayTeamHometown))
                .collect(Collectors.toList());
    }

    @Override
    public List<GameRefereeResponse> getListGame(Integer idSeason) {
        List<Round> rounds = roundRepository.findBySeasonSeasonId(idSeason);

        List<GameReferee> gameRefereeList = new ArrayList<>();
        for (Round round : rounds) {
            List<Game> games = round.getGames();
            for (Game game : games) {
                gameRefereeList.addAll(gameRefereeRepository.getGameRefereeByGame(game));
            }
        }

        List<Game> games = gameRepository.findAll();

        // Xây dựng danh sách phản hồi
        List<GameRefereeResponse> gameRefereeListResponse = new ArrayList<>();
        for (Game game : games) {
            GameRefereeResponse gameRefereeResponse = new GameRefereeResponse();
            List<GameReferee> gameReferees = gameRefereeRepository.getGameRefereeByGame(game);

            // Thiết lập thông tin trọng tài
            List<RefereeResponse> otherReferee = new ArrayList<>();
            if(!gameReferees.isEmpty()){
                for (GameReferee gameReferee : gameReferees) {
                    Referee referee = gameReferee.getReferee();
                    if (gameReferee.getRefereePosition().equals("main")) {
                        gameRefereeResponse.setMainReferee(new RefereeResponse(referee.getRefereeCode(), referee.getRefereeName(), gameReferee.getRefereePosition()));
                    } else {
                        otherReferee.add(new RefereeResponse(referee.getRefereeCode(), referee.getRefereeName(), gameReferee.getRefereePosition()));
                    }
                }
                gameRefereeResponse.setOtherReferee(otherReferee);
            }


            // Thiết lập thông tin trận đấu
            Ticket ticket = game.getTickets().get(0);
            GameResponse gameResponse = new GameResponse();
            gameResponse.setStadium(ticket.getStadium().getStadiumName());
            gameResponse.setHomeTeamName(game.getHomeTeam().getTeamName());
            gameResponse.setAwayTeamName(game.getAwayTeam().getTeamName());
            gameResponse.setIdGame(ticket.getGame().getGameId());
            gameRefereeResponse.setGameResponse(gameResponse);

            // Thiết lập thông tin vòng đấu
            String round = "Vòng " + ticket.getGame().getRound().getRoundNumber() + " - " + ticket.getGame().getTime();
            gameRefereeResponse.setRound(round);

            gameRefereeListResponse.add(gameRefereeResponse);
        }
        return gameRefereeListResponse;
    }


    @Override
    public List<StatisticResponse> getStatistic(Integer idSeason) {
        List<Object[]> games = ticketRepository.findGameAndStadiumNameOrderByTicketCountDesc();

        List<StatisticResponse> statistics = new ArrayList<>();
        for (Object[] result : games) {
            Game game = (Game) result[0];
            Long ticketCount = (Long) result[1];
            String stadiumName = (String) result[2]; // Chuyển đổi giá trị từ Long sang String

            StatisticResponse statistic = new StatisticResponse();
            statistic.setTime(game.getTime().toString());
            statistic.setHomeTeam(game.getHomeTeam().getTeamName());
            statistic.setAwayTeam(game.getAwayTeam().getTeamName());
            statistic.setRound(game.getRound().getRoundNumber());
            statistic.setStadium(stadiumName);
            statistic.setNumberSpectator(ticketCount.intValue()); // Chuyển đổi Long thành int
            statistics.add(statistic);
        }

        List<StatisticResponse> sortedStatistics = statistics.stream()
                .sorted(Comparator.comparingInt(StatisticResponse::getNumberSpectator).reversed())
                .collect(Collectors.toList());
        return sortedStatistics;
    }


}
