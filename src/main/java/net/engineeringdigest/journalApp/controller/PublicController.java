package net.engineeringdigest.journalApp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.dto.UserDTO;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserDetailsServiceImpl;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import net.engineeringdigest.journalApp.utils.JwtUtil;


@Slf4j
@RestController
@RequestMapping("/public")
@Tag(name = "Public APIs")
public class PublicController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private  JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck() {
        log.info("Health is OK");
        return "OK";
    }

    @PostMapping("/signup")
    public void signup(@RequestBody UserDTO user) {
        User newUser = new User();

        newUser.setEmail(user.getEmail());
        newUser.setUserName(user.getUserName());
        newUser.setPassword(user.getPassword());
        newUser.setSentimentAnalysis(user.isSentimentAnalysis());

        userService.saveNewUser(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetails=userDetailsService.loadUserByUsername(user.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch(Exception e){
            log.error("exception occured while creating AuthenticationToken");
            return new ResponseEntity<>("Incorrect Username or password",HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/privacy-policy")
    public String privacyPolicy() {
        return "<!DOCTYPE html>" +
                "<html lang='en'>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                "<title>Privacy Policy | Journal App</title>" +

                "<style>" +
                "* { box-sizing: border-box; margin: 0; padding: 0; }" +

                "body {" +
                "    font-family: Arial, Helvetica, sans-serif;" +
                "    line-height: 1.7;" +
                "    color: #333;" +
                "    background: #f5f7fb;" +
                "    margin: 0;" +
                "}" +

                ".header {" +
                "    background: #1f2937;" +
                "    color: white;" +
                "    padding: 40px 20px;" +
                "    text-align: center;" +
                "}" +

                ".header h1 {" +
                "    font-size: 32px;" +
                "    margin-bottom: 8px;" +
                "}" +

                ".header p {" +
                "    color: #d1d5db;" +
                "    font-size: 15px;" +
                "}" +

                ".container {" +
                "    max-width: 900px;" +
                "    margin: 40px auto;" +
                "    padding: 0 20px;" +
                "}" +

                ".card {" +
                "    background: white;" +
                "    padding: 45px;" +
                "    border-radius: 12px;" +
                "    box-shadow: 0 4px 20px rgba(0,0,0,0.08);" +
                "}" +

                ".intro {" +
                "    font-size: 16px;" +
                "    color: #555;" +
                "    margin-bottom: 30px;" +
                "}" +

                "section {" +
                "    margin-bottom: 30px;" +
                "}" +

                "h2 {" +
                "    font-size: 21px;" +
                "    color: #1f2937;" +
                "    margin-bottom: 10px;" +
                "    border-bottom: 1px solid #e5e7eb;" +
                "    padding-bottom: 8px;" +
                "}" +

                "p {" +
                "    margin-bottom: 12px;" +
                "}" +

                "ul {" +
                "    margin: 10px 0 10px 25px;" +
                "}" +

                "li {" +
                "    margin-bottom: 8px;" +
                "}" +

                ".contact {" +
                "    background: #f3f4f6;" +
                "    padding: 20px;" +
                "    border-radius: 8px;" +
                "}" +

                ".contact a {" +
                "    color: #2563eb;" +
                "    text-decoration: none;" +
                "}" +

                ".footer {" +
                "    text-align: center;" +
                "    padding: 30px 20px;" +
                "    color: #6b7280;" +
                "    font-size: 14px;" +
                "}" +

                "@media (max-width: 600px) {" +
                "    .card {" +
                "        padding: 25px;" +
                "    }" +

                "    .header h1 {" +
                "        font-size: 26px;" +
                "    }" +

                "    .container {" +
                "        margin: 20px auto;" +
                "    }" +
                "}" +

                "</style>" +
                "</head>" +

                "<body>" +

                "<header class='header'>" +
                "    <h1>Journal App</h1>" +
                "    <p>Privacy Policy</p>" +
                "</header>" +

                "<main class='container'>" +

                "<div class='card'>" +

                "<p class='intro'>" +
                "    Your privacy is important to us. This Privacy Policy explains " +
                "how Journal App collects, uses, and protects information when you " +
                "use our application." +
                "</p>" +

                "<section>" +
                "    <h2>1. Information We Collect</h2>" +
                "    <p>When you use Journal App, we may collect the following information:</p>" +
                "    <ul>" +
                "        <li>Your name and username.</li>" +
                "        <li>Your email address.</li>" +
                "        <li>Journal entries and information that you choose to provide.</li>" +
                "        <li>Information required to authenticate and secure your account.</li>" +
                "    </ul>" +
                "</section>" +

                "<section>" +
                "    <h2>2. Google Sign-In</h2>" +
                "    <p>" +
                "        Journal App may allow you to sign in using your Google account. " +
                "        When you use Google Sign-In, we may receive basic account " +
                "        information such as your name and email address as provided " +
                "        by Google." +
                "    </p>" +
                "    <p>" +
                "        We use this information to create and manage your Journal App " +
                "        account and provide authentication services." +
                "    </p>" +
                "</section>" +

                "<section>" +
                "    <h2>3. How We Use Your Information</h2>" +
                "    <p>Information collected through Journal App may be used to:</p>" +
                "    <ul>" +
                "        <li>Create and manage your account.</li>" +
                "        <li>Authenticate users and protect account security.</li>" +
                "        <li>Store and display your journal entries.</li>" +
                "        <li>Provide and improve application functionality.</li>" +
                "        <li>Communicate with you regarding the application when necessary.</li>" +
                "    </ul>" +
                "</section>" +

                "<section>" +
                "    <h2>4. Data Security</h2>" +
                "    <p>" +
                "        We take reasonable technical and organizational measures to " +
                "        protect your information against unauthorized access, alteration, " +
                "        disclosure, or destruction. However, no method of electronic " +
                "        storage or transmission can be guaranteed to be completely secure." +
                "    </p>" +
                "</section>" +

                "<section>" +
                "    <h2>5. Data Sharing</h2>" +
                "    <p>" +
                "        We do not sell or rent your personal information. Information " +
                "        may only be shared with third-party services when necessary to " +
                "        provide application functionality, authentication, or other " +
                "        services that you have requested." +
                "    </p>" +
                "</section>" +

                "<section>" +
                "    <h2>6. Data Retention</h2>" +
                "    <p>" +
                "        We retain information for as long as necessary to provide the " +
                "        application and maintain your account, unless a longer retention " +
                "        period is required by law." +
                "    </p>" +
                "</section>" +

                "<section>" +
                "    <h2>7. Your Rights</h2>" +
                "    <p>" +
                "        Depending on applicable law, you may have the right to access, " +
                "        correct, or request deletion of your personal information. " +
                "        You may contact us using the information below for questions " +
                "        or requests regarding your data." +
                "    </p>" +
                "</section>" +

                "<section>" +
                "    <h2>8. Changes to This Privacy Policy</h2>" +
                "    <p>" +
                "        We may update this Privacy Policy from time to time. Any changes " +
                "        will be reflected on this page. We encourage you to review this " +
                "        page periodically for the latest information." +
                "    </p>" +
                "</section>" +

                "<section>" +
                "    <h2>9. Contact Us</h2>" +
                "    <div class='contact'>" +
                "        <p>If you have any questions about this Privacy Policy, please contact us:</p>" +
                "        <p><strong>Email:</strong> " +
                "        <a href='mailto:likhithasaggurthi@gmail.com'>" +
                "        likhithasaggurthi@gmail.com</a></p>" +
                "    </div>" +
                "</section>" +

                "</div>" +
                "</main>" +

                "<footer class='footer'>" +
                "    &copy; 2026 Journal App. All rights reserved." +
                "</footer>" +

                "</body>" +
                "</html>";
    }
}
