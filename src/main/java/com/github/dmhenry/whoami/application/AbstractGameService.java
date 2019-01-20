package com.github.dmhenry.whoami.application;

import com.github.dmhenry.whoami.application.model.Candidate;
import com.github.dmhenry.whoami.application.model.Game;
import com.github.dmhenry.whoami.application.model.Profile;
import com.github.dmhenry.whoami.data.game.GameDao;
import com.github.dmhenry.whoami.data.profile.ProfileDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

abstract class AbstractGameService {

    private static final int DEFAULT_CANDIDATE_COUNT = 6;

    @Autowired
    private ProfileDao profileDao;
    @Autowired
    private GameDao gameDao;

    public abstract Game setupGame();

    static int getDefaultCandidateCount() {
        return DEFAULT_CANDIDATE_COUNT;
    }

    public Game guess(String gameId, int candidateIndex) {
        Game game = fetch(gameId);
        Candidate candidate = game.getCandidates().get(candidateIndex);
        game.isSolution(candidate);
        return game;
    }


    public List<Game> getGames() {
        return gameDao.getGames();
    }

    public Game getGame(String gameId) {
        return gameDao.getGame(gameId);
    }

    boolean save(Game game) {
        return gameDao.save(game);
    }

    Game fetch(String id) {
        return gameDao.fetch(id);
    }

    List<Profile> getProfiles() {
        return profileDao.getProfiles();
    }

    String getNextId() {
        return Long.toString(gameDao.getNextId());
    }

    Candidate selectRandomSolution(Set<Candidate> candidates) {
        int randomCandidate = (int) (Math.random() * candidates.size());
        return new ArrayList<>(candidates).get(randomCandidate);
    }

}
