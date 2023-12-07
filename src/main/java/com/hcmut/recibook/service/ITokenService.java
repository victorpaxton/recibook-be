package com.hcmut.recibook.service;

import com.hcmut.recibook.model.TokenType;
import com.hcmut.recibook.model.entity.Auth.Token;
import com.hcmut.recibook.model.entity.User.User;

public interface ITokenService {
    Token addToken(String token, TokenType type, User user);
}
