package com.github.dmhenry.whoami.application;

import com.github.dmhenry.whoami.application.model.*;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Component
public class StandardGameService extends AbstractGameService {

    @Override
    public Game setupGame() {
        List<Profile> profiles = getProfiles();

        String id = getNextId();
        Set<Candidate> candidates = selectRandomCandidates(profiles, getDefaultCandidateCount());
        Candidate solution = selectRandomSolution(candidates);
        Game game = new StandardGame(candidates, solution, id);
        save(game);

        return game;
    }

    @Override
    public List<Game> getGames() {
        return super.getGames().stream()
                    .filter(game -> game instanceof StandardGame)
                    .collect(Collectors.toList());
    }

    Set<Candidate> selectRandomCandidates(List<Profile> profiles, int numCandidates) {
        Set<Candidate> candidates = new LinkedHashSet<>();
        int candidateCount = 0;

        while (candidateCount < numCandidates) {
            int randomProfileIndex = (int) (Math.random() * profiles.size());
            Profile randomProfile = profiles.get(randomProfileIndex);
            String fullName = randomProfile.getFirstName() + " " + randomProfile.getLastName();
            String headshotUrl = (randomProfile.getHeadshot() == null) ? null : randomProfile.getHeadshot().getUrl();
            String id = randomProfile.getId();

            if (isNotBlank(fullName) && isNotBlank(headshotUrl) && isNotBlank(id)) {
                candidates.add(new StandardCandidate(candidateCount, headshotUrl, id, fullName));
            }
            candidateCount = candidates.size();
        }
        return candidates;
    }

}
