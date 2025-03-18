package org.yandrut.gmail_at.drivers;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverWaiter {

    public static final Duration VISIBILITY_TIMEOUT = Duration.ofSeconds(10L);
    private static final WebDriverWait wait = new WebDriverWait(DriverProvider.getDriver(), VISIBILITY_TIMEOUT);
    private static final Logger log = LogManager.getLogger(DriverWaiter.class);

    private static final String JS_AJAX_PROGRESS =
        "var userWindow = window;"
            + "var docReady = window.document.readyState == 'complete';"
            + "var isJqueryComplete = typeof(userWindow.jQuery) != 'function' || userWindow.jQuery.active == 0;"
            + "var isPrototypeComplete = typeof(userWindow.Ajax) != 'function' "
            + "|| userWindow.Ajax.activeRequestCount == 0;"
            + "var isDojoComplete = typeof(userWindow.dojo) != 'function' "
            + "|| userWindow.dojo.io.XMLHTTPTransport.inFlight.length == 0;"
            + "var result = docReady && isJqueryComplete && isPrototypeComplete && isDojoComplete;"
            + "return result;";

    private static final String JS_ANIMATION_PROGRESS =
        "var anim = 0; var anim = $(':animated'); "
            + "return anim.length == 0";

    private static final ExpectedCondition<Object> isAJAXCompleted = webDriver -> {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        return Boolean.parseBoolean(js.executeScript(JS_AJAX_PROGRESS).toString());
    };

    private static final ExpectedCondition<Object> isAnimated = webDriver -> {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        return Boolean.parseBoolean(js.executeScript(JS_ANIMATION_PROGRESS).toString());
    };

    public static void waitForPageUpdate() {
        try {
            wait.until(isAJAXCompleted);
            wait.until(isAnimated);
        } catch (WebDriverException e) {
            log.debug("Waiting for page to update");
        }
    }

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