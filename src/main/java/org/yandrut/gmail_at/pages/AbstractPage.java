package org.yandrut.gmail_at.pages;

import static org.yandrut.gmail_at.drivers.DriverWaiter.waitForJSComplete;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.openqa.selenium.support.PageFactory;
import org.yandrut.gmail_at.drivers.DriverWaiter;

@Lazy
@Component
@Slf4j
public abstract class AbstractPage {

    @Autowired
    protected WebDriver driver;

    @PostConstruct
    public void initPageFactory() {
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element, String logInfo) {
        DriverWaiter.waitForElementToBeVisible(element);
        DriverWaiter.waitForElementToBeClickable(element);
        for (int i = 0; i < 10; i++) {
            try {
                log.info("Trying to click on: {} ", logInfo);
                element.click();
                break;
            } catch (WebDriverException e) {
                log.debug("Error occurred when attempting to click: {}", e.getMessage());
            }
        }
    }

    public void clickByJs(WebElement element, String logInfo) {
        DriverWaiter.waitForElementToBeVisible(element);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        log.info("Clicking by JS on: {} ", logInfo);
        js.executeScript("arguments[0].click();", element);
    }


    public void sendKeys(WebElement element, String sequence) {
        DriverWaiter.waitForElementToBeVisible(element);
        log.info("Sending key sequence: {}", sequence);
        for (int i = 0; i < 5; i++) {
            try {
                element.sendKeys(sequence);
                break;
            } catch (WebDriverException e) {
                log.debug("Error occurred when attempting to send keys: {}", e.getMessage());
            }
        }
    }

    public String getText(WebElement element) {
        log.info("Retrieving text");
        return element.getText();
    }

    public boolean isElementPresent(WebElement element, String logInfo) {
        log.info("Waiting for element to be present: {}", logInfo);
        DriverWaiter.waitForElementToBeVisible(element);
        return element.isDisplayed();
    }

    public WebElement findElementByXpath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    public void waitForAttributeToBe(WebElement element, String attribute, String expectedValue) {
        DriverWaiter.waitForAttributeToBe(element, attribute, expectedValue);
    }

    public void refreshPage() {
        waitForJSComplete();
        driver.navigate().refresh();
        waitForJSComplete();
    }

    public String[] splitStringIntoSeparateWords(String string) {
        return string.split("\\s+");
    }
}