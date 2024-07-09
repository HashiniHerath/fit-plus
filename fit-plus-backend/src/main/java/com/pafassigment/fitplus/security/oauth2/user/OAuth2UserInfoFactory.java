package com.pafassigment.fitplus.security.oauth2.user;


import com.pafassigment.fitplus.enumeration.AuthProvider;
import com.pafassigment.fitplus.exception.OAuth2AuthenticationProcessingException;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
