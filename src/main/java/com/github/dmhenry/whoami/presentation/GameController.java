package com.github.dmhenry.whoami.presentation;

import com.github.dmhenry.whoami.application.GameService;
import com.github.dmhenry.whoami.application.model.Game;
import com.github.dmhenry.whoami.application.model.Guess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1.0/games/")
class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("standard")
    ResponseEntity<Game> setupNewGame() {
        Game game = gameService.setupGame();

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newGameUri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(game.getId()).toUri();
        responseHeaders.setLocation(newGameUri);
        return new ResponseEntity<>(game, responseHeaders, HttpStatus.CREATED);
    }

    @GetMapping("standard/{id}")
    ResponseEntity<Game> getGame(@PathVariable String id) {
        Game game = gameService.getGame(id);
        return new ResponseEntity<>(game, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("standard/{id}")
    ResponseEntity<Game> guess(@PathVariable String id, @RequestBody Guess guess) {
        Game game = gameService.guess(id, guess.getCandidateId());
        return new ResponseEntity<>(game, new HttpHeaders(), HttpStatus.OK);
    }

}
