package com.gr.football.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roundId;

    private Integer roundNumber;

    @OneToMany(mappedBy = "round", cascade = CascadeType.ALL)
    private List<Game> games;

    @ManyToOne
    @JoinColumn(name = "season_id")
    private Season season;
}
