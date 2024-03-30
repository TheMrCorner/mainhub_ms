package com.mrcorner.mainhub.configuration;

import com.mrcorner.mainhub.configuration.authentication.JwtAuthFilter;
import com.mrcorner.mainhub.user.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration  {

    private final JwtAuthFilter jwtAuthFilter;

    @Value("${app.security.enabled}")
    private Boolean enableSecurity;

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    } // userDetailsService

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        if(Boolean.TRUE.equals(enableSecurity)){
            return httpSecurity
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(authRequest ->
                            authRequest.requestMatchers("/api/v1/auth/**")
                                    .permitAll()
                            .anyRequest().authenticated())
                    .sessionManagement(sessionManager ->
                            sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authenticationProvider(authenticationProvider())
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();
        } // if
        else{
            return httpSecurity
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(authRequest ->
                            authRequest.requestMatchers("/api/v1/auth/**", "/swagger-ui/**", "/v3/api-docs/**")
                                    .permitAll()
                                    .anyRequest().authenticated())
                    .sessionManagement(sessionManager ->
                            sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authenticationProvider(authenticationProvider())
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
