package com.hcmut.recibook.model.entity.User;

import com.hcmut.recibook.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "_user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String password;

    @Temporal(TemporalType.DATE)
    private Date birthdate;

    private String phone;

    private String avatar;

    @Column(name = "bio_intro")
    private String bioIntro;

    private String roles;

    @Column(name = "is_enabled")
    private boolean isEnabled;
}
