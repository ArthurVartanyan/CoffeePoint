package com.coffee.point.configuration;

import com.coffee.point.security.JWTUserDetails;
import com.coffee.point.security.jwt.JWTTokenProvider;
import com.coffee.point.security.jwt.configuration.JWTConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @EnableGlobalMethodSecurity - roles allowed enable
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JWTUserDetails jwtUserDetails;

    public SecurityConfiguration(JWTUserDetails jwtUserDetails) {
        this.jwtUserDetails = jwtUserDetails;
    }


    /**
     * Using BCrypt
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    /**
     * Using @Bean, because we have circles
     */
    @Bean
    public JWTTokenProvider jwtTokenProvider(JWTUserDetails userDetails) {
        return new JWTTokenProvider(userDetails);
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    /**
     * Method of configurations.
     * Instead of annotations over methods and classes for access by role,
     * it was decided to use antMatchers with restrictions on links (by role).
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable().csrf().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/META-INF/**").permitAll()
                .antMatchers("/api/registration", "/api/authentication")
                .permitAll()
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**")
                .permitAll()
                .anyRequest()
                .authenticated().and()
                .logout()
                .permitAll()
                .and()
                .apply(new JWTConfigurer(jwtTokenProvider(jwtUserDetails)));
    }
}