package com.gr.football.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticResponse {
    private String time;
    private String homeTeam;
    private String awayTeam;
    private Integer round;
    private String stadium;
    private Integer numberSpectator;
}
