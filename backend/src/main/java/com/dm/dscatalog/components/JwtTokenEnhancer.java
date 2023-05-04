package com.dm.dscatalog.components;

import com.dm.dscatalog.entities.User;
import com.dm.dscatalog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dm
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2AccessToken enhance( OAuth2AccessToken accessToken, OAuth2Authentication authentication )
    {

        User user = userRepository.findByEmail(authentication.getName() );

        Map<String, Object> map = new HashMap<>();
        map.put("user_id", user.getId() );
        map.put("first_name", user.getFirstName() );

        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        token.setAdditionalInformation( map );

        return accessToken;
    }
}
