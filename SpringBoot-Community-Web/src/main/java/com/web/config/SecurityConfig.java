package com.web.config;

import com.web.domain.enums.SocialType;
import com.web.oauth.UserTokenService;
import com.web.oauth.ClientResources;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CompositeFilter;
import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

import static com.web.domain.enums.SocialType.*;

@Configuration
// @EnableWebSecurity : To use security features from the web
@EnableWebSecurity
@EnableOAuth2Client
// extends WebSecurityConfigurerAdapter, Override configure(HttpSecurity) to customize settings
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        // authorizeHttpRequests : Request mechanism based on HttpServletRequest
        // antMatchers : List type requests
        // permitAll : Permission to anyone
        // authenticated : To only authenticated users
        http.authorizeHttpRequests().antMatchers("/", "/login/**", "/css/**", "/images/**", "/js/**", "/console/**").permitAll().anyRequest().authenticated()
                // headers : Matched header
                .and().headers().frameOptions().disable()
                .and().exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                .and().formLogin().successForwardUrl("/board/list")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/").deleteCookies("JSESSIONID").invalidateHttpSession(true)
                .and().addFilterBefore(filter, CsrfFilter.class).csrf().disable();
    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);

        return registration;
    }

    private Filter oauth2Filter() {
        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = new ArrayList<>();
        filters.add(oauth2Filter(facebook(), "/login/facebook", FACEBOOK));
        filters.add(oauth2Filter(google(), "/login/google", GOOGLE));
        filters.add(oauth2Filter(kakao(), "/login/kakao", KAKAO));
        filter.setFilters(filters);
        return filter;
    }

    private Filter oauth2Filter(ClientResources client, String path, SocialType socialType) {
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
        // Create OAuth2RestTemplate to communicate with authentication server (Oauth2ClientContext needed)
        OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oAuth2ClientContext);

        filter.setRestTemplate(template);
        // Optimize User permission
        filter.setTokenServices(new UserTokenService(client, socialType));
        // Set automatically redirected URL when authentication succeeds
        filter.setAuthenticationSuccessHandler((request, response, authentication) -> response.sendRedirect("/" + socialType.getValue() + "/complete"));
        // Set authentication failed URL
        filter.setAuthenticationFailureHandler((request, response, exception) -> response.sendRedirect("/error"));
        return filter;
    }


    @Bean
    @ConfigurationProperties("facebook")
    public ClientResources facebook() {
        return new ClientResources();
    }

    @Bean
    @ConfigurationProperties("google")
    public ClientResources google() {
        return new ClientResources();
    }

    @Bean
    @ConfigurationProperties("kakao")
    public ClientResources kakao() {
        return new ClientResources();
    }
}
