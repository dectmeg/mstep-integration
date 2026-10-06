package org.nicmeg.mstep.integration.repository;


import org.nicmeg.mstep.integration.entity.ApiParameter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApiParameterRepository
        extends JpaRepository<ApiParameter, Long> {

    List<ApiParameter> findByApi_Id(Long apiId);
}
