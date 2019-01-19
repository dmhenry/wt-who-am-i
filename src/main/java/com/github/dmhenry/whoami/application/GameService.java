package com.github.dmhenry.whoami.application;

import com.github.dmhenry.whoami.application.model.Candidate;
import com.github.dmhenry.whoami.application.model.Game;
import com.github.dmhenry.whoami.application.model.Profile;
import com.github.dmhenry.whoami.data.profile.ProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class GameService {

    private static final int DEFAULT_CANDIDATE_COUNT = 6;

    // TODO: get from database
    private static AtomicLong id = new AtomicLong();
    private static final ConcurrentHashMap<String, Game> GAMES = new ConcurrentHashMap<>();

    @Autowired
    private ProfileDao profileDao;

    public Game setupGame() {
        List<Profile> profiles = profileDao.getProfiles();

        String id = Long.toString(GameService.id.incrementAndGet());
        Set<Candidate> candidates = selectRandomCandidates(DEFAULT_CANDIDATE_COUNT, profiles);
        Candidate solution = selectRandomSolution(candidates);
        Game game = new Game(candidates, solution, id);
        GAMES.put(id, game);

        return game;
    }

    public Game guess(String gameId, int candidateIndex) {
        Game game = GAMES.get(gameId);
        Candidate candidate = game.getCandidates().get(candidateIndex);
        game.isSolution(candidate);
        return game;
    }

    public Game getGame(String gameId) {
        return GAMES.get(gameId);
    }

    private Set<Candidate> selectRandomCandidates(int numCandidates, List<Profile> profiles) {
        LinkedHashSet<Candidate> candidates = new LinkedHashSet<>();
        int candidateCount = 0;

        while (candidateCount < numCandidates) {
            int randomProfileIndex = (int) (Math.random() * profiles.size());
            Profile randomProfile = profiles.get(randomProfileIndex);
            String fullName = randomProfile.getFirstName() + " " + randomProfile.getLastName();
            String headshotUrl = randomProfile.getHeadshot().getUrl();
            String id = randomProfile.getId();

            candidates.add(new Candidate(candidateCount, headshotUrl, id, fullName));
            candidateCount = candidates.size();
        }
        return candidates;
    }

    private Candidate selectRandomSolution(Set<Candidate> candidates) {
        int randomCandidate = (int) (Math.random() * candidates.size());
        return new ArrayList<>(candidates).get(randomCandidate);
    }

}
