package com.github.dmhenry.whoami.application.model;

import java.util.Set;

public class InvertedGame extends Game {

    private final String imageOfPersonToIdentify;

    public InvertedGame(Set<Candidate> candidates, Candidate solution, String id) {
        super(candidates, solution, id);
        this.imageOfPersonToIdentify = solution.getHeadshotUrl();
    }

    public String getImageOfPersonToIdentify() {
        return imageOfPersonToIdentify;
    }

}
