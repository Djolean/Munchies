package com.ingsoftware.munchies.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.config.Customizer;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/",
                    "/homePage",
                    "/admin/restaurants",
                    "/admin/restaurant-details").permitAll();
            auth.anyRequest().authenticated();
        });
        security.csrf(AbstractHttpConfigurer::disable);
        security.httpBasic(Customizer.withDefaults());
        security.formLogin(Customizer.withDefaults());
        security.formLogin(form -> form
                .loginPage("/login")
                .permitAll()
                .loginProcessingUrl("/process-login")
                .defaultSuccessUrl("/admin/restaurants")
        );
        security.logout(logout -> logout
                .logoutSuccessUrl("/")
                .permitAll());
        return security.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}