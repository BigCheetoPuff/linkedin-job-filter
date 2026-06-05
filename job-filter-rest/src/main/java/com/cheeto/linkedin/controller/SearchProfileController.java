package com.cheeto.linkedin.controller;

import com.cheeto.jobfilter.api.SearchProfilesApi;
import com.cheeto.jobfilter.model.EmptyResponse;
import com.cheeto.jobfilter.model.GetSearchProfilesResponse;
import com.cheeto.jobfilter.model.SearchProfileRequest;
import com.cheeto.jobfilter.model.SearchProfileResponse;
import org.springframework.http.ResponseEntity;

public class SearchProfileController implements SearchProfilesApi {

    @Override
    public ResponseEntity<SearchProfileResponse> createSearchProfile(Long userId, SearchProfileRequest searchProfileRequest) {
        return null;
    }

    @Override
    public ResponseEntity<EmptyResponse> deleteSearchProfile(Long userId, Long searchProfileId) {
        return null;
    }

    @Override
    public ResponseEntity<SearchProfileResponse> getSearchProfile(Long userId, Long searchProfileId) {
        return null;
    }

    @Override
    public ResponseEntity<GetSearchProfilesResponse> getSearchProfiles(Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<SearchProfileResponse> updateSearchProfile(Long userId, Long searchProfileId, SearchProfileRequest searchProfileRequest) {
        return null;
    }
}
