package org.yandrut.gmail_at.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.yandrut.gmail_at.decorator.ElementDecorator;
import org.yandrut.gmail_at.driver.VisibleAjaxElementLocatorFactory;

public abstract class AbstractPage {

    public AbstractPage(WebDriver driver) {
        var locatorFactory = new VisibleAjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(new ElementDecorator(locatorFactory), this);
    }
}