package org.yandrut.gmail_at.element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.yandrut.gmail_at.driver.waits.DriverWaiter;

public class Element {
    private static final Logger log = LogManager.getLogger(Element.class);
    private final WebElement element;

    public Element(WebElement element) {
        this.element = element;
    }

    public String getText() {
        DriverWaiter.waitForJSComplete();
        log.info("Retrieving text");
        return element.getText();
    }

    public boolean isDisplayed() {
        return element.isDisplayed();
    }
}
