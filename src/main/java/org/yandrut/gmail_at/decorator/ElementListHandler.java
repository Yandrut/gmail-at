package org.yandrut.gmail_at.decorator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.yandrut.gmail_at.element.Element;

public class ElementListHandler implements InvocationHandler {
    private final ElementLocator locator;
    private final Class<Element> clazz;

    public ElementListHandler(ElementLocator locator, Class<Element> clazz) {
        this.locator = locator;
        this.clazz = clazz;
    }

    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
        List<WebElement> elements = locator.findElements();
        List<Element> customs = new ArrayList<>();

        for (WebElement element : elements) {
            customs.add(WrapperFactory.createInstance(clazz, element));
        }
        try {
            return method.invoke(customs, objects);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}
