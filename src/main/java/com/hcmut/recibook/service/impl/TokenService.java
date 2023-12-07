package com.hcmut.recibook.service.impl;

import com.hcmut.recibook.model.TokenType;
import com.hcmut.recibook.model.entity.Auth.Token;
import com.hcmut.recibook.model.entity.User.User;
import com.hcmut.recibook.repository.Auth.TokenRepository;
import com.hcmut.recibook.service.ITokenService;
import com.hcmut.recibook.util.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService implements ITokenService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public Token addToken(String token, TokenType type, User user) {
        Token newToken = Token.builder()
                .strToken(token)
                .type(type)
                .expires(jwtService.extractExpiration(token))
                .user(user)
                .build();

        return tokenRepository.save(newToken);
    }
}
