package com.cheeto.linkedin.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Profile("local")
public class LocalDBResetService {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void reset() {
        jdbcTemplate.execute("PRAGMA foreign_keys = OFF");

        jdbcTemplate.update("DELETE FROM search_profile_extended_flds");
        jdbcTemplate.update("DELETE FROM search_profiles");
        jdbcTemplate.update("DELETE FROM users");

        jdbcTemplate.update("DELETE FROM sqlite_sequence WHERE name = 'search_profile_extended_flds'");
        jdbcTemplate.update("DELETE FROM sqlite_sequence WHERE name = 'search_profiles'");
        jdbcTemplate.update("DELETE FROM sqlite_sequence WHERE name = 'users'");

        jdbcTemplate.execute("PRAGMA foreign_keys = ON");
    }
}
