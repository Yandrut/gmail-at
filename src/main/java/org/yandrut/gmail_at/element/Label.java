package org.yandrut.gmail_at.element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.yandrut.gmail_at.driver.waits.DriverWaiter;

public class Label extends Element {

    private static final Logger log = LogManager.getLogger(Label.class);

    public Label(WebElement element) {
        super(element);
    }

    public static Label findElementByXpath(String xpath) {
        WebElement webElement = null;
        for (int i = 0; i < 10; i++) {
            try {
                log.info("Looking for an element by: {}", xpath);
                webElement = DriverWaiter.waitToBeInteractableAndReturn(By.xpath(xpath));
            } catch (WebDriverException e) {
                log.warn("Error occurred while searching: {}", e.getMessage());
            }
        }
        return new Label(webElement);
    }
}
