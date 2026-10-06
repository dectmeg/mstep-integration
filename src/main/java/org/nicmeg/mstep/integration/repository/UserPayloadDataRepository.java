package org.nicmeg.mstep.integration.repository;


import org.nicmeg.mstep.integration.entity.UserPayloadData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPayloadDataRepository
        extends JpaRepository<UserPayloadData, Long> {

    Optional<UserPayloadData> findByUserId(Long userId);
}