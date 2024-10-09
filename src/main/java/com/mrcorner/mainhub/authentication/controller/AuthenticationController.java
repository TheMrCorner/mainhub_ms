package com.mrcorner.mainhub.authentication.controller;

import com.mrcorner.mainhub.authentication.dtos.AuthRequestDto;
import com.mrcorner.mainhub.authentication.dtos.JwtResponseDto;
import com.mrcorner.mainhub.authentication.dtos.RefreshTokenRequestDto;
import com.mrcorner.mainhub.authentication.dtos.UserRequest;
import com.mrcorner.mainhub.authentication.dtos.UserResponse;
import com.mrcorner.mainhub.exceptions.AuthenticationErrorException;
import com.mrcorner.mainhub.exceptions.DataNotFoundException;
import com.mrcorner.mainhub.exceptions.InvalidDataException;
import com.mrcorner.mainhub.token.entities.RefreshToken;
import com.mrcorner.mainhub.token.service.IRefreshTokenService;
import com.mrcorner.mainhub.token.service.ITokenService;
import com.mrcorner.mainhub.user.entities.UserInfo;
import com.mrcorner.mainhub.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private IRefreshTokenService refreshTokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth/login")
    public ResponseEntity<JwtResponseDto> authenticateAndGetToken(@RequestBody AuthRequestDto authRequestDTO)
            throws AuthenticationErrorException, DataNotFoundException, InvalidDataException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword())
            );
            if (authentication.isAuthenticated()) {
                log.info("RETURNING TOKEN");
                RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
                return ResponseEntity.ok(JwtResponseDto.builder()
                        .accessToken(tokenService.generateToken(authRequestDTO.getUsername()))
                        .token(refreshToken.getToken())
                        .build());
            } else {
                throw new UsernameNotFoundException("invalid user request..!!");
            }
        }
        catch(AuthenticationException ex){
            throw new AuthenticationErrorException(ex.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/save")
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserRequest userRequest)
            throws InvalidDataException, DataNotFoundException {
        UserResponse userResponse = userService.saveUser(userRequest);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/auth/refreshToken")
    public JwtResponseDto refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDTO)
            throws DataNotFoundException, AuthenticationErrorException{
        RefreshToken refreshToken = refreshTokenService.findByToken(refreshTokenRequestDTO.getToken());
        refreshToken = refreshTokenService.verifyExpiration(refreshToken);
        UserInfo userInfo = refreshToken.getUserInfo();
        String accessToken = tokenService.generateToken(userInfo.getUsername());
        return JwtResponseDto.builder()
                .accessToken(accessToken)
                .token(refreshTokenRequestDTO.getToken()).build();
    }
}
