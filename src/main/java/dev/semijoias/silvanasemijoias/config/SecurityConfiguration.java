/*
package dev.semijoias.silvanasemijoias.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration{

    private UserDetailsServiceImpl userDetailsServiceImpl;
    private AuthEntryPointJwt authEntryPointJwt;
    private AuthTokenFilter authTokenFilter;

    public SecurityConfiguration(UserDetailsServiceImpl userDetailsServiceImpl, AuthEntryPointJwt authEntryPointJwt, AuthTokenFilter authTokenFilter){
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.authEntryPointJwt = authEntryPointJwt;
        this.authTokenFilter = authTokenFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration){
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    @Primary
    public AuthenticationManagerBuilder authenticationManagerBuilder(AuthenticationManagerBuilder authenticationManagerBuilder, UserDetailsService userDetailsService){
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authEntryPointJwt).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/auth/**").permiteAll()
                .anyRequest().authenticated();
        httpSecurity.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

}
*/

