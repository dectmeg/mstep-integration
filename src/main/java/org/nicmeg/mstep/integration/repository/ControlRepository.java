package org.nicmeg.mstep.integration.repository;

import org.nicmeg.mstep.integration.entity.Control;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlRepository extends JpaRepository<Control, Long> {

}
