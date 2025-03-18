package org.yandrut.gmail_at.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.yandrut.gmail_at.drivers.DriverProvider;
import org.yandrut.gmail_at.drivers.DriverWaiter;

public abstract class AbstractPage {

    private static final Logger log = LogManager.getLogger(AbstractPage.class);

    public AbstractPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
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
        DriverWaiter.waitForPageUpdate();
    }

    public void sendKeys(WebElement element, String sequence) {
        DriverWaiter.waitForPageUpdate();
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
        DriverWaiter.waitForPageUpdate();
        log.info("Retrieving text");
        return element.getText();
    }

    public boolean isElementPresent(WebElement element, String logInfo) {
        DriverWaiter.waitForPageUpdate();
        log.info("Waiting for element to be present: {}", logInfo);
        DriverWaiter.waitForElementToBeVisible(element);
        return element.isDisplayed();
    }

    public WebElement findElementByXpath(String xpath) {
        DriverWaiter.waitForPageUpdate();
        return DriverProvider.getDriver().findElement(By.xpath(xpath));
    }

    public String[] splitStringIntoSeparateWords(String string) {
        return string.split("\\s+");
    }
}