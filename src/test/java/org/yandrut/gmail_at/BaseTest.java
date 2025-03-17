package org.yandrut.gmail_at;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.yandrut.gmail_at.drivers.DriverProvider;
import org.yandrut.gmail_at.model.User;
import org.yandrut.gmail_at.pages.DraftsPage;
import org.yandrut.gmail_at.pages.HomePage;
import org.yandrut.gmail_at.pages.MailModalWindow;
import org.yandrut.gmail_at.pages.SentMailsPage;

@SpringBootTest
public class BaseTest {

    @Value("${test.url}")
    String BASE_URL;

    @Value("${AT_GOOGLE_MAIL}")
    String EMAIL;

    @Value("${AT_GOOGLE_PASSWORD}")
    String PASSWORD;

    @Autowired
    HomePage mailServicePage;

    @Autowired
    MailModalWindow modalWindow;

    @Autowired
    DraftsPage draftsPage;

    @Autowired
    SentMailsPage sentMailsPage;

    @Autowired
    WebDriver driver;

    @BeforeEach
    public void setup() {
        driver.get(BASE_URL);
        User user = new User(EMAIL, PASSWORD);
        mailServicePage.loginToMailServiceAs(user);
    }

    @AfterEach()
    public void closeBrowser() {
        DriverProvider.quit();
    }
}