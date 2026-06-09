package com.cheeto.linkedin.controller;

import com.cheeto.jobfilter.api.SearchProfilesApi;
import com.cheeto.jobfilter.model.*;
import com.cheeto.linkedin.service.SearchProfileService;
import com.cheeto.linkedin.service.mappers.SearchProfileDTOMapper;
import com.cheeto.linkedin.service.models.SearchProfileDB;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchProfileController implements SearchProfilesApi {

    private final SearchProfileService searchProfileService;

    @Override
    public ResponseEntity<CreateSearchProfileResponse> createSearchProfile(Long userId, CreateSearchProfileRequest searchProfileRequest) {
        Long searchProfileId = searchProfileService.createSearchProfile(SearchProfileDTOMapper.toDao(searchProfileRequest.getSearchProfile()));
        CreateSearchProfileResponse response = CreateSearchProfileResponse.builder().id(searchProfileId).build();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<EmptyResponse> deleteSearchProfile(Long userId, Long searchProfileId) {
        return null;
    }

    @Override
    public ResponseEntity<GetSearchProfileResponse> getSearchProfile(Long userId, Long searchProfileId) {
        return ResponseEntity.ok(
                GetSearchProfileResponse.builder()
                        .searchProfile(
                                SearchProfileDTOMapper.toDto(searchProfileService.getSearchProfileById(searchProfileId))
                        )
                        .build()
        );
    }

    @Override
    public ResponseEntity<GetSearchProfilesResponse> getSearchProfiles(Long userId) {
        return ResponseEntity.ok(
                GetSearchProfilesResponse.builder()
                        .searchProfiles(
                                searchProfileService.getSearchProfilesByUserId(userId)
                                        .stream()
                                        .map(SearchProfileDTOMapper::toDto)
                                        .toList()
                        )
                        .build()
        );
    }

    @Override
    public ResponseEntity<UpdateSearchProfileResponse> updateSearchProfile(Long userId, Long searchProfileId, UpdateSearchProfileRequest searchProfileRequest) {
        return null;
    }
}
