package com.cheeto.linkedin.service;

import com.cheeto.linkedin.service.dao.SearchProfileExtendedFieldDao;
import com.cheeto.linkedin.service.mappers.MapperUtils;
import com.cheeto.linkedin.service.models.SearchProfileExtendedFieldDB;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchProfileExtendedFieldService {


    private final SearchProfileExtendedFieldDao searchProfileExtendedFieldDao;

    public void insertAll(List<SearchProfileExtendedFieldDB> extendedFields) {
        MapperUtils.assertUpdatedManyRows(searchProfileExtendedFieldDao.insertAll(extendedFields), extendedFields.size());
    }
}
