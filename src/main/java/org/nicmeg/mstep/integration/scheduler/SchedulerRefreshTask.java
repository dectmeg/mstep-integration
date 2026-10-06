package org.nicmeg.mstep.integration.scheduler;

import java.util.List;

import org.nicmeg.mstep.integration.entity.Control;
import org.nicmeg.mstep.integration.repository.ControlRepository;
import org.nicmeg.mstep.integration.service.DynamicSchedulerService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SchedulerRefreshTask {

    private final ControlRepository repository;
    private final DynamicSchedulerService schedulerService;

    // @Scheduled(fixedDelay = 30000)
    @Scheduled(cron = "0 0 12 * * *")
    public void refreshSchedulers() {

        List<Control> controls = repository.findAll();

        for (Control control : controls) {

            if (Boolean.TRUE.equals(control.getControlStatus())) {

                schedulerService.startJob(
                        control.getOrgName(),
                        control.getScheduledTime()
                );

            } else {

                schedulerService.stopJob(
                        control.getOrgName()
                );
            }
        }
    }
}
