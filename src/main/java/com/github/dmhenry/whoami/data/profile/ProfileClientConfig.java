package com.github.dmhenry.whoami.data.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:static/endpoints.properties")
class ProfileClientConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
