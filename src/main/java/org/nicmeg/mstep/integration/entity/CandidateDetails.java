package org.nicmeg.mstep.integration.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "details", schema = "candidate", indexes = @Index(name = "idx_voterid", columnList = "voterId"))
public class CandidateDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ruralUrban;
    private String cityTownVillage;
    private String otherTrainingFlag;
    private String phFlag;
    private String skillFlag;
    private String postOffice;
    private String block;
    private String pinCode;
    private String district;
    private String address;
    private String fatherName;
    private String motherTongue;
    private String motherName;
    private String currentSelfEmployment;
    private String caste;
    private String category;

    // @Column(name = "renewal_due")
    // private Date renewalDate;

    @Column(name = "renewal_due")
    private LocalDate renewalDate;


    

    private String experienceFlag;
    // private Integer employmentExchangeId;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "exchange_id", referencedColumnName = "id")
    // private EmploymentExchange exchangeId;

    @Column(name = "exchange_code")
    private String exchangeCode;

    private String exServiceman;
    private String drivingLicenseType;
    private String drivingLicenseValidity;
    private String drivingLicense;
    private String state;
    private String maritalStatus;
    private String gender;
    private String occupation;
    private LocalDate dateOfBirth;
    private String localNonLocal;
    private String eligibilityAptitudeFlag;
    private String religion;

    // NOTE: Phone no. not required to be unique, suppose single parents applied for
    // two or more children
    private String phoneNumber;

    private String name;

    // private String verifiedAt;

    @Column(name = "verified_at")
    private LocalDateTime verifiedAt;

    @Column(name = "current_occupation")
    private String currentOccupation;

    @Column(length = 10)
    private String voterId;

    // private String createdAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // private String updatedAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // private String expiredAt;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;

    // new
    private String appearedExam;

    // new
    private String disabilityType;
    private String disabilityPercent;

    // private String registrationStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status", referencedColumnName = "flagName")
    private StatusFlag registrationStatus;

    // @Column(name = "registration_number", unique = true)
    private String registrationNumber;

    private String applicationNumber;

    @Column(name = "email_id")
    private String email;

    // Foreign Key Reference
    // @OneToOne(fetch = FetchType.LAZY, optional = true)
    // @JoinColumn(name = "login_id", referencedColumnName = "id", nullable = true, unique = true)
    // private CandidateRegistration candidateRegistration;

    @OneToMany(mappedBy = "candidateDetails", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CandidateSkill> candidateSkills;

    // @OneToMany(mappedBy = "candidateDetails", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    // private List<CandidateSport> candidateSports;

    // @OneToMany(mappedBy = "candidateDetails", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    // private List<AdditionalQualification> additionalQualifications;

    @OneToMany(mappedBy = "candidateDetails", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CandidateQualification> candidateQualifications;

    // @OneToMany(mappedBy = "candidateDetails", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    // private List<TransferExchange> transferExchanges;

    // @OneToMany(mappedBy = "candidateDetails", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    // private List<CandidateLanguage> candidateLanguages;

    // @OneToMany(mappedBy = "candidateDetails", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    // private List<CandidateExperience> candidateExperiences;

    @Column(name = "xtencardissued")
    private boolean xTenCardIssued;

    @Column(name = "x_ten_card_path")
    private String xTenCardPath;

    @Column(name = "ews_flag")
    private String ewsFlag;

    private List<String> trainings;

    // @OneToMany(mappedBy = "candidateDetails", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    // private List<CandidateTraining> candidateTrainings;

    private List<String> sports;

    private String remark;
    private String applicationDefectsToBeRectified;
    private String documentsRequired;
    private String verifiedBy;
    private String approvedBy;
    // private String approvedAt;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    // @Column(name = "approved_at")
    // private LocalDate approvedAt;

    private String candidateProfilePicturePath;
    private String candidateAgeProofPath;
    private String candidateVoterIdPath;
    private String candidateOtherCertificatesPath;
    private String candidateCasteCertificatePath;

    @Column(name = "ph_certificate")
    private String candidatePhCertificatePath;


    // Alternate phone number
    @Column(name = "alternate_phone_number", length = 15)
    private String alternatePhoneNumber;

    // Height stored as total inches
    @Column(name = "height_inch")
    private Integer heightInch;

    // @OneToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "renew_id", referencedColumnName = "id")
    // private CandidateRenewal candidateRenewal;

    // @OneToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "transfer_id", referencedColumnName = "id")
    // private TransferExchange transferExchange;
}
