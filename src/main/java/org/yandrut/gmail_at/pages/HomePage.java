package org.yandrut.gmail_at.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.yandrut.gmail_at.driver.waits.DriverWaiter;
import org.yandrut.gmail_at.element.Button;
import org.yandrut.gmail_at.element.Image;
import org.yandrut.gmail_at.element.InputField;
import org.yandrut.gmail_at.element.Label;
import org.yandrut.gmail_at.model.User;

public class HomePage extends AbstractPage {

    @FindBy(css = "#identifierId")
    private InputField emailField;

    @FindBy(xpath = "//div[@id='identifierNext']//button")
    private Button submitEmail;

    @FindBy(css = "[type='password']")
    private InputField passwordField;

    @FindBy(css = "div#passwordNext button")
    private Button submitPassword;

    @FindBy(css = "a[title='Gmail'] > img")
    private Image gmailLogo;

    @FindBy(xpath = "//*[contains(@href, '#draft')]")
    private Button draftFolderLink;

    @FindBy(css = "div[role='navigation'] div[role='button']")
    private Button writeNewMail;

    @FindBy(css = "tr[draggable='false'] div[role='link'] div > div > span > span")
    private List<Label> inboxMail;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void loginToMailServiceAs(User user) {
        emailField.sendKeys(user.email());
        submitEmail.click("Submit email button");
        passwordField.sendKeys(user.password());
        submitPassword.click("Submit password button");
    }

    public boolean isUserLoggedIn() {
        return gmailLogo.isImagePresent("Gmail logo on the mail page");
    }

    public void clickOnWriteNewMail() {
        writeNewMail.click("Write new mail button");
    }

    public void openDraftsFolder() {
        draftFolderLink.click("Draft folder link");
    }

    public List<String> getAllMails() {
        DriverWaiter.waitForListOfElementsToBePresent(inboxMail);
        return inboxMail.stream()
                        .map(Label::getText)
                        .toList();
    }
}