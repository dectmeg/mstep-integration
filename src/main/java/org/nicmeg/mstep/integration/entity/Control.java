package org.nicmeg.mstep.integration.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "control", schema = "integration")
@Getter
@Setter
@NoArgsConstructor
public class Control {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "scheduled_time", nullable = false)
    private String scheduledTime;

    @Column(name = "control_status")
    private Boolean controlStatus = false;

    @Column(name = "org_name", unique = true, nullable = false)
    private String orgName;
}