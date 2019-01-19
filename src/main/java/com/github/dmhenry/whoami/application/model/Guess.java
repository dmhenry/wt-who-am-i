package com.github.dmhenry.whoami.application.model;

import javax.validation.constraints.NotNull;

public class Guess {

    @NotNull
    private int candidateId;

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

}
