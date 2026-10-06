package org.nicmeg.mstep.integration.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "skill", schema = "candidate")
public class CandidateSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String createdAt;

    private Integer skillId;

    private String skillName;

    private Integer referenceNumber;

    private Integer exchangeId;

    @Column(name = "exchange_code")
    private String exchangeCode;

    @ManyToOne
    @JoinColumn(name = "candidate_details_id", referencedColumnName = "id", nullable = false)
    private CandidateDetails candidateDetails;
}