
package org.yandrut.gmail_at.decorator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.yandrut.gmail_at.element.Element;

@SuppressWarnings("unchecked")
public class ElementDecorator extends DefaultFieldDecorator {

    public ElementDecorator(ElementLocatorFactory factory) {
        super(factory);
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class decoratableClass = decoratableClass(field);

        if (Objects.nonNull(decoratableClass)) {
            ElementLocator locator = factory.createLocator(field);

            if (Objects.isNull(locator)) {
                return null;
            }

            if (List.class.isAssignableFrom(field.getType())) {
                return createList(loader, locator, decoratableClass);
            }
            return createElement(loader, locator, decoratableClass);
        }
        return super.decorate(loader, field);
    }

    private Class decoratableClass(Field field) {
        Class clazz = field.getType();
        if (List.class.isAssignableFrom(clazz)) {
            if (Objects.isNull(field.getAnnotation(FindBy.class)) &&
                Objects.isNull(field.getAnnotation(FindBys.class))) {
                return null;
            }
            Type genericType = field.getGenericType();
            if (!(genericType instanceof ParameterizedType)) {
                return null;
            }
            clazz = (Class) ((ParameterizedType) genericType).getActualTypeArguments()[0];
        }
        if (Element.class.isAssignableFrom(clazz)) {
            return clazz;
        } else {
            return null;
        }
    }

    protected Element createElement(ClassLoader loader, ElementLocator locator, Class<Element> clazz) {
        WebElement proxy = proxyForLocator(loader, locator);
        return WrapperFactory.createInstance(clazz, proxy);
    }

    protected List createList(ClassLoader loader, ElementLocator locator, Class<Element> clazz) {
        InvocationHandler handler = new ElementListHandler(locator, clazz);
        return (List) Proxy.newProxyInstance(loader, new Class[] {List.class}, handler);
    }
}