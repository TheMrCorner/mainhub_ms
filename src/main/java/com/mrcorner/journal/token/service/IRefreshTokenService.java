package com.mrcorner.journal.token.service;

import com.mrcorner.journal.token.entities.RefreshToken;

public interface IRefreshTokenService {

    RefreshToken createRefreshToken(String username);

    RefreshToken findByToken(String token);

    RefreshToken verifyExpiration(RefreshToken token);

}
