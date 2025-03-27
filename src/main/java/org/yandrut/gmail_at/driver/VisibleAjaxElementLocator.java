package org.yandrut.gmail_at.driver;

import java.lang.reflect.Field;
import java.util.Objects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AjaxElementLocator;

public class VisibleAjaxElementLocator extends AjaxElementLocator {

    public VisibleAjaxElementLocator(WebDriver driver, Field field, int timeoutInSeconds) {
        super(driver, field, timeoutInSeconds);
    }

    @Override
    protected boolean isElementUsable(WebElement element) {
        if (Objects.isNull(element)) {
            return false;
        }
        return element.isDisplayed() && element.isEnabled();
    }
}
