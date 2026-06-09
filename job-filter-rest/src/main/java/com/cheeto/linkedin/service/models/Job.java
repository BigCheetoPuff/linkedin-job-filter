package com.cheeto.linkedin.service.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    private Long id;

    private String source;
    private String externalJobId;
    private String fingerprint;
    private String url;

    private String title;
    private String company;
    private String location;
    private String postedDate;

    private String description;

    private String metadataLevel;

    private LocalDateTime firstSeenAt;
    private LocalDateTime lastSeenAt;
}