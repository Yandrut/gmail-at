package org.yandrut.gmail_at.pages;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public abstract class AbstractPage {

    private static final Logger log = LogManager.getLogger(AbstractPage.class);

    private static SelenideElement highlightElement(SelenideElement element) {
        return element.shouldBe(visible, enabled).highlight();
    }

    public void click(SelenideElement element, String logInfo) {

        for (int i = 0; i < 10; i++) {
            try {
                log.info("Trying to click on: {} ", logInfo);
                highlightElement(element)
                    .shouldBe(clickable)
                    .click();
                break;
            } catch (NoSuchElementException e) {
                log.debug("Error occurred while clicking {}", e.getMessage());
            }
        }
    }

    public void sendKeys(SelenideElement element, String sequence) {
        for (int i = 0; i < 10; i++) {
            try {
                log.info("Sending key sequence: {}", sequence);
                highlightElement(element)
                    .setValue(sequence);
                break;
            } catch (NoSuchElementException e) {
                log.debug("Error occurred while sending keys: {}", e.getMessage());
            }
        }
    }

    public void clickUsingActions(SelenideElement element) {
        highlightElement(element);
        WebElement webElement = element.toWebElement();
        new Actions(WebDriverRunner.getWebDriver())
            .moveToElement(webElement)
            .click()
            .perform();
    }

    public void sendKeysUsingActions(SelenideElement element, String sequence) {
        highlightElement(element);
        new Actions(WebDriverRunner.getWebDriver())
            .moveToElement(element.toWebElement())
            .click()
            .sendKeys(sequence)
            .perform();
    }

    public String getText(SelenideElement element) {
        return highlightElement(element).getText();
    }

    private void sizeGreaterThanZero(ElementsCollection elements) {
        elements.shouldBe(CollectionCondition.sizeGreaterThan(0));
    }

    public SelenideElement getElementWithTextFromCollection(ElementsCollection collection, String text) {
        sizeGreaterThanZero(collection);
        SelenideElement wantedElement = collection.findBy(text(text)).shouldBe(visible, clickable);
        highlightElement(wantedElement);
        return wantedElement;
    }

    public String getTextOfAllElements(ElementsCollection collection) {
        sizeGreaterThanZero(collection);

        String visibleMails = collection.stream()
                                        .filter(SelenideElement::isDisplayed)
                                        .map(SelenideElement::highlight)
                                        .map(SelenideElement::getText)
                                        .reduce((a, b) -> a + "; " + b)
                                        .orElse("");
        log.debug("Elements found on the page: {}", visibleMails);
        return visibleMails;
    }

    public void waitForElementNotVisible(SelenideElement element) {
        element.shouldBe(not(visible));
    }

    public boolean isElementPresent(SelenideElement element, String logInfo) {
        highlightElement(element);
        log.info("Waiting for element to be present: {}", logInfo);
        return element.isDisplayed() && element.isEnabled();
    }

    public void elementShouldHaveText(SelenideElement element, String text) {
        element.shouldHave(text(text));
    }

    public String[] splitStringIntoSeparateWords(String string) {
        return string.split("\\s+");
    }
}