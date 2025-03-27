package org.yandrut.gmail_at.pages;

import static org.yandrut.gmail_at.utils.StringUtils.splitStringIntoSeparateWords;

import org.openqa.selenium.WebDriver;
import org.yandrut.gmail_at.element.Label;

public class SentMailsPage extends AbstractPage {

    private static final String BLANK_LOCATOR_FOR_TEXT = "//*[@class='bog']/*[contains(text(), '%s')]";

    public SentMailsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isEmailWithSubjectPresent(String emailSubject) {
        var firstWord = splitStringIntoSeparateWords(emailSubject)[0];
        var emailTopic = Label.findElementByXpath(String.format(BLANK_LOCATOR_FOR_TEXT, firstWord));
        return emailTopic.getText().equals(emailSubject);
    }
}