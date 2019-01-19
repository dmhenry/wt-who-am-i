package com.github.dmhenry.whoami.data.profile;

import com.github.dmhenry.whoami.application.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfileDao {

    @Autowired
    private ProfileClient client;

    public List<Profile> getProfiles() {
        return client.getProfiles();
    }

}
