package com.cheeto.linkedin.service.mappers;

import com.cheeto.jobfilter.model.SearchProfileExtendedFieldDTO;
import com.cheeto.linkedin.service.models.SearchProfileExtendedFieldDB;
import org.springframework.stereotype.Component;

@Component
public class SearchProfileExtendedFieldDTOMapper {

    public static SearchProfileExtendedFieldDB toDao(Long searchProfileId, SearchProfileExtendedFieldDTO dto) {
        return SearchProfileExtendedFieldDB.builder()
                .fieldKey(dto.getFieldKey())
                .value(dto.getValue())
                .weight(MapperUtils.toBigDecimal(dto.getWeight()))
                .isRequired(dto.getIsRequired())
                .build();
    }

    public static SearchProfileExtendedFieldDTO toDto(SearchProfileExtendedFieldDB dao) {
        return SearchProfileExtendedFieldDTO.builder()
                .fieldKey(dao.getFieldKey())
                .value(dao.getValue())
                .weight(MapperUtils.toDouble(dao.getWeight()))
                .isRequired(dao.getIsRequired())
                .build();

    }
}
