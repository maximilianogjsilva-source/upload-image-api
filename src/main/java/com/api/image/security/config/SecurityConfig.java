package com.api.image.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf( AbstractHttpConfigurer::disable )
                .httpBasic( Customizer.withDefaults() )
                .sessionManagement( session -> session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).authorizeHttpRequests( auth -> auth
                        .requestMatchers(HttpMethod.GET,"/uploads/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/css/**").permitAll()
                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated()
                );

        return httpSecurity.build();
    }

}
