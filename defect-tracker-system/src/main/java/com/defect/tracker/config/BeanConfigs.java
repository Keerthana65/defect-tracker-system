package com.defect.tracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableMethodSecurity
public class BeanConfigs {
    @Bean
    public static PasswordEncoder PasswordEncoder(){
        return new BCryptPasswordEncoder();

    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests((authorize)-> authorize.anyRequest().authenticated()).httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService()
    {
        UserDetails userChandricca= User.builder()
                .username("chandricca")
                .password(PasswordEncoder().encode("chandricca"))
                .roles("User")
                .build();
        UserDetails userElavarasy= User.builder()
                .username("elavarasy")
                .password(PasswordEncoder().encode("elavarasy"))
                .roles("User")
                .build();
        UserDetails userAingarathas= User.builder()
                .username("aingarathas")
                .password(PasswordEncoder().encode("aingarathas"))
                .roles("User")
                .build();
        UserDetails adminVijinthika= User.builder()
                .username("vijinthika")
                .password(PasswordEncoder().encode("vijinthika"))
                .roles("Admin")
                .build();
        return new InMemoryUserDetailsManager(userChandricca,userAingarathas,userElavarasy,adminVijinthika);
    }


}
