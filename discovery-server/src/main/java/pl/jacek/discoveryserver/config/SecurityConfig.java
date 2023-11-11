package pl.jacek.discoveryserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${eureka.client.username}")
    private String username;
    @Value("${eureka.client.password}")
    private String password;


    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails userDetails = User
                .withUsername(username)
                .passwordEncoder(NoOpPasswordEncoder.getInstance()::encode)
                .password("{noop}"+password)
                .authorities("USER")
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                        .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                        .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
