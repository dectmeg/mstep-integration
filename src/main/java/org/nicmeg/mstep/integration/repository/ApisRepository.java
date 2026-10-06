package org.nicmeg.mstep.integration.repository;


import org.nicmeg.mstep.integration.entity.Apis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApisRepository extends JpaRepository<Apis, Long> {

    List<Apis> findByControl_OrgNameAndApiStatusTrueOrderByApiSequenceAsc(String orgName);
}
