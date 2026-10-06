package org.nicmeg.mstep.integration.entity;


import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "qualification", schema = "candidate")
public class CandidateQualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String qualificationGroupName;

    private String qualificationSubGroupName;

    private String qualificationName;

    private String institute;

    private String rollNumber;

    private String languageId;

    private String percentage;

    private String yearPass;

    private String examPass;

    private String referenceNumber;


    @Column(name = "board_university")
    private String boardUniversity;

    // private String createdAt;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private String documentPath;

    private String preference;

    // new
    private String appearedExam;
    private String examDuration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qualification_status", referencedColumnName = "flagName")
    private StatusFlag qualificationStatus;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "qualification_status_id", referencedColumnName = "id",
    // nullable = false)
    // private StatusFlag qualificationStatus;

    @ManyToOne
    @JoinColumn(name = "candidate_details_id", referencedColumnName = "id", nullable = false)
    private CandidateDetails candidateDetails;

    @Column(name = "application_number")
    private String applicationNumber;

}