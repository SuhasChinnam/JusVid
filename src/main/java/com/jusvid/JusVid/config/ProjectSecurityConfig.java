package com.jusvid.JusVid.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.ignoringRequestMatchers("/saveMsg","/upload", "/public/**", "/api/**", "/data-api/**", "/jusvid/actuator/**"))
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/login", "/logout").permitAll()
                        .requestMatchers("/video-upload").permitAll()
                        .requestMatchers( "/uploadVideo").permitAll()
                        .requestMatchers("/watch/**").permitAll()
                        .requestMatchers( "/contact").permitAll()
                        .requestMatchers( "/about").permitAll()
                        .requestMatchers( "/public/**").permitAll()
                        .requestMatchers( "/assets/**").permitAll()
                        .requestMatchers( "/", "/home").permitAll()
                        .requestMatchers( "/updateProfile").authenticated()
                        .requestMatchers("/displayProfile").authenticated()
                        .requestMatchers("/dashboard").authenticated()
                        .requestMatchers("/closeMsg/**", "/admin/**", "/jusvid/actuator/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasRole("USER")
                        .requestMatchers("/api/**", "/data-api/**").authenticated()
                        .requestMatchers("/my-videos", "/delete-my-video/**").hasRole("USER")
                        .requestMatchers("/admin-videos", "/delete-video/**").hasRole("ADMIN")
                        .requestMatchers("/displayMessages/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
                        .defaultSuccessUrl("/dashboard")
                        .failureUrl("/login?error=true")
                        .permitAll())
                .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .permitAll())
                .httpBasic(Customizer.withDefaults());



        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
