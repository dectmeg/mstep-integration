package org.nicmeg.mstep.integration.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.nicmeg.mstep.integration.entity.CandidateDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateDetailsRepository extends JpaRepository<CandidateDetails, Long> {
    @Query("""
            SELECT c.id
            FROM CandidateDetails c
            WHERE c.id NOT IN (
                SELECT n.userId
                FROM NcsPushStatus n
                WHERE n.api.id = :apiId
                AND n.apiStatus = 'SUCCESS'
            )
            """)
    List<Long> findPendingCandidateIds(Long apiId);
}
