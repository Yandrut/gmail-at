package org.yandrut.gmail_at.driver.waits;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.yandrut.gmail_at.driver.DriverProvider;
import org.yandrut.gmail_at.element.Element;

public class CustomExpectedConditions {

    public static ExpectedCondition<Boolean> documentReadyState() {
        return webDriver -> ((JavascriptExecutor) DriverProvider.getDriver())
            .executeScript("return document.readyState").equals("complete");
    }

    public static ExpectedCondition<WebElement> waitUntilInteractableAndReturn(final By by) {
        return driver -> elementIfVisible(DriverProvider.getDriver().findElement(by));
    }

    private static WebElement elementIfVisible(WebElement element) {
        return element.isDisplayed() && element.isEnabled() ? element : null;
    }

    public static ExpectedCondition<Boolean> elementIsInteractable(final WebElement element) {
        return driver -> element.isDisplayed() && element.isDisplayed();
    }

    public static ExpectedCondition<List<? extends Element>> visibilityOfAllElements(
        final List<? extends Element> elements) {
        return driver -> {
            for (Element element : elements) {
                if (!element.isDisplayed()) {
                    return null;
                }
            }
            return !elements.isEmpty() ? elements : null;
        };
    }
}
