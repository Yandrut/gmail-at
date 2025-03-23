package org.yandrut.gmail_at.pages;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.yandrut.gmail_at.model.User;

public class HomePage extends AbstractPage {

    @FindBy(css = "#identifierId")
    private WebElement emailField;

    @FindBy(xpath = "//div[@id='identifierNext']//button")
    private WebElement nextAfterEmail;

    @FindBy(css = "[type='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//div[@id='passwordNext']//button")
    private WebElement nextAfterPassword;

    @FindBy(xpath = "//img[contains(@src, 'logo_gmail_lockup')]")
    private WebElement gmailLogo;

    @FindBy(xpath = "//div[@role='navigation']//div[@role='button']")
    private WebElement writeNewMail;

    @FindBy(xpath = "//*[contains(@href, '#draft')]")
    private WebElement draftFolderLink;

    @FindBy(xpath = "//*[@class='bog']/span")
    private List<WebElement> inboxMail;

    private static final Logger log = LogManager.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void loginToMailServiceAs(User user) {
        sendKeys(emailField, user.getEmail());
        click(nextAfterEmail, "Submit email button");
        sendKeys(passwordField, user.getPassword());
        click(nextAfterPassword, "Submit password button");
    }

    public boolean isUserLoggedIn() {
        return isElementPresent(gmailLogo, "Gmail logo on the mail page");
    }

    public void clickOnWriteNewMail() {
        click(writeNewMail, "Write new mail button");
    }

    public void openDraftsFolder() {
        click(draftFolderLink, "Draft folder link");
    }

    public String getAllMailsFromInbox() {
        String visibleMails = getTextOfVisibleElements(inboxMail);
        log.debug("Mails found on the page: {}", visibleMails);
        return visibleMails;
    }
}