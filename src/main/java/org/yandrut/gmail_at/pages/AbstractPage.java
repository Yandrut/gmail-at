package org.yandrut.gmail_at.pages;

import java.time.Duration;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yandrut.gmail_at.drivers.DriverWaiter;

public abstract class AbstractPage {

    private static final Logger log = LogManager.getLogger(AbstractPage.class);
    private final DriverWaiter wait;
    private final WebDriver driver;
    private final int TIMEOUT_SECONDS = 10;

    public AbstractPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, TIMEOUT_SECONDS), this);
        wait = new DriverWaiter(driver);
        this.driver = driver;
    }

    public void click(WebElement element, String logInfo) {
        wait.waitForElementToBeVisible(element);
        wait.waitForElementToBeClickable(element);
        for (int i = 0; i < 10; i++) {
            try {
                log.info("Trying to click on: {} ", logInfo);
                element.click();
                break;
            } catch (WebDriverException e) {
                log.debug("Error occurred when attempting to click: {}", e.getMessage());
            }
        }
        wait.waitForPageUpdate();
    }

    public void sendKeys(WebElement element, String sequence) {
        wait.waitForPageUpdate();
        wait.waitForElementToBeVisible(element);
        log.info("Sending key sequence: {}", sequence);
        for (int i = 0; i < 5; i++) {
            try {
                element.sendKeys(sequence);
                break;
            } catch (StaleElementReferenceException e) {
                log.debug("Error occurred when attempting to send keys: {}", e.getMessage());
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT_SECONDS));
            }
        }
    }

    public String getText(WebElement element) {
        wait.waitForPageUpdate();
        wait.waitForJSComplete();
        log.info("Retrieving text");
        return element.getText();
    }

    public boolean isElementPresent(WebElement element, String logInfo) {
        wait.waitForPageUpdate();
        wait.waitForJSComplete();
        log.info("Waiting for element to be present: {}", logInfo);
        wait.waitForElementToBeVisible(element);
        return element.isDisplayed();
    }

    public String getTextOfVisibleElements(List<WebElement> elements) {
        wait.waitForPageUpdate();
        wait.waitForJSComplete();
        String s = "";
        for (WebElement element : elements) {
            if (element.isDisplayed()) {
                s = element.getText() + ";";
            }
        }
        return s;
    }

    public WebElement findElementByXPath(String locator) {
        wait.waitForJSComplete();
        try {
            new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            return driver.findElement(By.xpath(locator));
        } catch (NoSuchElementException exception) {
            log.debug("Unable to find element: ", locator);
            throw exception;
        } finally {
            log.debug(driver.getCurrentUrl());
        }
    }

    public String[] splitStringIntoSeparateWords(String string) {
        return string.split("\\s+");
    }
}