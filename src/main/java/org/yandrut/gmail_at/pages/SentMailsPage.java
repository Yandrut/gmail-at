package org.yandrut.gmail_at.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SentMailsPage extends AbstractPage {

    @FindBy(xpath = "//a[@href='#inbox']")
    private WebElement inboxLink;

    private static final String BLANK_LOCATOR_FOR_TEXT = "//*[@class='bog']/*[contains(text(), '%s')]";

    public SentMailsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isEmailWithSubjectPresent(String emailSubject) {
        String firstWord = splitStringIntoSeparateWords(emailSubject)[0];
        WebElement emailTopic = findElementByXPath(String.format(BLANK_LOCATOR_FOR_TEXT, firstWord));
        return getText(emailTopic).equals(emailSubject);
    }
}