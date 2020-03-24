package com.yifan.config;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticationProcessingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import com.yifan.entrypoint.SimpleAccessDeniedHandler;

/**
 * The type Security config.
 *
 * @author wuyifan
 * @date 2020年03月24日 15:35
 */
@KeycloakConfiguration
@Configuration
public class KeycloakSecurityConfigurer extends KeycloakWebSecurityConfigurerAdapter {

    private static final String[] IGNORED_RESOURCE_LIST = new String[]{
            "/resources/**",
            "/static/**",
            "/webjars/**",
            "/plugins/**",
            "/imgs/**",
            "/fonts/**",
            "/vendors/**",
            "/flags/**",
            "/js/**",
            "/sso/login",
            "/css/**"};

    private static final String[] PERMIT_ALL_RESOURCE_LIST = new String[]{
            "/actuator/health/**",
            "/login/**",
            "/json/**",
            "/auth/**",
            "/error/**"};

    @Bean
    public GrantedAuthoritiesMapper grantedAuthoritiesMapper() {
        SimpleAuthorityMapper mapper = new SimpleAuthorityMapper();
        mapper.setConvertToUpperCase(true);
        return mapper;
    }

    @Override
    protected KeycloakAuthenticationProvider keycloakAuthenticationProvider() {
        final KeycloakAuthenticationProvider provider = super.keycloakAuthenticationProvider();
        provider.setGrantedAuthoritiesMapper(grantedAuthoritiesMapper());
        return provider;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(keycloakAuthenticationProvider());
    }

    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new NullAuthenticatedSessionStrategy();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new SimpleAccessDeniedHandler())
                .and();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(IGNORED_RESOURCE_LIST);
    }

    @Bean
    protected KeycloakAuthenticationProcessingFilter keycloakAuthenticationProcessingFilter() throws Exception {
        KeycloakAuthenticationProcessingFilter filter = new KeycloakAuthenticationProcessingFilter(authenticationManagerBean());
        filter.setSessionAuthenticationStrategy(sessionAuthenticationStrategy());
        filter.setAuthenticationFailureHandler(new CustomerAuthenticationFailureHandler());
        return filter;
    }
}
