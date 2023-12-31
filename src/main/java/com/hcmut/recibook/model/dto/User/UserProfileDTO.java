package com.hcmut.recibook.model.dto.User;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class UserProfileDTO {
    private UUID id;

    private String email;

    private String firstName;
    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date birthdate;

    private String phone;
    private String avatar;
    private String bioIntro;
    private boolean isEnabled;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
