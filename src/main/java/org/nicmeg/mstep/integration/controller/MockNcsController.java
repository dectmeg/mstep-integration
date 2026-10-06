package org.nicmeg.mstep.integration.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/integration-service/api")
public class MockNcsController {

    @PostMapping("/v1/auth/client/token")
    public Map<String, Object> token() {

        return Map.of(
                "status", "SUCCESS",
                "statusCode", 200,
                "message", "Token generated successfully",
                "data", Map.of(
                        "accessToken", "mock-token",
                        "refreshToken", "mock-refresh",
                        "tokenType", "Bearer",
                        "expiresIn", 259200
                )
        );
    }

    @PostMapping("/users/save-Jobseeker")
    public Map<String, Object> saveJobseeker(
            @RequestBody Map<String, Object> request) {

        return Map.of(
                "status", "SUCCESS",
                "statusCode", 200,
                "message", "User registered successfully",
                "data", Map.of(
                        "userId",
                        java.util.UUID.randomUUID().toString()
                )
        );
    }
}