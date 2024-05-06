package com.gr.football.controller;

import com.gr.football.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/apiv1/referee")
public class RefereeController {
    @Autowired
    private GameService gameService;
    @GetMapping("/withReferee/{idSeason}")
    public ResponseEntity<?> getListGameWithReferee(@PathVariable("idSeason") Integer idSeason){
        return new ResponseEntity<>(gameService.getListGameWithReferee(idSeason), HttpStatus.OK);
    }
}
