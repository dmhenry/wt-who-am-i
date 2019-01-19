package com.github.dmhenry.whoami.data.profile;

import com.github.dmhenry.whoami.application.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
class ProfileClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${profiles.url}")
    private String profilesUrl;

    List<Profile> getProfiles() {
        ResponseEntity<List<Profile>> responseEntity = restTemplate
                .exchange(profilesUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Profile>>() {
                });
        return responseEntity.getBody();
    }

}
