package org.yandrut.gmail_at.pages;

import java.util.List;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.yandrut.gmail_at.exception.ElementNotPresentException;
import org.yandrut.gmail_at.model.User;

@Lazy
@Component
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

    @FindBy(xpath = "//*[contains(@href, 'draft')]")
    private WebElement draftFolderLink;

    @FindBy(xpath = "//div[@role='navigation']//div[@role='button']")
    private WebElement writeNewMail;

    @FindBy(xpath = "//tr[@draggable='false']")
    private List<WebElement> inboxMail;

    @Autowired
    public HomePage() {
        super();
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

    public MailModalWindow clickOnWriteNewMail() {
        click(writeNewMail, "Write new mail button");
        return new MailModalWindow();
    }

    public void openDraftsFolder() {
        click(draftFolderLink, "Draft folder link");
    }

    public boolean isMailWithSubjectPresent(String mailSubject) {
        return findMailWithSubject(mailSubject).getText().equals(mailSubject);
    }

    public WebElement findMailWithSubject(String mailSubject) {
        return inboxMail.stream()
                        .filter((element) -> element.getText().equals(mailSubject))
                        .findAny()
                        .orElseThrow(() -> new ElementNotPresentException("Element is not present on the page"));
    }

    public long getAllInboxEmailsCount() {
        return inboxMail.stream()
                        .map(WebElement::getText)
                        .filter(s -> !s.isEmpty())
                        .count();
    }

    public void deleteAllMails() {

    }
}