package com.cheeto.linkedin.service.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchProfileDB {
    private Long id;
    private Long userId;
    private String name;
    private Boolean isDefault;
    private Integer minScore;
    private String customInstructions;
    private List<SearchProfileExtendedFieldDB> extendedValues = new ArrayList<>();
}