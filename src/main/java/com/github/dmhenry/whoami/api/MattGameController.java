package com.github.dmhenry.whoami.api;

import com.github.dmhenry.whoami.application.MattGameService;
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

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1.0/games/matt")
@Api(value = "Who Am I?", description = "Pick the Matt that matches the name.")
@SuppressWarnings("unused")
class MattGameController {

    @Autowired
    private MattGameService mattGameService;

    @PostMapping
    @ApiOperation(value = "Set up a new Matt-only game.", response = Game.class)
    ResponseEntity<Game> setupNewGame() {
        Game game = mattGameService.setupGame();

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newGameUri =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(game.getGameId()).toUri();
        responseHeaders.setLocation(newGameUri);
        return new ResponseEntity<>(game, responseHeaders, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Get the game state.", response = Game.class)
    ResponseEntity<Game> getGame(@PathVariable String id) {
        Game game = mattGameService.getGame(id);
        return new ResponseEntity<>(game, new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping
    @ApiOperation(value = "Get the Matt game list.", response = List.class)
    ResponseEntity<List<Game>> getGames() {
        List<Game> games = mattGameService.getGames();
        games.forEach(this::updateGameWithLinks);
        return new ResponseEntity<>(games, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("{id}")
    @Valid
    @ApiOperation(value = "Take a stab at matching the Matt to his picture.", response = Game.class)
    ResponseEntity<Game> guess(@PathVariable String id, @RequestBody Guess guess) {
        Game game = mattGameService.guess(id, guess.getCandidateId());
        return new ResponseEntity<>(game, new HttpHeaders(), HttpStatus.OK);
    }

    private void updateGameWithLinks(Game game) {
        game.add(linkTo(methodOn(MattGameController.class).getGames()).slash(game.getGameId()).withSelfRel());
    }

}
