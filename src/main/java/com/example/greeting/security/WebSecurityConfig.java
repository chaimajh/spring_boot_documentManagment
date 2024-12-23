package com.example.greeting.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
        

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/", "/home", "/webjars/**", "/images/**").permitAll()
                                                .anyRequest().authenticated())
                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .permitAll()
                                                .defaultSuccessUrl("/", true))
                                .logout(logout -> logout.permitAll());

                return http.build();
        }
        

        @Bean
        public UserDetailsService userDetailsService() {
                UserDetails admin = User.withUsername("admin")
                                .password(passwordEncoder().encode("password")) // Encode "password"
                                .roles("ADMIN")
                                .build();

                UserDetails user = User.withUsername("testuser")
                                .password(passwordEncoder().encode("password")) // Encode "password"
                                .roles("USER")
                                .build();

                return new InMemoryUserDetailsManager(admin, user);
        }

        @Bean
        public AuthenticationProvider authenticationProvider() {
                DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
                authenticationProvider.setUserDetailsService(userDetailsService());
                authenticationProvider.setPasswordEncoder(passwordEncoder());
                return authenticationProvider;
        }

        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
