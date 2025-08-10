package br.com.forum.forumhub.config;

import br.com.forum.forumhub.security.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private TokenFilter tokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                // Login e criação de usuário liberados
                .requestMatchers("/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/users").permitAll()

                // Acesso ao Swagger UI
                .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()

                // Tópicos
                .requestMatchers(HttpMethod.POST, "/topicos").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/topicos/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/topicos").permitAll()
                .requestMatchers(HttpMethod.PUT, "/topicos/**").hasRole("USER")
                .requestMatchers(HttpMethod.DELETE, "/topicos/**").hasRole("ADMIN")

                // Respostas
                .requestMatchers(HttpMethod.GET, "/answers/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/answers").hasRole("USER")
                .requestMatchers(HttpMethod.DELETE, "/answers/**").hasRole("ADMIN")

                // Qualquer outra rota exige autenticação
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}