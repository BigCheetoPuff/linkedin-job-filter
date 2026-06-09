package com.cheeto.linkedin;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootApplication
@MapperScan("com.cheeto.linkedin.service.dao")
public class LinkedinJobFilterApplication {

    private static final Logger log =
            LoggerFactory.getLogger(LinkedinJobFilterApplication.class);

    public static void main(String[] args) throws IOException {
        Path dbPath = Path.of("job-filter.db");

        log.info("Working directory: {}", Path.of("").toAbsolutePath());
        log.info("Deleting local SQLite DB at: {}", dbPath.toAbsolutePath());

        Files.deleteIfExists(dbPath);

        SpringApplication.run(LinkedinJobFilterApplication.class, args);
    }

}
