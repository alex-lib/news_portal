package com.example.springbootnewsportal.configuration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "app.security", name = "type", havingValue = "db")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    @ConditionalOnProperty(prefix = "app.security", name = "type", havingValue = "db")
    public AuthenticationManager databaseAuthenticationManager(HttpSecurity http, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) throws Exception {
        var authManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authManagerBuilder.userDetailsService(userDetailsService);

        var authProvider = new DaoAuthenticationProvider(passwordEncoder);
        authProvider.setUserDetailsService(userDetailsService);

        authManagerBuilder.authenticationProvider(authProvider);
        return authManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        http.authorizeHttpRequests((auth) -> auth.requestMatchers("/api/v/user/find-all-by-filter").hasRole("ADMIN")
                        .requestMatchers("/api/v/user/create").permitAll()
                        .requestMatchers("/api/v/user/{id}").hasAnyRole("ADMIN", "USER", "MODERATOR")
                        .requestMatchers("/api/v/news-category/find-all-by-filter").hasAnyRole("ADMIN", "USER", "MODERATOR")
                        .requestMatchers("/api/v/news-category/create").hasAnyRole("ADMIN", "MODERATOR")
                        .requestMatchers("/api/v/news-category/{id}").hasAnyRole("ADMIN", "USER", "MODERATOR")
                        .requestMatchers("/api/v/news-category/update/{id}").hasAnyRole("ADMIN", "MODERATOR")
                        .requestMatchers("/api/v/news-category/delete/{id}").hasAnyRole("ADMIN", "MODERATOR")
                        .requestMatchers("/api/v/news/find-all-by-filter").hasAnyRole("ADMIN", "USER", "MODERATOR")
                        .requestMatchers("/api/v/news/{id}").hasAnyRole("ADMIN", "USER", "MODERATOR")
                        .requestMatchers("/api/v/news/create").hasAnyRole("ADMIN", "USER", "MODERATOR")
                        .requestMatchers("/api/v/news/delete/{id}").hasAnyRole("ADMIN", "USER", "MODERATOR")
                        .requestMatchers("/api/v/news/update/{id}").hasRole("USER")
                        .requestMatchers("/api/v/comment/{id}").hasAnyRole("ADMIN", "USER", "MODERATOR")
                        .requestMatchers("/api/v/comment/create").hasAnyRole("ADMIN", "USER", "MODERATOR")
                        .requestMatchers("/api/v/comment/update/{id}").hasAnyRole("USER")
                        .requestMatchers("/api/v/comment/delete/{id}").hasAnyRole("ADMIN", "USER", "MODERATOR")
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationManager(authenticationManager);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.debug(true);
    }
}