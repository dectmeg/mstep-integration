package org.nicmeg.mstep.integration.repository;

import org.nicmeg.mstep.integration.entity.NcsPushStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NcsPushStatusRepository
        extends JpaRepository<NcsPushStatus, Long> {

    Optional<NcsPushStatus> findTopByUserIdAndApi_IdOrderByCreatedAtDesc(
            Long userId,
            Long apiId
    );
}