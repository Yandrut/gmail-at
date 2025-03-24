package org.yandrut.gmail_at.pages;

import com.codeborne.selenide.SelenideElement;

public class DraftsPage extends AbstractPage {

    private static final String BLANK_LOCATOR_FOR_TEXT = "//*[@class='bog']/*[contains(text(), '%s')]";

    public void clickToTheDraftWith(String draftSubject) {
        String subjectFirstWord = splitStringIntoSeparateWords(draftSubject)[0];
        String draftLocator = String.format(BLANK_LOCATOR_FOR_TEXT, subjectFirstWord);
        SelenideElement draftLink = findElementByXPath(draftLocator);
        click(draftLink, String.format("the draft: '%s'", draftSubject));
    }
}