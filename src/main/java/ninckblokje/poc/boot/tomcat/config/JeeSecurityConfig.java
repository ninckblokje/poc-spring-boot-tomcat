package ninckblokje.poc.boot.tomcat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.mapping.MappableAttributesRetriever;
import org.springframework.security.core.authority.mapping.SimpleAttributes2GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleMappableAttributesRetriever;
import org.springframework.security.web.authentication.preauth.j2ee.J2eeBasedPreAuthenticatedWebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.j2ee.J2eePreAuthenticatedProcessingFilter;

import java.util.Set;

@Configuration
@EnableWebSecurity
@Profile("jee")
public class JeeSecurityConfig extends WebSecurityConfigurerAdapter {

    private static String ROLE_PREFIX = "ROLE_";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .jee().j2eePreAuthenticatedProcessingFilter(j2eePreAuthenticatedProcessingFilter());
    }

    @Bean
    public J2eePreAuthenticatedProcessingFilter j2eePreAuthenticatedProcessingFilter() throws Exception {
        J2eePreAuthenticatedProcessingFilter j2eePreAuthenticatedProcessingFilter = new J2eePreAuthenticatedProcessingFilter();
        j2eePreAuthenticatedProcessingFilter.setAuthenticationManager(authenticationManagerBean());

        J2eeBasedPreAuthenticatedWebAuthenticationDetailsSource j2eeBasedPreAuthenticatedWebAuthenticationDetailsSource = new J2eeBasedPreAuthenticatedWebAuthenticationDetailsSource();
        j2eeBasedPreAuthenticatedWebAuthenticationDetailsSource.setMappableRolesRetriever(simpleMappableAttributesRetriever());

        SimpleAttributes2GrantedAuthoritiesMapper simpleAttributes2GrantedAuthoritiesMapper = new SimpleAttributes2GrantedAuthoritiesMapper();
        simpleAttributes2GrantedAuthoritiesMapper.setConvertAttributeToUpperCase(true);
        j2eeBasedPreAuthenticatedWebAuthenticationDetailsSource.setUserRoles2GrantedAuthoritiesMapper(simpleAttributes2GrantedAuthoritiesMapper);

        j2eePreAuthenticatedProcessingFilter.setAuthenticationDetailsSource(j2eeBasedPreAuthenticatedWebAuthenticationDetailsSource);
        return j2eePreAuthenticatedProcessingFilter;
    }

    @Bean
    public MappableAttributesRetriever simpleMappableAttributesRetriever() {
        SimpleMappableAttributesRetriever simpleMappableAttributesRetriever = new SimpleMappableAttributesRetriever();

        simpleMappableAttributesRetriever.setMappableAttributes(Set.of(
                ROLE_PREFIX + "INTERNAL",
                ROLE_PREFIX + "MANAGEMENT",
                ROLE_PREFIX + "USER"
        ));

        return simpleMappableAttributesRetriever;
    }
}
