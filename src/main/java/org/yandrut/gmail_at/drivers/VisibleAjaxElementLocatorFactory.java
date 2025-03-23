package org.yandrut.gmail_at.drivers;

import java.lang.reflect.Field;
import java.util.Objects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AjaxElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

public class VisibleAjaxElementLocatorFactory implements ElementLocatorFactory {

    private final WebDriver driver;
    private final int timeoutInSeconds;

    public VisibleAjaxElementLocatorFactory(WebDriver driver, int timeoutInSeconds) {
        this.driver = driver;
        this.timeoutInSeconds = timeoutInSeconds;
    }

    @Override
    public ElementLocator createLocator(Field field) {
        return new VisibleAjaxElementLocator(driver, field, timeoutInSeconds);
    }

    private static class VisibleAjaxElementLocator extends AjaxElementLocator {

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
}
