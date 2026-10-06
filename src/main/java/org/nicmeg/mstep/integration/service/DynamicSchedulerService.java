package org.nicmeg.mstep.integration.service;

import java.util.List;
import java.util.Map;

import org.nicmeg.mstep.integration.entity.Apis;
import org.nicmeg.mstep.integration.repository.ApisRepository;
import org.nicmeg.mstep.integration.repository.CandidateDetailsRepository;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DynamicSchedulerService {

    private final TaskScheduler taskScheduler;
    private final ApisRepository apisRepository;
    private final ApiExecutorService apiExecutorService;
    private final CandidateDetailsRepository candidateDetailsRepository;

    private final Map<String, ScheduledFuture<?>> jobs = new ConcurrentHashMap<>();

    public void startJob(String orgName, String cron) {

        stopJob(orgName);

        ScheduledFuture<?> future = taskScheduler.schedule(
                () -> execute(orgName),
                new CronTrigger(cron));

        jobs.put(orgName, future);
    }

    public void stopJob(String orgName) {

        ScheduledFuture<?> future = jobs.remove(orgName);

        if (future != null) {
            future.cancel(false);
        }
    }

    private void execute(String orgName) {

        List<Apis> apis = apisRepository.findByControl_OrgNameAndApiStatusTrueOrderByApiSequenceAsc(orgName);

        System.out.println("Running job for org: " + orgName);

        for (Apis api : apis) {

            List<Long> candidateIds = candidateDetailsRepository
                    .findPendingCandidateIds(
                            api.getId());

            System.out.println(
                    "Pending Candidates : "
                            + candidateIds.size());

            for (Long candidateId : candidateIds) {

                try {

                    apiExecutorService.executeApi(
                            api,
                            candidateId);

                } catch (Exception ex) {

                    System.out.println(
                            "API Failed : "
                                    + api.getApiUrl());

                    ex.printStackTrace();
                }
            }
        }
    }
}
