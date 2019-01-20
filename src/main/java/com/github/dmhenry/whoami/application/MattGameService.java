package com.github.dmhenry.whoami.application;

import com.github.dmhenry.whoami.application.model.Candidate;
import com.github.dmhenry.whoami.application.model.Game;
import com.github.dmhenry.whoami.application.model.MattGame;
import com.github.dmhenry.whoami.application.model.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MattGameService extends StandardGameService {

    @Override
    public Game setupGame() {
        List<Profile> profiles = filter(getProfiles());

        String id = getNextId();
        Set<Candidate> candidates = selectRandomCandidates(profiles, getDefaultCandidateCount());
        Candidate solution = selectRandomSolution(candidates);
        Game game = new MattGame(candidates, solution, id);
        save(game);

        return game;
    }

    @Override
    public List<Game> getGames() {
        return super.getGames().stream()
                    .filter(game -> game instanceof MattGame)
                    .collect(Collectors.toList());
    }

    private List<Profile> filter(List<Profile> profiles) {
        return profiles.stream()
                       .filter(profile -> profile.getFirstName().startsWith("Matt"))
                       .collect(Collectors.toList());
    }

}
