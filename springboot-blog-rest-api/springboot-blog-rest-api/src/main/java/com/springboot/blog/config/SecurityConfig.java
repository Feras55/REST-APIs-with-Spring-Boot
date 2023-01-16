package com.springboot.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //Now this class becomes a Java based configuration
public class SecurityConfig {

    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                    authorize.anyRequest().authenticated()
                ).httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
    
}
