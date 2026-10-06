// package org.nicmeg.mstep.integration.entity;


// import jakarta.persistence.*;
// import lombok.*;

// import java.util.Collection;

// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// @Entity
// // @Table(name = "registration", schema = "candidate")
// @Table(name = "login", schema = "candidate")
// public class CandidateRegistration implements UserDetails {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     // Login Credentials
//     private String email;
//     private String password;
//     private String username;

//     @Column(name = "password_old")
//     private String passwordOld;

//     // Audit Field
//     private String createdAt;

//     private String updatedAt;

//     // One-to-One Relationship
//     @OneToOne(mappedBy = "candidateRegistration", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//     private CandidateDetails candidateDetails;

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         return null;
//     }

//     @Override
//     public String getPassword() {
//         return password;
//     }

//     @Override
//     public String getUsername() {
//         return username;
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//         return UserDetails.super.isAccountNonExpired();
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return UserDetails.super.isAccountNonLocked();
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//         return UserDetails.super.isCredentialsNonExpired();
//     }

//     @Override
//     public boolean isEnabled() {
//         return UserDetails.super.isEnabled();
//     }
// }
