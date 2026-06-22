package com.example.Oauth2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class that enables authentication using
 * form-based login and OAuth2 login providers such as GitHub and Facebook.
 * <p>
 * Endpoints under '/secure' require authentication, while all other
 * endpoints are accessible without login.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    /**
     * Configures the application's security rules and authentication mechanisms.
     * <p>
     * This filter chain enables form login, OAuth2 login, and restricts
     * access to '/secure' endpoints to authenticated users only. After a
     * successful OAuth2 login, users are redirected to the '/home' page.
     *
     * @param http HttpSecurity object used to configure security settings
     * @return configured SecurityFilterChain
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http.authorizeHttpRequests(a -> a.requestMatchers("/secure").authenticated()
                        .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .csrf(c -> c.disable())
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/home", true))
                .build();
    }

    /**
     * Creates an in-memory repository containing the OAuth2 client
     * registrations used by the application.
     * <p>
     * The repository stores the configuration details required to
     * authenticate users through GitHub and Facebook.
     *
     * @return repository containing OAuth2 client registrations
     */
    @Bean
    public ClientRegistrationRepository clientRegistration() {
        return new InMemoryClientRegistrationRepository(gitHubClientRegistration(), faceBookRegistration());
    }

    /**
     * Creates the OAuth2 client registration for GitHub.
     * <p>
     * This registration contains the client credentials and endpoint
     * information required to authenticate users using their GitHub account.
     *
     * @return GitHub client registration
     */
    private ClientRegistration gitHubClientRegistration() {
        return CommonOAuth2Provider.GITHUB.getBuilder("github").clientId("Iv23liAnPREXKaxwsVCu")
                .clientSecret("cddcfad3d4c4a7a6b37ba0bb8d6d9e1f411a634d")
                .build();
    }

    /**
     * Creates the OAuth2 client registration for Facebook.
     * <p>
     * This registration contains the client credentials and endpoint
     * information required to authenticate users using their Facebook account.
     *
     * @return Facebook client registration
     */
    private ClientRegistration faceBookRegistration() {
        return CommonOAuth2Provider.FACEBOOK.getBuilder("FaceBook").clientId("sdfj23oi5jkg")
                .clientSecret("ksdjfh32o8nklsd9jml3lk").build();
    }
}
