package com.github.dmhenry.whoami.application.model;

import java.util.List;
import java.util.Objects;

public class Profile {

    private String id;
    private String type;
    private String slug;
    private String jobTitle;
    private String firstName;
    private String lastName;
    private ImageMetadata headshot;
    private String bio;
    private List<SocialLink> socialLinks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ImageMetadata getHeadshot() {
        return headshot;
    }

    public void setHeadshot(ImageMetadata headshot) {
        this.headshot = headshot;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<SocialLink> getSocialLinks() {
        return socialLinks;
    }

    public void setSocialLinks(List<SocialLink> socialLinks) {
        this.socialLinks = socialLinks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Profile)) {
            return false;
        }
        Profile profile = (Profile) o;
        return Objects.equals(id, profile.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
