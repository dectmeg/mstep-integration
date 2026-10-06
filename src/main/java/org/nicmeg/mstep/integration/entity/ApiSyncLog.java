package org.nicmeg.mstep.integration.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "api_sync_log")
@Getter
@Setter
public class ApiSyncLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "sync_status")
    private String syncStatus;

    @Column(name = "retry_count")
    private Integer retryCount;

    @Column(name = "response_message")
    private String responseMessage;

    @Column(name = "synced_at")
    private LocalDateTime syncedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
