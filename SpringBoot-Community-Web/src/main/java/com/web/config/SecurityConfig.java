package com.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import static com.web.domain.enums.SocialType.*;

@Configuration
// @EnableWebSecurity : To use security features from the web
@EnableWebSecurity
// extends WebSecurityConfigurerAdapter, Override configure(HttpSecurity) to customize settings
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        // authorizeHttpRequests : Request mechanism based on HttpServletRequest
        // antMatchers : List type requests
        // permitAll : Permission to anyone
        // authenticated : To only authenticated users
        http.authorizeHttpRequests().antMatchers("/", "/login/**", "/css/**", "/images/**", "/js/**", "/console/**").permitAll()
                // Specify URLs each Social Media login format
                .antMatchers("/facebook").hasAuthority(FACEBOOK.getRoleType())
                .antMatchers("/google").hasAuthority(GOOGLE.getRoleType())
                .antMatchers("/kakao").hasAuthority(KAKAO.getRoleType())
                .anyRequest().authenticated()
                // oauth2Login : Use OAuth2 login way
                .and().oauth2Login().defaultSuccessUrl("/loginSuccess").failureUrl("/loginFailure")
                // headers : Matched header
                .and().headers().frameOptions().disable()
                .and().exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                .and().formLogin().successForwardUrl("/board/list")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/").deleteCookies("JSESSIONID").invalidateHttpSession(true)
                .and().addFilterBefore(filter, CsrfFilter.class).csrf().disable();
    }
}
