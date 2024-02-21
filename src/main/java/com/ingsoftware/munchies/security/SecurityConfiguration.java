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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
@CrossOrigin(origins = "http://localhost:4200")
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
                            "/slack/events",
                            "/slack/events/**",
                            "/swagger-ui.html",
                            "/webjars/**",
                            "/swagger-resources/**",
                            "/api/v1/auth/**",
                            "/rest/main/**"
                    )
                    .permitAll();
            auth.anyRequest().authenticated();
        });
        security.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource()));
        security.csrf(AbstractHttpConfigurer::disable);
        security.httpBasic(Customizer.withDefaults());
        security.formLogin(Customizer.withDefaults());
        security.formLogin(form -> form
                .loginPage("/rest/main/login") //for thymeleaf change to /login
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

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(List.of("Content-Type", "Accept", "remember-me", "Authorization", "Access-Control-Allow-Origin" +
                                "Accept", "Origin", "Content-Type", "Depth", "User-Agent", "If-Modified-Since" +
                                "Cache-Control", "Authorization", "X-Req", "X-File-Size", "X-Requested-With", "X-File-Name"));
        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}