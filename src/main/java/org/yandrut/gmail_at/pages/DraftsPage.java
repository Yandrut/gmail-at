package org.yandrut.gmail_at.pages;

import static com.codeborne.selenide.Selenide.$$x;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class DraftsPage extends AbstractPage {

    private static final String BLANK_LOCATOR_FOR_TEXT = "//*[@class='bog']/*[contains(text(), '%s')]";

    public void clickToTheDraftWith(String draftSubject) {
        String subjectFirstWord = splitStringIntoSeparateWords(draftSubject)[0];
        String draftLocator = String.format(BLANK_LOCATOR_FOR_TEXT, subjectFirstWord);
        ElementsCollection draftLinks = $$x(draftLocator);
        SelenideElement wantedElement = getElementWithTextFromCollection(draftLinks, draftSubject);
        click(wantedElement, String.format("the draft: '%s'", draftSubject));
    }
}