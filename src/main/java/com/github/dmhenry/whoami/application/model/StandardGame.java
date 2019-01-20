package com.github.dmhenry.whoami.application.model;

import java.util.Set;

public class StandardGame extends Game {

    private final String nameOfPersonToIdentify;

    public StandardGame(Set<Candidate> candidates, Candidate solution, String id) {
        super(candidates, solution, id);
        this.nameOfPersonToIdentify = solution.getFullName();
    }

    public String getNameOfPersonToIdentify() {
        return nameOfPersonToIdentify;
    }

}
