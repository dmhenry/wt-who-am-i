package com.github.dmhenry.whoami.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class Candidate {

    private final Integer id;
    private final String headshotUrl;

    @JsonIgnore
    private final String profileId;
    @JsonIgnore
    private final String fullName;

    public Candidate(Integer id, String headshotUrl, String profileId, String fullName) {
        this.id = id;
        this.headshotUrl = headshotUrl;
        this.profileId = profileId;
        this.fullName = fullName;
    }

    public Integer getId() {
        return id;
    }

    public String getHeadshotUrl() {
        return headshotUrl;
    }

    public String getProfileId() {
        return profileId;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Candidate)) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return profileId.equals(candidate.profileId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileId);
    }

}
