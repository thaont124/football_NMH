package com.gr.football.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameResponse {
    private Integer idGame;
    private String stadium;
    private String homeTeamName;
    private String awayTeamName;
}
