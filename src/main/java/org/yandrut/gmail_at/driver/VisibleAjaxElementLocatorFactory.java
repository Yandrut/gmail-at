package org.yandrut.gmail_at.driver;

import java.lang.reflect.Field;
import org.openqa.selenium.WebDriver;
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
}