package com.cheeto.linkedin.controller;

import com.cheeto.jobfilter.api.JobScansApi;
import com.cheeto.jobfilter.model.BasicJobScanRequest;
import com.cheeto.jobfilter.model.BasicJobScanResponse;
import org.springframework.http.ResponseEntity;

public class JobScanController implements JobScansApi {

    @Override
    public ResponseEntity<BasicJobScanResponse> submitBasicJobScan(BasicJobScanRequest basicJobScanRequest) {
        return null;
    }
}
