package com.gr.football.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameRefereeResponse {
    private String round;
    private GameResponse gameResponse;
    private List<RefereeResponse> otherReferee;
    private RefereeResponse mainReferee;

}
