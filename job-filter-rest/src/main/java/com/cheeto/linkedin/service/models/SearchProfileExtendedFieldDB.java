package com.cheeto.linkedin.service.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchProfileExtendedFieldDB {
    private Long id;
    private Long searchProfileId;
    private String fieldKey;
    private String value;
    private String label;
    private BigDecimal weight;
    private Boolean isRequired;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
