package org.yandrut.gmail_at.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.yandrut.gmail_at.drivers.DriverWaiter;

@Lazy
@Component
public class DraftsPage extends AbstractPage {

    @FindBy(xpath = " //*[@id=':67']/div[1]/span")
    private WebElement selectAllDrafts;

    @FindBy(xpath = "//div[@act='16']")
    private WebElement deleteSelectedDraftsButton;

    @FindBy(xpath = "//a[contains(@href, '#sent')]")
    private WebElement sentPageLink;

    @FindBy(css = "[translate='no']")
    private WebElement draftsLabel;

    private static final String BLANK_LOCATOR_FOR_TEXT = "//*[@class='bog']/*[contains(text(), '%s')]";

    public DraftsPage() {
        super();
    }

    public void clickToTheDraftWith(String draftSubject) {
        String subjectFirstWord = splitStringIntoSeparateWords(draftSubject)[0];
        DriverWaiter.waitForJSComplete();
        By draftLocator = By.xpath(String.format(BLANK_LOCATOR_FOR_TEXT, subjectFirstWord));

        WebElement draftLink = DriverWaiter.waitForElementToBeClickableAndReturn(draftLocator);
        click(draftLink, String.format("the draft: '%s'", draftSubject));
    }

    public boolean isDraftPageOpen() {
        return isElementPresent(draftsLabel, "Any draft label");
    }
}