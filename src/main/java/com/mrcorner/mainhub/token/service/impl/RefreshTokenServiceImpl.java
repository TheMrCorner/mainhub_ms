package com.mrcorner.mainhub.token.service.impl;

import com.mrcorner.mainhub.exceptions.AuthenticationErrorException;
import com.mrcorner.mainhub.exceptions.DataNotFoundException;
import com.mrcorner.mainhub.exceptions.InvalidDataException;
import com.mrcorner.mainhub.token.entities.RefreshToken;
import com.mrcorner.mainhub.token.repository.IRefreshTokenRepository;
import com.mrcorner.mainhub.token.service.IRefreshTokenService;
import com.mrcorner.mainhub.user.repositories.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements IRefreshTokenService {

    IRefreshTokenRepository refreshTokenRepository;
    IUserRepository userRepository;

    public RefreshTokenServiceImpl(IRefreshTokenRepository refreshTokenRepository,
                                   IUserRepository userRepository){
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    } // Constructor

    @Override
    public RefreshToken createRefreshToken(String username) throws InvalidDataException, DataNotFoundException {
        try {
            RefreshToken refreshToken = RefreshToken.builder()
                    .userInfo(userRepository.findByUsername(username))
                    .token(UUID.randomUUID().toString())
                    .expiryDate(Instant.now().plusMillis(600000))
                    .build();
            return refreshTokenRepository.save(refreshToken);
        } // try
        catch(EntityNotFoundException ex){
            throw new DataNotFoundException("REFRESH TOKEN SERVICE: USER NOT FOUND " + username);
        } // catch
        catch(IllegalArgumentException ex){
            throw new InvalidDataException("REFRESH TOKEN SERVICE: ERROR SAVING DATA " + username);
        } // catch
    } // createRefreshToken

    @Override
    public RefreshToken findByToken(String token) throws DataNotFoundException {
        Optional<RefreshToken> refreshTokenOpt = refreshTokenRepository.findByToken(token);
        if(refreshTokenOpt.isPresent()) {
            return refreshTokenOpt.get();
        } // if
        else{
            throw new DataNotFoundException("REFRESH TOKEN SERVICE: TOKEN NOT FOUND " + token);
        } // else
    } // findByToken

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) throws AuthenticationErrorException {
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new AuthenticationErrorException(token.getToken() + " Refresh token is expired.");
        } // if
        return token;
    } // verifyExpiration
} // RefreshTokenServiceImpl
