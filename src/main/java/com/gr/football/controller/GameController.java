package com.gr.football.controller;

import com.gr.football.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/apiv1/game")

@CrossOrigin("*")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping("/noReferee/{idSeason}")
    public ResponseEntity<?> getListGameWithReferee(@PathVariable("idSeason") Integer idSeason){
        return new ResponseEntity<>(gameService.getListGame(idSeason), HttpStatus.OK);
    }

    @GetMapping("/statistic/{idSeason}")
    public ResponseEntity<?> getStatistic(@PathVariable("idSeason") Integer idSeason){
        return new ResponseEntity<>(gameService.getStatistic(idSeason), HttpStatus.OK);
    }
}
