package com.github.dmhenry.whoami.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public abstract class Game extends ResourceSupport {

    private final String gameId;
    private boolean solved;
    private Integer guessesToSolve;
    private int guesses;
    private final List<Candidate> candidates = new ArrayList<>();

    @JsonIgnore
    private final Candidate solution;

    public Game(Set<? extends Candidate> candidates, Candidate solution, String gameId) {
        if (!candidates.contains(solution)) {
            throw new IllegalStateException("candidates must include solution");
        }

        this.candidates.addAll(candidates);
        this.solution = solution;
        this.gameId = gameId;
    }

    public String getGameId() {
        return gameId;
    }

    public boolean isSolved() {
        return solved;
    }

    public int getGuesses() {
        return guesses;
    }

    public List<Candidate> getCandidates() {
        return Collections.unmodifiableList(candidates);
    }

    public Candidate getSolution() {
        return solution;
    }

    public Integer getGuessesToSolve() {
        return guessesToSolve;
    }

    public boolean isSolution(Candidate candidate) {
        ++guesses;
        boolean correct = candidate.equals(solution);
        if (correct) {
            if (!solved) {
                guessesToSolve = guesses;
            }
            solved = true;
        }
        return correct;
    }

}
