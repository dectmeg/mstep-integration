package org.nicmeg.mstep.integration.service;

import java.time.LocalDateTime;
import java.util.List;

import org.nicmeg.mstep.integration.dto.UserSyncRequestDto;
import org.nicmeg.mstep.integration.entity.ApiSyncLog;
import org.nicmeg.mstep.integration.entity.CandidateDetails;
import org.nicmeg.mstep.integration.repository.ApiSyncLogRepository;
import org.nicmeg.mstep.integration.repository.CandidateDetailsRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSyncService {

    private final ApiSyncLogRepository syncRepo;

    private final CandidateDetailsRepository userRepo;

    private final WebClient webClient;

    private final AuthService authService;

    public void syncPendingUsers() {

        List<ApiSyncLog> pendingUsers =
                syncRepo.findTop500BySyncStatus("PENDING");

        log.info("Total pending users: {}",
                pendingUsers.size());

        for (ApiSyncLog logEntity : pendingUsers) {

            try {

                CandidateDetails user = userRepo.findById(
                        logEntity.getUserId()
                ).orElseThrow();

                UserSyncRequestDto request =
                        UserSyncRequestDto.builder()
                                .fullName(user.getName())
                                .mobileNumber(user.getPhoneNumber())
                                .email(user.getEmail())
                                .gender(user.getGender())
                                // .role(user.get())
                                .build();

                String token = authService.getToken();

                String response = webClient.post()
                        .uri("/users")
                        .header(
                                HttpHeaders.AUTHORIZATION,
                                "Bearer " + token
                        )
                        .bodyValue(request)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();

                logEntity.setSyncStatus("SUCCESS");
                logEntity.setResponseMessage(response);
                logEntity.setSyncedAt(LocalDateTime.now());

            } catch (Exception ex) {

                log.error("Sync failed", ex);

                logEntity.setSyncStatus("FAILED");

                logEntity.setRetryCount(
                        logEntity.getRetryCount() + 1
                );

                logEntity.setResponseMessage(
                        ex.getMessage()
                );
            }

            syncRepo.save(logEntity);
        }
    }
}
