package org.nicmeg.mstep.integration.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "apis", schema = "integration")
@Data
@NoArgsConstructor
public class Apis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "base_url", nullable = false, length = 500)
    private String baseUrl;

    @Column(name = "api_url", nullable = false, length = 500)
    private String apiUrl;

    @Column(name = "http_method", nullable = false, length = 10)
    private String httpMethod = "POST";

    @Column(name = "api_status")
    private Boolean apiStatus = true;

    @ManyToOne
    @JoinColumn(name = "org_name", referencedColumnName = "org_name")
    private Control control;

    @Column(name = "api_sequence", nullable = false)
    private Integer apiSequence;

    @Column(name = "environment", nullable = false, length = 20)
    private String environment;
}
