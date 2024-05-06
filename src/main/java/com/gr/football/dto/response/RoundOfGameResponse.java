package com.gr.football.dto.response;

import lombok.Data;

@Data
public class RoundOfGameResponse {
    private String round;
    private GameResponse gameResponse;
}
