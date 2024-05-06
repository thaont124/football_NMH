package com.gr.football.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer seasonId;

    private String seasonName;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    private List<Round> rounds;

}
