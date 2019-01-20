package com.github.dmhenry.whoami.application.model;

public abstract class Candidate {

    private final Integer id;

    public Candidate(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public abstract String getHeadshotUrl();

    public abstract String getProfileId();

    public abstract String getFullName();

}
