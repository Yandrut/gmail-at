package org.yandrut.gmail_at;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.yandrut.gmail_at.driver.DriverProvider;
import org.yandrut.gmail_at.model.User;
import org.yandrut.gmail_at.pages.DraftsPage;
import org.yandrut.gmail_at.pages.HomePage;
import org.yandrut.gmail_at.pages.MailModalWindow;
import org.yandrut.gmail_at.pages.SentMailsPage;
import org.yandrut.gmail_at.utils.DataReader;

public class BaseTest {

    String BASE_URL = DataReader.getData("test.url");
    String EMAIL = System.getenv("AT_GOOGLE_MAIL");
    String PASSWORD = System.getenv("AT_GOOGLE_PASSWORD");

    HomePage homePage;
    MailModalWindow modalWindow;
    DraftsPage draftsPage;
    SentMailsPage sentMailsPage;

    @BeforeEach
    public void setup() {
        var driver = DriverProvider.getDriver();
        driver.get(BASE_URL);
        var user = new User(EMAIL, PASSWORD);
        homePage = new HomePage(driver);
        homePage.loginToMailServiceAs(user);
        modalWindow = new MailModalWindow(driver);
        draftsPage = new DraftsPage(driver);
        sentMailsPage = new SentMailsPage(driver);
    }

    @AfterEach
    public void closeBrowser() {
        DriverProvider.quit();
    }
}