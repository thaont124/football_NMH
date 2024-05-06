package com.gr.football.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefereeResponse {
    private String refereeCode;
    private String refereeName;

    private String refereePosition;
}
