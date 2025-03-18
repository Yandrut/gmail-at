package org.yandrut.gmail_at.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SentMailsPage extends AbstractPage {

    @FindBy(xpath = "//a[@href='#inbox']")
    private WebElement inboxLink;

    @FindBy(css = "[style='color: #666']")
    private WebElement inboxLabel;

    private static final String BLANK_LOCATOR_FOR_TEXT = "//*[@class='bog']/*[contains(text(), '%s')]";

    public SentMailsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isEmailWithSubjectPresent(String emailSubject) {
        String firstWord = splitStringIntoSeparateWords(emailSubject)[0];
        WebElement emailTopic = findElementByXpath(String.format(BLANK_LOCATOR_FOR_TEXT, firstWord));
        return getText(emailTopic).equals(emailSubject);
    }

    public boolean isSentPageOpen() {
        return isElementPresent(inboxLabel, "Sent mail page");
    }

    public void moveToHome() {
        click(inboxLink, "inbox link");
    }
}