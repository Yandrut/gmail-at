package org.yandrut.gmail_at.drivers;

import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DriverWaiter {
    public static final Duration VISIBILITY_TIMEOUT = Duration.ofSeconds(10L);
    private static final WebDriverWait wait = new WebDriverWait(DriverProvider.getDriver(), VISIBILITY_TIMEOUT);

    public static void waitForElementToBeClickable(WebElement element) {
        log.debug("Waiting for element to be clickable");
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForElementToBeClickableAndReturn(By by) {
        log.debug("Waiting for element to be clickable and return");
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitForElementToBeVisible(WebElement element) {
        log.debug("Waiting for element to appear in the DOM");
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForAttributeToBe(WebElement elementLocator, String attribute, String value) {
        log.debug("Waiting for attribute '{}' to be '{}'", attribute, value);
        wait.until(ExpectedConditions.attributeToBe(elementLocator, attribute, value));
    }

    public static void waitForJSComplete() {
        try {
            wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
        } catch (TimeoutException exception) {
            log.debug("Unable to get JS answer {}", String.valueOf(exception));
        }
    }
}