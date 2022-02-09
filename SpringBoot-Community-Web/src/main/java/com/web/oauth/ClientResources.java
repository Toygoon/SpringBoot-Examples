package com.web.oauth;

import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;

public class ClientResources {
    // @NestedConfigurationProperty : For the duplicated binding
    @NestedConfigurationProperty
    // AuthorizationCodeResourceDetails : Map the key/value object by 'client'
    private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();

    @NestedConfigurationProperty
    private ResourceServerProperties resource = new ResourceServerProperties();

    /* Could be replaced with Getter below */
    public AuthorizationCodeResourceDetails getClient() {
        return client;
    }

    public ResourceServerProperties getResource() {
        return resource;
    }
}
