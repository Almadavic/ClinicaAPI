package com.almada.clinicaapi.config.securityConfig;

import com.almada.clinicaapi.config.exceptionConfig.handler.ForbiddenHandler;
import com.almada.clinicaapi.config.exceptionConfig.handler.UnauthorizedHandler;
import com.almada.clinicaapi.filter.AuthenticationJWTFilter;
import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.serviceAction.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
@Primary
public class SecurityConfigurationsImpl implements SecurityConfigurations {

    private final TokenService tokenService;

    private final UserRepository userRepository;

    @Override
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Override
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        String administrator = "ADMINISTRATOR";
        String secretary = "SECRETARY";
        String patient = "PATIENT";
        String dentist = "DENTIST";

        http.authorizeHttpRequests()
                .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()

                .requestMatchers("/auth").permitAll()

                .requestMatchers("/logs/**").hasRole(administrator)

                .requestMatchers("/users/forgetpassword/*").permitAll()
                .requestMatchers("/users/changepassword").permitAll()
                .requestMatchers("/users/enableaccount").permitAll()
                .requestMatchers("/users/disable/*").hasAnyRole(administrator, secretary)
                .requestMatchers(HttpMethod.GET,  "/users/**").hasRole(administrator)
                .requestMatchers(HttpMethod.DELETE,  "/users/**").hasRole(administrator)

                .requestMatchers(HttpMethod.GET, "/secretaries/**").hasRole(administrator)
                .requestMatchers(HttpMethod.POST, "/secretaries").hasRole(administrator)
                .requestMatchers(HttpMethod.PATCH, "/secretaries/*").hasAnyRole(administrator)

                .requestMatchers(HttpMethod.GET, "/patients/**").hasAnyRole(administrator, secretary)
                .requestMatchers(HttpMethod.POST, "/patients").hasAnyRole(administrator, secretary)
                .requestMatchers(HttpMethod.PATCH, "/patients/*").hasAnyRole(administrator, secretary)

                .requestMatchers(HttpMethod.GET, "/dentists/**").hasAnyRole(administrator, secretary)
                .requestMatchers(HttpMethod.POST, "/dentists").hasAnyRole(administrator, secretary)
                .requestMatchers(HttpMethod.PATCH, "/dentists/*").hasAnyRole(administrator, secretary)

                .requestMatchers(HttpMethod.GET, "/appointments/**").hasAnyRole(administrator, secretary)
                .requestMatchers(HttpMethod.POST, "/appointments").hasAnyRole(administrator, secretary)
                .requestMatchers(HttpMethod.PATCH, "/appointments/*").hasAnyRole(administrator, secretary)


                .requestMatchers("/userarea/admin").hasRole(administrator)

                .requestMatchers("/userarea/secretary").hasRole(secretary)

                .requestMatchers("/userarea/patient").hasRole(patient)

                .requestMatchers("/userarea/dentist").hasRole(dentist)

                .requestMatchers("/userarea/patient/appointments/*").hasRole(patient)

                .requestMatchers("/userarea/dentist/appointments/*").hasRole(dentist)

                .anyRequest().authenticated()

                .and().cors()
                .and().headers().frameOptions().disable()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AuthenticationJWTFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(new UnauthorizedHandler())
                .and().exceptionHandling().accessDeniedHandler(new ForbiddenHandler());


        return http.build();
    }

    @Override
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}


