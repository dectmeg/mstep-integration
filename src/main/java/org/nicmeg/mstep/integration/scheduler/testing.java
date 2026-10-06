// package org.nicmeg.mstep.integration.scheduler;

// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Component;
// import org.springframework.transaction.annotation.Transactional;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;

// @Component
// @RequiredArgsConstructor
// @Slf4j
// public class testing {

//     @Scheduled(cron = "0 30 11 * * ?")
//     @Transactional
//     public void testingTriggerOne() {

//         for (int i = 0; i < 5; i++) {
//             log.info("testingTriggerOne i: {}", i);
//         }
//     }

//     @Scheduled(cron = "0 30 11 * * ?")
//     @Transactional
//     public void testingTriggerTwo() {

//         for (int j = 0; j < 5; j++) {
//             log.info("testingTriggerTwo j: {}", j);
//         }
//     }
// }