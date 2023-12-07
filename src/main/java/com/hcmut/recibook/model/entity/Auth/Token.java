package com.hcmut.recibook.model.entity.Auth;

import com.hcmut.recibook.model.TokenType;
import com.hcmut.recibook.model.entity.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "token")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "str_token")
    private String strToken;

    @Enumerated(EnumType.STRING)
    private TokenType type;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expires;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
