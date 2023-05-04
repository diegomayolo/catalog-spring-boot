package com.dm.dscatalog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter
{
    private static final String ROLE_OPERATOR = "OPERATOR";
    private static final String ROLE_ADMIN    = "ADMIN";


    private static final String[] PUBLIC = { "/oauth/token" };

    private static final String[] OPERATOR_OR_ADMIN = { "/products/**", "/categories/**" };

    private static final String[] ADMIN = { "/users/**" };

    @Autowired
    private JwtTokenStore tokenStore;

    /**
     * configure
     *
     * @param resources ResourceServerSecurityConfigurer
     * @throws Exception
     */
    @Override
    public void configure( ResourceServerSecurityConfigurer resources ) throws Exception
    {
        resources.tokenStore( tokenStore );
    }

    /**
     * configure
     *
     * @param http HttpSecurity
     * @throws Exception
     */
    @Override
    public void configure( HttpSecurity http ) throws Exception
    {
        http.authorizeRequests().antMatchers( PUBLIC ).permitAll()
                                .antMatchers( HttpMethod.GET, OPERATOR_OR_ADMIN ).permitAll()
                                .antMatchers(  OPERATOR_OR_ADMIN ).hasAnyRole( ROLE_OPERATOR, ROLE_ADMIN )
                                .antMatchers( ADMIN ).hasRole( ROLE_ADMIN )
                                .anyRequest().authenticated();
    }
}
