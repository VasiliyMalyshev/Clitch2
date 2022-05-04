package com.malyshev.clitch.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService; // сервис, с помощью которого тащим пользователя
    private final SuccessUserHandler successUserHandler; // класс, в котором описана логика перенаправления пользователей по ролям
    private final JwtFilter jwtFilter;

    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, SuccessUserHandler successUserHandler, JwtFilter jwtFilter) {
        this.userDetailsService = userDetailsService;
        this.successUserHandler = successUserHandler;
        this.jwtFilter = jwtFilter;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); // конфигурация для прохождения аутентификации
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/login", "/logout", "/").permitAll() // доступность всем
                    .antMatchers("/user/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')") // разрешаем входить на /user пользователям с ролью User, Admin
                    .antMatchers("/**").access("hasAnyRole('ROLE_ADMIN')") // разрешает входить на /admin пользователю с ролью Admin
                .and()
                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                    .formLogin()  // Spring сам подставит свою логин форму
                .successHandler(successUserHandler)// подключаем наш SuccessHandler для перенеправления по ролям
                .permitAll()
                .and().logout()
                .logoutUrl("/logout") //URL-адрес, запускающий выход из системы (по умолчанию "/ logout").
                .logoutSuccessUrl("/login") //URL-адрес для перенаправления после выхода из системы.
                .and().csrf().disable();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}

