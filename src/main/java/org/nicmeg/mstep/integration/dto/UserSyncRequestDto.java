package org.nicmeg.mstep.integration.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSyncRequestDto {

    private String fullName;

    private String mobileNumber;

    private String role;

    private String email;

    private String dateOfBirth;

    private String gender;

    private String status;

    private Long stateId;

    private Long districtId;

}
