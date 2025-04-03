package org.yandrut.gmail_at.pages;

import static com.codeborne.selenide.Selenide.$$x;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class SentMailsPage extends AbstractPage {

    private static final String BLANK_LOCATOR_FOR_TEXT = "//*[@class='bog']/*[contains(text(), '%s')]";

    public boolean isEmailWithSubjectPresent(String emailSubject) {
        String firstWord = splitStringIntoSeparateWords(emailSubject)[0];
        String locator = String.format(BLANK_LOCATOR_FOR_TEXT, firstWord);
        ElementsCollection emailTopics = $$x(locator);
        SelenideElement wantedElement = getElementWithTextFromCollection(emailTopics, emailSubject);
        return wantedElement.getText().equals(emailSubject);
    }
}