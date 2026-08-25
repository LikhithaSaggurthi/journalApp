package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    public void testSendEmail(){
        emailService.sendEmail("srinivas.saggurthi@gmail.com","Testing Java Mail Sender","Sentiment Analysis for the last 7 days");
    }
}
