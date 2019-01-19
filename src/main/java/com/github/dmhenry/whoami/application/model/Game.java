package com.github.dmhenry.whoami.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Game {

    private final String id;
    private final String personToIdentify;
    private boolean solved;
    private int guesses;
    private final List<Candidate> candidates = new ArrayList<>();

    @JsonIgnore
    private final Candidate solution;

    public Game(Set<Candidate> candidates, Candidate solution, String id) {
        if (!candidates.contains(solution)) {
            throw new IllegalStateException("candidates must include solution");
        }

        this.candidates.addAll(candidates);
        this.solution = solution;
        this.personToIdentify = solution.getFullName();
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getPersonToIdentify() {
        return personToIdentify;
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

    public boolean isSolution(Candidate candidate) {
        boolean correct = candidate.equals(solution);
        if (correct) {
            solved = true;
        } else if (!solved) {
            ++guesses;
        }
        return correct;
    }

}