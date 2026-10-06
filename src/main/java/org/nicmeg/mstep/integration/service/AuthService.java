package org.nicmeg.mstep.integration.service;

import java.time.LocalDateTime;
import java.util.Map;

import org.nicmeg.mstep.integration.dto.TokenResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final WebClient webClient;

    private String token;

    private LocalDateTime tokenExpiry;

    public String getToken() {

        if (token != null
                && tokenExpiry != null
                && tokenExpiry.isAfter(LocalDateTime.now())) {

            return token;
        }

        return refreshToken();
    }

    private String refreshToken() {

        TokenResponseDto response = webClient.post()
                .uri("/auth/login")
                .bodyValue(Map.of(
                        "username", "your_username",
                        "password", "your_password"
                ))
                .retrieve()
                .bodyToMono(TokenResponseDto.class) 
                .block();

        this.token = response.getAccessToken();

        this.tokenExpiry =
                LocalDateTime.now().plusMinutes(50);

        return token;
    }
}
