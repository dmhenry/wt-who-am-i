package com.github.dmhenry.whoami.api;

import com.github.dmhenry.whoami.application.GameService;
import com.github.dmhenry.whoami.application.model.Game;
import com.github.dmhenry.whoami.application.model.Guess;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1.0/games/")
@Api(value = "Who Am I?", description = "A game to match names to faces.")
@SuppressWarnings("unused")
class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("standard")
    @ApiOperation(value = "Set up a new game.", response = Game.class)
    ResponseEntity<Game> setupNewGame() {
        Game game = gameService.setupGame();

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newGameUri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(game.getId()).toUri();
        responseHeaders.setLocation(newGameUri);
        return new ResponseEntity<>(game, responseHeaders, HttpStatus.CREATED);
    }

    @GetMapping("standard/{id}")
    @ApiOperation(value = "Get the game state.", response = Game.class)
    ResponseEntity<Game> getGame(@PathVariable String id) {
        Game game = gameService.getGame(id);
        return new ResponseEntity<>(game, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("standard/{id}")
    @Valid
    @ApiOperation(value = "Take a stab at matching the person to the image.", response = Game.class)
    ResponseEntity<Game> guess(@PathVariable String id, @RequestBody Guess guess) {
        Game game = gameService.guess(id, guess.getCandidateId());
        return new ResponseEntity<>(game, new HttpHeaders(), HttpStatus.OK);
    }

}
