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
