package com.cheeto.linkedin.service.mappers;

import com.cheeto.jobfilter.model.SearchProfileDTO;
import com.cheeto.jobfilter.model.SearchProfileExtendedFieldDTO;
import com.cheeto.linkedin.service.models.SearchProfileDB;


public class SearchProfileDTOMapper {

    public static SearchProfileDB toDao(
            SearchProfileDTO searchProfileDTO) {

        return SearchProfileDB.builder()
                .userId(searchProfileDTO.getId())
                .name(searchProfileDTO.getName())
                .isDefault(searchProfileDTO.getIsDefault())
                .minScore(searchProfileDTO.getMinScore())
                .customInstructions(searchProfileDTO.getCustomInstructions())
                .extendedValues(
                        searchProfileDTO.getExtendedValues()
                                .stream()
                                .map((SearchProfileExtendedFieldDTO extendedValue) ->
                                        SearchProfileExtendedValueDTOMapper.toDao(
                                                searchProfileDTO.getId(),
                                                extendedValue
                                        ))
                                .toList()
                )
                .build();
    }

    public static SearchProfileDTO toDto(SearchProfileDB dao) {
        return SearchProfileDTO.builder()
                .userId(dao.getUserId())
                .name(dao.getName())
                .isDefault(dao.getIsDefault())
                .minScore(dao.getMinScore())
                .customInstructions(dao.getCustomInstructions())
                .extendedValues(
                        dao.getExtendedValues()
                                .stream()
                                .map(SearchProfileExtendedValueDTOMapper::toDto)
                                .toList()
                )
                .build();
    }

}
