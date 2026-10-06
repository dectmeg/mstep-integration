package org.nicmeg.mstep.integration.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

@Entity
@Table(name = "api_parameters", schema = "integration")
@Data
@NoArgsConstructor
public class ApiParameter {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "api_id", nullable = false)
    private Apis api;

    @Column(name = "param_name", nullable = false)
    private String paramName;

    @Column(name = "param_type", nullable = false)
    private String paramType;

    @Column(name = "required")
    private Boolean required = false;

    @Column(name = "source_schema")
    private String sourceSchema;

    @Column(name = "source_table")
    private String sourceTable;

    @Column(name = "source_column")
    private String sourceColumn;

    @Column(name = "source_key_column")
    private String sourceKeyColumn;

    @Column(name = "fixed_value")
    private String fixedValue;

}
