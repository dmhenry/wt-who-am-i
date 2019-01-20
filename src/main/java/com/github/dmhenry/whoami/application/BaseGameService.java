package com.github.dmhenry.whoami.application;

import com.github.dmhenry.whoami.application.model.Candidate;
import com.github.dmhenry.whoami.application.model.Game;
import com.github.dmhenry.whoami.data.profile.ProfileDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

abstract class BaseGameService {

    // TODO: add proper cache
    private final Map<String, Game> gameCache = new ConcurrentHashMap<>();

    private static final int DEFAULT_CANDIDATE_COUNT = 6;
    private static AtomicLong id = new AtomicLong();

    @Autowired
    ProfileDao profileDao;

    public abstract Game setupGame();

    public Game guess(String gameId, int candidateIndex) {
        Game game = fetch(gameId);
        Candidate candidate = game.getCandidates().get(candidateIndex);
        game.isSolution(candidate);
        return game;
    }

    public Game getGame(String gameId) {
        return gameCache.get(gameId);
    }

    public List<Game> getGames() {
        return new ArrayList<>(gameCache.values());
    }

    static int getDefaultCandidateCount() {
        return DEFAULT_CANDIDATE_COUNT;
    }

    boolean save(Game game) {
        return (gameCache.put(game.getId(), game) == null);
    }

    private Game fetch(String id) {
        return gameCache.get(id);
    }

    String getNextId() {
        return Long.toString(id.incrementAndGet());
    }

    Candidate selectRandomSolution(Set<Candidate> candidates) {
        int randomCandidate = (int) (Math.random() * candidates.size());
        return new ArrayList<>(candidates).get(randomCandidate);
    }

}
