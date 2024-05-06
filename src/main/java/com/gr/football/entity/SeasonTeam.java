package com.gr.football.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SeasonTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seasonTeamId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "season_id")
    private Season season;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stadium_id")
    private Stadium stadium;
}
