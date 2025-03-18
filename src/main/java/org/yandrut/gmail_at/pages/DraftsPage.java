package org.yandrut.gmail_at.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DraftsPage extends AbstractPage {

    @FindBy(xpath = " //*[@id=':67']/div[1]/span")
    private WebElement selectAllDrafts;

    @FindBy(xpath = "//div[@act='16']")
    private WebElement deleteSelectedDraftsButton;

    @FindBy(xpath = "//a[contains(@href, '#sent')]")
    private WebElement sentPageLink;

    @FindBy(xpath = "//*[@translate='no']")
    private List<WebElement> drafts;

    private static final String BLANK_LOCATOR_FOR_TEXT = "//*[@class='bog']/*[contains(text(), '%s')]";

    public DraftsPage(WebDriver driver) {
        super(driver);
    }

    public void clickToTheDraftWith(String draftSubject) {
        String subjectFirstWord = splitStringIntoSeparateWords(draftSubject)[0];

        String draftLocator = String.format(BLANK_LOCATOR_FOR_TEXT, subjectFirstWord);

        WebElement draftLink = findElementByXPath(draftLocator);
        click(draftLink, String.format("the draft: '%s'", draftSubject));
    }

    public boolean isDraftPageOpen() {
        String draftsText = getTextOfVisibleElements(drafts);
        return draftsText.contains("Чернетка") || draftsText.contains("Draft");

    }
}