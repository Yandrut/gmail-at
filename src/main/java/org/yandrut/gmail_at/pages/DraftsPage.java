package org.yandrut.gmail_at.pages;

import static org.yandrut.gmail_at.utils.StringUtils.splitStringIntoSeparateWords;

import org.openqa.selenium.WebDriver;
import org.yandrut.gmail_at.driver.waits.DriverWaiter;
import org.yandrut.gmail_at.element.Button;

public class DraftsPage extends AbstractPage {

    private static final String BLANK_LOCATOR_FOR_TEXT = "//*[@class='bog']/*[contains(text(), '%s')]";

    public DraftsPage(WebDriver driver) {
        super(driver);
    }

    public void clickToTheDraftWith(String draftSubject) {
        String subjectFirstWord = splitStringIntoSeparateWords(draftSubject)[0];
        DriverWaiter.waitForJSComplete();
        var element = Button.findElementByXpath(String.format(BLANK_LOCATOR_FOR_TEXT, subjectFirstWord));
        element.click(String.format("the draft: '%s'", draftSubject));
    }
}