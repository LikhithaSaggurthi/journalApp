package net.engineeringdigest.journalApp.controller;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.service.UserDetailsServiceImpl;
import net.engineeringdigest.journalApp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/auth/google")
@Slf4j
public class GoogleAuthController {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    @Value("${google.oauth.redirect-uri}")
    private String redirectUri;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;


    // Starts Google OAuth login
    @GetMapping("/login")
    public void googleLogin(HttpServletResponse response) throws IOException {

        String googleAuthUrl =
                "https://accounts.google.com/o/oauth2/auth"
                        + "?client_id=" + clientId
                        + "&redirect_uri=" + URLEncoder.encode(
                        redirectUri,
                        "UTF-8"
                )
                        + "&response_type=code"
                        + "&scope=email%20profile"
                        + "&access_type=offline"
                        + "&prompt=consent";

        response.sendRedirect(googleAuthUrl);
    }


    // Handles Google's callback
    @GetMapping("/callback")
    public String handleGoogleCallback(
            @RequestParam String code,
            Model model) {

        try {

            String tokenEndpoint =
                    "https://oauth2.googleapis.com/token";

            MultiValueMap<String, String> params =
                    new LinkedMultiValueMap<>();

            params.add("code", code);
            params.add("client_id", clientId);
            params.add("client_secret", clientSecret);
            params.add("redirect_uri", redirectUri);
            params.add("grant_type", "authorization_code");

            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(
                    MediaType.APPLICATION_FORM_URLENCODED
            );

            HttpEntity<MultiValueMap<String, String>> request =
                    new HttpEntity<>(params, headers);

            ResponseEntity<Map> tokenResponse =
                    restTemplate.postForEntity(
                            tokenEndpoint,
                            request,
                            Map.class
                    );

            String idToken =
                    (String) tokenResponse.getBody().get("id_token");


            String userInfoUrl =
                    "https://oauth2.googleapis.com/tokeninfo?id_token="
                            + idToken;

            ResponseEntity<Map> userInfoResponse =
                    restTemplate.getForEntity(
                            userInfoUrl,
                            Map.class
                    );


            if (userInfoResponse.getStatusCode() == HttpStatus.OK) {

                Map<String, Object> userInfo =
                        userInfoResponse.getBody();

                String email =
                        (String) userInfo.get("email");

                String name =
                        (String) userInfo.get("name");


                try {

                    userDetailsService.loadUserByUsername(email);

                } catch (Exception e) {

                    User user = new User();

                    user.setEmail(email);
                    user.setUserName(email);

                    user.setPassword(
                            passwordEncoder.encode(
                                    UUID.randomUUID().toString()
                            )
                    );

                    user.setRoles(
                            Arrays.asList("USER")
                    );

                    userRepository.save(user);
                }


                // Generate JWT
                String jwtToken =
                        jwtUtil.generateToken(email);


                // Send information to success.html
                model.addAttribute("name", name);
                model.addAttribute("email", email);

                return "success";
            }

            return "redirect:/";

        } catch (Exception e) {

            log.error(
                    "Exception occurred while handling Google OAuth",
                    e
            );

            model.addAttribute(
                    "error",
                    "Google sign-in failed. Please try again."
            );

            return "error";
        }
    }
}