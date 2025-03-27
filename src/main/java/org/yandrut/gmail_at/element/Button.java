package org.yandrut.gmail_at.element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.yandrut.gmail_at.driver.waits.DriverWaiter;

public class Button extends Element {
    private static final Logger log = LogManager.getLogger(Button.class);
    private final WebElement element;

    public Button(WebElement element) {
        super(element);
        this.element = element;
    }

    public static Button findElementByXpath(String xpath) {
        DriverWaiter.waitForJSComplete();
        var webElement = DriverWaiter.waitToBeInteractableAndReturn(By.xpath(xpath));
        return new Button(webElement);
    }

    public void click(String logInfo) {
        for (int i = 0; i < 10; i++) {
            try {
                DriverWaiter.waitForElementToBeInteractable(element);
                DriverWaiter.waitForElementToBeClickable(element);
                log.info("Trying to click on: {} ", logInfo);
                element.click();
                break;
            } catch (WebDriverException e) {
                log.debug("Error occurred when attempting to click: {}", e.getMessage());
            }
        }
        DriverWaiter.waitForJSComplete();
    }

    public String getText() {
        return super.getText();
    }
}
