package org.yandrut.gmail_at.element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.yandrut.gmail_at.driver.waits.DriverWaiter;

public class Image extends Element {

    private static final Logger log = LogManager.getLogger(Image.class);
    private final WebElement element;

    public Image(WebElement element) {
        super(element);
        this.element = element;
    }

    public boolean isImagePresent(String logInfo) {
        DriverWaiter.waitForJSComplete();
        log.info("Waiting for element to be present: {}", logInfo);
        DriverWaiter.waitForElementToBeInteractable(element);
        return super.isDisplayed();
    }
}
