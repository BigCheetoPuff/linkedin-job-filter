package com.cheeto.linkedin.domain.dao;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class SearchProfile {
    private Long id;
    private Long userId;

    private String name;

    private String careerGoals;
    private String companyCulturePreferences;
    private String remoteWorkPreferences;
    private String customInstructions;

    private Boolean isDefault;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<Criterion> criteria = new ArrayList<>();
}