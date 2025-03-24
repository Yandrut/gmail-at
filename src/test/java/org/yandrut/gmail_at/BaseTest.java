package org.yandrut.gmail_at;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.yandrut.gmail_at.configuration.SelenideConfiguration;
import org.yandrut.gmail_at.model.User;
import org.yandrut.gmail_at.pages.DraftsPage;
import org.yandrut.gmail_at.pages.HomePage;
import org.yandrut.gmail_at.pages.MailModalWindow;
import org.yandrut.gmail_at.pages.SentMailsPage;
import org.yandrut.gmail_at.utils.DataReader;

public class BaseTest {

    String BASE_URL = DataReader.getData("base.url");
    String EMAIL = System.getenv("AT_GOOGLE_MAIL");
    String PASSWORD = System.getenv("AT_GOOGLE_PASSWORD");

    HomePage homePage = new HomePage();
    MailModalWindow modalWindow = new MailModalWindow();
    DraftsPage draftsPage = new DraftsPage();
    SentMailsPage sentMailsPage = new SentMailsPage();

    @BeforeEach
    public void setup() {
        SelenideConfiguration.configureSelenide();
        open(BASE_URL);
        User user = new User(EMAIL, PASSWORD);
        homePage.loginToMailServiceAs(user);
    }

    @AfterEach
    public void closeBrowser() {
        closeWebDriver();
    }
}