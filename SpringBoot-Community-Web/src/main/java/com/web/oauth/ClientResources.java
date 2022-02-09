package com.web.oauth;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

public class ClientResources {
    // @NestedConfigurationProperty : For the duplicated binding
    @NestedConfigurationProperty
    // AuthorizationCodeResourceDetails : Map the key/value object by 'client'
    private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();

    @NestedConfigurationProperty
    // OAuth2ResourceServerProperties : To use userInfoUri
    private OAuth2ResourceServerProperties resource = new OAuth2ResourceServerProperties();

    /* Could be replaced with Getter below */
    public AuthorizationCodeResourceDetails getClient() {
        return client;
    }

    public OAuth2ResourceServerProperties getResource() {
        return resource;
    }
}
