package org.yandrut.gmail_at.driver.waits;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.yandrut.gmail_at.driver.waits.CustomExpectedConditions.documentReadyState;
import static org.yandrut.gmail_at.driver.waits.CustomExpectedConditions.elementIsInteractable;
import static org.yandrut.gmail_at.driver.waits.CustomExpectedConditions.visibilityOfAllElements;
import static org.yandrut.gmail_at.driver.waits.CustomExpectedConditions.waitUntilInteractableAndReturn;

import java.time.Duration;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yandrut.gmail_at.driver.DriverProvider;
import org.yandrut.gmail_at.element.Element;

public class DriverWaiter {

    public static final Duration VISIBILITY_TIMEOUT = Duration.ofSeconds(10L);
    private static final WebDriverWait wait = new WebDriverWait(DriverProvider.getDriver(), VISIBILITY_TIMEOUT);
    private static final Logger log = LogManager.getLogger(DriverWaiter.class);

    public static void waitForElementToBeInteractable(WebElement element) {
        wait.until(elementIsInteractable(element));
    }

    public static void waitForListOfElementsToBePresent(List<? extends Element> elementList) {
        wait.until(visibilityOfAllElements(elementList));
    }

    public static WebElement waitToBeInteractableAndReturn(By locator) {
        return wait.until(waitUntilInteractableAndReturn(locator));
    }

    public static void waitForElementToBeClickable(WebElement element) {
        log.debug("Waiting for element to be clickable");
        wait.until(elementToBeClickable(element));
    }

    public static void waitForJSComplete() {
        try {
            wait.until(documentReadyState());
        } catch (WebDriverException e) {
            log.debug("Unable to get JS answer {}", e.getMessage());
        }
    }
}