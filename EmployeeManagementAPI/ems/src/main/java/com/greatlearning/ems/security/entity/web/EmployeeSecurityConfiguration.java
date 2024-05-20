package com.greatlearning.ems.security.entity.web;

import com.greatlearning.ems.security.entity.service.EmployeeUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;


@Configuration
public class EmployeeSecurityConfiguration {

    @Bean
    public UserDetailsService userDetailsService() {
        return new EmployeeUserDetailsService();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider ssrsDaoAuthenticationProvider() {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .requestMatchers(UnsecuredRequests()).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/employees/create", HttpMethod.POST.name())).hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .cors().and().csrf().disable()
                .authenticationProvider(ssrsDaoAuthenticationProvider());

        return http.build();
    }

    private RequestMatcher UnsecuredRequests() {
        return request -> {
            String path = request.getServletPath();
            return path.startsWith("/roles") || path.startsWith("/users") || path.startsWith("/employees");
        };
    }
}