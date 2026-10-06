package org.nicmeg.mstep.integration.service;

import java.util.List;
import java.util.Map;

import org.nicmeg.mstep.integration.entity.Apis;
import org.nicmeg.mstep.integration.entity.NcsPushStatus;
import org.nicmeg.mstep.integration.repository.CandidateDetailsRepository;
import org.nicmeg.mstep.integration.repository.NcsPushStatusRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApiExecutorService {

    private final WebClient webClient;
    private final NcsPushStatusRepository statusRepository;
    private final PayloadBuilderService payloadBuilderService;
    private final CandidateDetailsRepository candidateDetailsRepository;

    // public void executeApi(Apis api) {

    // String fullUrl =
    // api.getBaseUrl() +
    // api.getApiUrl();

    // System.out.println("Calling : " + fullUrl);

    // String response = webClient
    // .post()
    // .uri(fullUrl)
    // .retrieve()
    // .bodyToMono(String.class)
    // .block();

    // System.out.println(response);
    // }

    public boolean executeApi(Apis api, Object keyValue) {

        Map<String, Object> payload =
                payloadBuilderService.buildPayload(
                        api.getId(),
                        keyValue);

                System.out.println("fsdfds" + payload);

        String url = api.getBaseUrl() + api.getApiUrl();

        try {

            System.out.println("Calling API : " + url);

            String response = webClient
                    .get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            System.out.println("API Success");
            System.out.println(response);
            // testPendingCandidates(api.getId());

            
            NcsPushStatus status = new NcsPushStatus();
            status.setUserId(1L); // temporary for testing
            status.setApi(api);
            status.setApiStatus("SUCCESS");
            status.setResponseCode(200);
            status.setResponseMessage(response);

            statusRepository.save(status);

            return true;

        } catch (Exception ex) {

            System.out.println("API Failed : " + url);
            System.out.println(ex.getMessage());

            NcsPushStatus status = new NcsPushStatus();
            status.setUserId(1L); // temporary for testing
            status.setApi(api);
            status.setApiStatus("FAILED");
            status.setResponseMessage(ex.getMessage());

            statusRepository.save(status);

            return false;
        }
    }

    public void testPendingCandidates(Long apiId) {

        List<Long> ids =
                candidateDetailsRepository
                        .findPendingCandidateIds(apiId);

        System.out.println("Pending Candidates:");

        ids.forEach(System.out::println);
    }
}
