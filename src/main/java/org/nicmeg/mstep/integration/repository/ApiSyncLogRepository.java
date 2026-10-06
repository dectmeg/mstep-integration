package org.nicmeg.mstep.integration.repository;

import java.util.List;

import org.nicmeg.mstep.integration.entity.ApiSyncLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiSyncLogRepository
        extends JpaRepository<ApiSyncLog, Long> {

    List<ApiSyncLog>
    findTop500BySyncStatus(String status);
}
