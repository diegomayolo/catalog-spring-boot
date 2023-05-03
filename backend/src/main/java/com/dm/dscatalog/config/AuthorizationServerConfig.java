package com.dm.dscatalog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 *
 * @author dm
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter
{
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Autowired
    private JwtTokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * configure
     *
     * @param security AuthorizationServerSecurityConfigurer
     * @throws Exception
     */
    @Override
    public void configure( AuthorizationServerSecurityConfigurer security ) throws Exception
    {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    /**
     * configure
     *
     * @param clients ClientDetailsServiceConfigurer
     * @throws Exception
     */
    @Override
    public void configure( ClientDetailsServiceConfigurer clients ) throws Exception
    {
        clients.inMemory().withClient( "dscatalog" )
                .secret( passwordEncoder.encode( "dscatalog123" ) )
                .scopes( "read", "write" )
                .authorizedGrantTypes( "password" )
                .accessTokenValiditySeconds( 86400 );
    }

    /**
     * configure
     *
     * @param endpoints AuthorizationServerEndpointsConfigurer
     * @throws Exception
     */
    @Override
    public void configure( AuthorizationServerEndpointsConfigurer endpoints ) throws Exception
    {
        endpoints.authenticationManager( authenticationManager )
                .tokenStore( tokenStore )
                .accessTokenConverter( accessTokenConverter );
    }
}
