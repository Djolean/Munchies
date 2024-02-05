package com.ingsoftware.munchies.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        security.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/",
                            "/homePage",
                            "/restaurants",
                            "/restaurant/**",
                            "/error/**",
                            "/swagger-ui/**",
                            "/api-docs/**",
                            "/forgot-password/**",
                            "/reset-password/**",
                            "/reset-password-form/**",
                            "/initiatePasswordReset/**",
                            "/verifyPasswordReset/**",
                            "/confirmation/**",
                            "/swagger-ui.html",
                            "/webjars/**",
                            "/swagger-resources/**",
                            "/api/v1/auth/**"
                    )
                    .permitAll();
            auth.anyRequest().authenticated();
        });
        security.csrf(AbstractHttpConfigurer::disable);
        security.httpBasic(Customizer.withDefaults());
        security.formLogin(Customizer.withDefaults());
        security.formLogin(form -> form
                .loginPage("/login")
                .permitAll()
                .loginProcessingUrl("/process-login")
                .defaultSuccessUrl("/restaurants")
        );
        security.logout(logout -> logout
                .logoutSuccessUrl("/homePage")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .permitAll());
        return security.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}