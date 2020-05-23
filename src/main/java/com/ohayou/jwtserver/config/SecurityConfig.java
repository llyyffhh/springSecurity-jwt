package com.ohayou.jwtserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohayou.jwtserver.exception.AccessDeniedExceptionHandler;
import com.ohayou.jwtserver.exception.MyAuthenticationEntryPoint;
import com.ohayou.jwtserver.filter.JwtAuthenticationFilter;
import com.ohayou.jwtserver.filter.JwtAuthorizationFilter;
import com.ohayou.jwtserver.handler.JwtTokenLoginSuccessHandler;
import com.ohayou.jwtserver.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author liyan
 * @date 2020/5/17 下午10:21
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    AccessDeniedExceptionHandler accessDeniedExceptionHandler;

    @Autowired
    MyAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JwtTokenLoginSuccessHandler jwtTokenLoginSuccessHandler;

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    JwtAuthorizationFilter jwtAuthorizationFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/doLogin","/index").permitAll()
                .antMatchers("/info").authenticated()
                .anyRequest().access("@rbacService.hasPermission(request,authentication)")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthorizationFilter,jwtAuthenticationFilter.getClass())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedExceptionHandler).authenticationEntryPoint(authenticationEntryPoint);




    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
