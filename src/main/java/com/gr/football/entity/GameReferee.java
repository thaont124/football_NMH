package com.gr.football.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameReferee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gameRefereeId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id", referencedColumnName = "gameId")
    private Game game;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "referee_id")
    private Referee referee;

    private String refereePosition;
}
