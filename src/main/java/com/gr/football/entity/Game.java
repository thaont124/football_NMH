package com.gr.football.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gameId;

    @ManyToOne
    @JoinColumn(name = "home_team_id", referencedColumnName = "teamId")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id", referencedColumnName = "teamId")
    private Team awayTeam;

    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "round_id")
    private Round round;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}
