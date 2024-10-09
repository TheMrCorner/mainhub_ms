package com.mrcorner.mainhub.token.service;

import com.mrcorner.mainhub.token.entities.RefreshToken;

public interface IRefreshTokenService {

    RefreshToken createRefreshToken(String username);

    RefreshToken findByToken(String token);

    RefreshToken verifyExpiration(RefreshToken token);

}
