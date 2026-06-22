package com.example.Oauth2.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    /**
     * Handles requests to the '/secure' endpoint.
     * <p>
     * This endpoint is accessible only to authenticated users.
     * It inspects the Authentication object to determine whether
     * the user authenticated using form login or OAuth2 login and
     * prints the corresponding authentication details.
     *
     * @param authentication contains information about the currently
     *                       authenticated user and their authorities
     * @return a simple response message
     */
    @GetMapping("/secure")
    public String secure(Authentication authentication) {
        if (authentication instanceof UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
            System.out.println(usernamePasswordAuthenticationToken);
        } else if (authentication instanceof OAuth2AuthenticationToken oAuth2AuthenticationToken) {
            System.out.println(oAuth2AuthenticationToken);
        }
        return "hello world";
    }

    @GetMapping("/home")
    public String home() {
        return "Hey There welcome to RD Coffee shop";
    }
}
