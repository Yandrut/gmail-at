package org.yandrut.gmail_at.decorator;

import org.openqa.selenium.WebElement;
import org.yandrut.gmail_at.element.Element;

public class WrapperFactory {

    public static Element createInstance(Class<Element> clazz, WebElement element) {
        try {
            return clazz.getConstructor(WebElement.class).newInstance(element);
        } catch (Exception e) {
            throw new RuntimeException("WebElement can't be represented as " + clazz);
        }
    }
}