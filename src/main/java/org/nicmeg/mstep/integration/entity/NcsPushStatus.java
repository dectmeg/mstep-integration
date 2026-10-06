package org.nicmeg.mstep.integration.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "ncs_push_status", schema = "integration")
@Data
@NoArgsConstructor
public class NcsPushStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_id", nullable = false)
    private Apis api;

    @Column(name = "api_status", nullable = false)
    private String apiStatus;

    @Column(name = "response_code")
    private Integer responseCode;

    @Column(name = "response_message")
    private String responseMessage;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
