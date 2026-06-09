package com.cheeto.linkedin.service;

import com.cheeto.linkedin.service.dao.SearchProfileDao;
import com.cheeto.linkedin.service.mappers.MapperUtils;
import com.cheeto.linkedin.service.models.SearchProfileDB;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchProfileService {

    private final SearchProfileDao searchProfileDao;

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
        return searchProfile.getId();
    }
}
