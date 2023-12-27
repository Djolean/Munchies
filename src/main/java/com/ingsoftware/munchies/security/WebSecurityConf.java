//package com.ingsoftware.munchies.security;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import org.springframework.security.web.SecurityFilterChain;
//
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConf {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/restaurants/**").permitAll()
//                .antMatchers("/admin").hasAuthority("Admin")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .defaultSuccessUrl("/homePage")
//                .and()
//                .logout(logout ->
//                        logout
//                                .logoutSuccessUrl("/login?logout")
//                );
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder getPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}
