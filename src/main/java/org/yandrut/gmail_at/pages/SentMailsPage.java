package org.yandrut.gmail_at.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class SentMailsPage extends AbstractPage {

    private static final String BLANK_LOCATOR_FOR_TEXT = "//*[@class='bog']/*[contains(text(), '%s')]";

    public boolean isEmailWithSubjectPresent(String emailSubject) {
        String firstWord = splitStringIntoSeparateWords(emailSubject)[0];
        SelenideElement emailTopic = findElementByXPath(String.format(BLANK_LOCATOR_FOR_TEXT, firstWord));
        return emailTopic.shouldBe(Condition.visible).getText().equals(emailSubject);
    }
}