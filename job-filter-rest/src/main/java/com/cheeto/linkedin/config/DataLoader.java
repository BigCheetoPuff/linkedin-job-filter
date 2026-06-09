package com.cheeto.linkedin.config;

import com.cheeto.jobfilter.model.*;
import com.cheeto.linkedin.service.SearchProfileService;
import com.cheeto.linkedin.service.UserService;
import com.cheeto.linkedin.service.models.SearchProfileDB;
import com.cheeto.linkedin.service.models.UserDB;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
@Profile("local")
public class DataLoader {

    private final ObjectMapper objectMapper;

    private final UserService userService;
    private final SearchProfileService searchProfileService;

    private final String TEST_EMAIL = "keaton@example.com";

    @jakarta.annotation.Resource
    private ResourceLoader resourceLoader;

    @EventListener(ApplicationReadyEvent.class)
    public void load() throws Exception {


        if (userService.getUserByEmail(TEST_EMAIL).isEmpty()) {
            Resource userResource =
                    resourceLoader.getResource(
                            "classpath:db/migration/seed/create-user.json");

            Resource profileResource =
                    resourceLoader.getResource(
                            "classpath:db/migration/seed/create-search-profile.json");

            UserDB seededUser =
                    objectMapper.readValue(
                            userResource.getInputStream(),
                            UserDB.class);

            userService.createUser(seededUser);


            SearchProfileDB seededSearchProfile =
                    objectMapper.readValue(
                            profileResource.getInputStream(),
                            SearchProfileDB.class);


            seededSearchProfile.setUserId(seededUser.getId());
            
            searchProfileService.createSearchProfile(
                    seededSearchProfile);

            log.info(
                    "Dev data loader complete."
            );
        }
    }
}
