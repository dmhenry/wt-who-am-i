package com.github.dmhenry.whoami.api;

import com.github.dmhenry.whoami.application.InvertedGameService;
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
import java.util.List;

@RestController
@RequestMapping("/api/v1.0/games/inverted")
@Api(value = "Who Am I?", description = "Pick the name that matches the face.")
@SuppressWarnings("unused")
class InvertedGameController {

    @Autowired
    private InvertedGameService invertedGameService;

    @PostMapping
    @ApiOperation(value = "Set up a new inverted game.", response = Game.class)
    ResponseEntity<Game> setupNewGame() {
        Game game = invertedGameService.setupGame();

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newGameUri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(game.getId()).toUri();
        responseHeaders.setLocation(newGameUri);
        return new ResponseEntity<>(game, responseHeaders, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Get the game state.", response = Game.class)
    ResponseEntity<Game> getGame(@PathVariable String id) {
        Game game = invertedGameService.getGame(id);
        return new ResponseEntity<>(game, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value = "Get the inverted game list.", response = List.class)
    ResponseEntity<List<Game>> getGames() {
        List<Game> games = invertedGameService.getGames();
        return new ResponseEntity<>(games, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("{id}")
    @Valid
    @ApiOperation(value = "Take a stab at matching the image to the face.", response = Game.class)
    ResponseEntity<Game> guess(@PathVariable String id, @RequestBody Guess guess) {
        Game game = invertedGameService.guess(id, guess.getCandidateId());
        return new ResponseEntity<>(game, new HttpHeaders(), HttpStatus.OK);
    }

}
