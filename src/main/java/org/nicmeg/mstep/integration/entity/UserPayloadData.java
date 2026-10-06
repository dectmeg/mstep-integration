package org.nicmeg.mstep.integration.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_payload_data", schema = "integration")
@Data
@NoArgsConstructor
public class UserPayloadData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "payload_json", columnDefinition = "jsonb")
    private String payloadJson;
}
