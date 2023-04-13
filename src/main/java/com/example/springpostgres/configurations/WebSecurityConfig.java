package com.example.springpostgres.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final UserDetailsService myUserDetailsService;

    private final CustomRequestFilter customRequestFilter;

    WebSecurityConfig(UserDetailsService myUserDetailsService, CustomRequestFilter customRequestFilter) {
        this.myUserDetailsService = myUserDetailsService;
        this.customRequestFilter = customRequestFilter;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(customRequestFilter, BasicAuthenticationFilter.class);
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/api/account/sign-in", "/api/account/sign-up").permitAll()
                                .requestMatchers(HttpMethod.PUT, "api/account").authenticated()
                                .requestMatchers(HttpMethod.POST, "/api/complaint").hasAuthority("ROLE_USER")
                                .anyRequest().hasAuthority("ROLE_OFFICER")
                );
        return http.build();
    }
}
