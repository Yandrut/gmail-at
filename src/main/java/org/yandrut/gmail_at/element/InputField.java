package org.yandrut.gmail_at.element;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.yandrut.gmail_at.driver.waits.DriverWaiter;

public class InputField extends Element {
    private static final Logger log = LogManager.getLogger(InputField.class);
    private final WebElement element;

    public InputField(WebElement element) {
        super(element);
        this.element = element;
    }

    public void sendKeys(String sequence) {
        for (int i = 0; i < 10; i++) {
            try {
                DriverWaiter.waitForJSComplete();
                DriverWaiter.waitForElementToBeInteractable(element);
                log.info("Sending key sequence: {}", sequence);
                element.sendKeys(sequence);
                break;
            } catch (WebDriverException e) {
                log.debug("Error occurred when attempting to send keys: {}", e.getMessage());
            }
        }
    }

    public String getText() {
        return super.getText();
    }
}
