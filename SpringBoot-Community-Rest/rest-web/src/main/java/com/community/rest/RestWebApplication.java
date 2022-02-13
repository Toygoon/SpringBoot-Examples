package com.community.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@SpringBootApplication
public class RestWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestWebApplication.class, args);
    }

    @Configuration
    // @EnableGlobalMethodSecurity : To use @PreAuthorize, and @PostAuthorize
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    // @EnableWebSecurity : Just enables web security
    @EnableWebSecurity
    static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            CorsConfiguration configuration = new CorsConfiguration();
            /* Allow all origin, method, and header */
            configuration.addAllowedOrigin("*");
            configuration.addAllowedMethod("*");
            configuration.addAllowedHeader("*");
            // Apply set value (from CorsConfiguration which is implemented with CorsConfigurationSource interface) to UrlBasedCorsConfigurationSource
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);

            http.httpBasic()
                    .and().authorizeRequests()
                    //.antMatchers(HttpMethod.POST, "/Boards/**").hasRole("ADMIN")
                    .anyRequest().permitAll()
                    // Apply settings
                    .and().cors().configurationSource(source)
                    .and().csrf().disable();
        }
    }
}