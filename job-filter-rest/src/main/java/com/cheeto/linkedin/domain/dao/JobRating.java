package com.cheeto.linkedin.domain.dao;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobRating {
    private Long id;

    private Long userId;
    private Long jobId;
    private Long searchProfileId;

    private String ratingType;

    private Integer overallScore;
    private String decision;

    private Integer titleMatchScore;
    private Integer careerGoalScore;
    private Integer companyCultureScore;
    private Integer remoteWorkScore;
    private Integer stabilityScore;

    private String summary;

    private String reasonsJson;
    private String concernsJson;

    private LocalDateTime createdAt;
}
