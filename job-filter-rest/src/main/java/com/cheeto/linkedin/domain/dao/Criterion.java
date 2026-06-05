package com.cheeto.linkedin.domain.dao;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Criterion {
    private Long id;
    private Long searchProfileId;

    private String category;
    private String preferenceType;
    private String value;
    private Integer weight;

    private LocalDateTime createdAt;
}