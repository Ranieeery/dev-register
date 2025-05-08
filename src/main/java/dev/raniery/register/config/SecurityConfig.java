package dev.raniery.register.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(req -> req
                .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .requestMatchers(HttpMethod.GET, "api/api-docs/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/swagger/**").permitAll()

                .requestMatchers(HttpMethod.GET, "/developer/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/developer/**").permitAll()
                .requestMatchers(HttpMethod.PATCH, "/developer/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/developer/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/tasks/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/tasks/**").permitAll()
                .requestMatchers(HttpMethod.PATCH, "/tasks/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/tasks/**").permitAll()
                .anyRequest().authenticated()
            )
            .build();
    }
}
