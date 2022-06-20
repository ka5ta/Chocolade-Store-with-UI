package com.gridu.store.security;

import com.gridu.store.constraint.Role;


import com.gridu.store.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import javax.servlet.http.HttpServletResponse;

import static com.gridu.store.constraint.Role.USER;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http.csrf().disable();

        http
                .formLogin(form -> {
                            try {
                                form
                                        .loginPage("/shopping/signin")
                                        .defaultSuccessUrl("/shopping")
                                        .failureHandler(new AccountAuthenticationFailureHandler())
                                        .usernameParameter("email")
                                        .loginProcessingUrl("/shopping/signin")
                                        .permitAll()
                                        .and()
                                        .logout()
                                        .logoutUrl("/logout")
                                        .logoutSuccessUrl("/shopping/logout")
                                        .deleteCookies("JSESSIONID");
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }

                );

        // Set permissions on endpoints
        http.authorizeHttpRequests(authorize -> authorize
                .mvcMatchers("/css/**").permitAll()
                .mvcMatchers("/shopping/logout").permitAll()
                .mvcMatchers("/shopping/register/**").permitAll()
                .mvcMatchers("/shopping/signin/**").permitAll()
                .mvcMatchers("/shopping/user").hasAuthority("USER")
                .mvcMatchers("/shopping/admin").hasAuthority("ADMIN")
                .mvcMatchers("/shopping").hasAuthority("USER")

        );

        http.headers().frameOptions().sameOrigin();
        return http.build();
    }



}