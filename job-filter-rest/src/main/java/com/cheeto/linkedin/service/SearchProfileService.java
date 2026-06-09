package com.cheeto.linkedin.service;

import com.cheeto.linkedin.service.dao.SearchProfileDao;
import com.cheeto.linkedin.service.dao.SearchProfileExtendedFieldDao;
import com.cheeto.linkedin.service.mappers.MapperUtils;
import com.cheeto.linkedin.service.models.SearchProfileDB;
import com.cheeto.linkedin.service.models.SearchProfileExtendedFieldDB;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchProfileService {

    private final SearchProfileDao searchProfileDao;
    private final SearchProfileExtendedFieldDao searchProfileExtendedFieldDao;

    public SearchProfileDB getSearchProfileById(Long searchProfileId) {
        return searchProfileDao.findById(searchProfileId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Search profile not found"));
    }

    public List<SearchProfileDB> getSearchProfilesByUserId(Long userId) {
        return searchProfileDao.findByUserId(userId);
    }

    public Long createSearchProfile(SearchProfileDB searchProfile) {
        MapperUtils.assertUpdatedSingleRow(searchProfileDao.insert(searchProfile));
        Long searchProfileId = searchProfile.getId();

        if (searchProfileId != null) {
            searchProfile.getExtendedFields()
                    .forEach((SearchProfileExtendedFieldDB dao) -> dao.setSearchProfileId(searchProfileId));
            
            searchProfileExtendedFieldDao.insertAll(searchProfile.getExtendedFields());
        }

        return searchProfileId;
    }
}
