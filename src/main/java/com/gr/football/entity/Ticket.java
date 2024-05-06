package com.gr.football.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gameStadiumId;

    private String ticketCode;

    private String stand; //khán đài

    private Integer ticketRow; //hàng

    private String gate; //cổng

    private String seat;

    private Double price;

    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id", referencedColumnName = "gameId")
    private Game game;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stadium_id", referencedColumnName = "stadiumId")
    private Stadium stadium;

}
