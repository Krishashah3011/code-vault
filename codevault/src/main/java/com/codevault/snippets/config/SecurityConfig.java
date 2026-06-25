package com.codevault.snippets.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.codevault.snippets.service.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/register", "/login", "/register/**", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated()
            )
           .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/snippets", true)
                .failureUrl("/login?error=true")   // 👈 ADD THIS
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login")
                .permitAll()
            )
            .userDetailsService(userDetailsService);

        return http.build();
    }
    @Bean
    public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
        return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    }

    @Autowired
    private CustomUserDetailsService userDetailsService;
}