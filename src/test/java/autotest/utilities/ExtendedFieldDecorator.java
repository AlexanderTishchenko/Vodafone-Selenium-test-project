package autotest.utilities;

import autotest.models.interfaces.CustomWebElementInterface;
import autotest.models.interfaces.ElementContainer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.*;
import java.util.List;

public class ExtendedFieldDecorator extends DefaultFieldDecorator {
    private ElementContainer elementContainer;

    public ExtendedFieldDecorator(ElementContainer container) {
        super(new DefaultElementLocatorFactory(container.getSearchContext()));
        elementContainer = container;
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Class<CustomWebElementInterface> decoratableClass = decoratableClass(field);
        if (decoratableClass != null) {
            ElementLocator locator = factory.createLocator(field);
            if (locator == null) {
                return null;
            }


            if (List.class.isAssignableFrom(field.getType())) {
                return createList(loader, locator, decoratableClass);
            }

            return createElement(loader, locator, decoratableClass);
        }
        return super.decorate(loader, field);
    }

    private Class<CustomWebElementInterface> decoratableClass(Field field) {

        Class<?> clazz = field.getType();

        if (List.class.isAssignableFrom(clazz)) {

            if (field.getAnnotation(FindBy.class) == null &&
                    field.getAnnotation(FindBys.class) == null) {
                return null;
            }

            Type genericType = field.getGenericType();
            if (!(genericType instanceof ParameterizedType)) {
                return null;
            }
            clazz = (Class<?>) ((ParameterizedType) genericType).
                    getActualTypeArguments()[0];
        }

        if (CustomWebElementInterface.class.isAssignableFrom(clazz)) {
            return (Class<CustomWebElementInterface>) clazz;
        } else {
            return null;
        }
    }

    private CustomWebElementInterface createElement(ClassLoader loader,
                                                    ElementLocator locator, Class<CustomWebElementInterface> clazz) {
        WebElement proxy = proxyForLocator(loader, locator);
        return WrapperFactory.createInstance(clazz, proxy, elementContainer);
    }

    private List<CustomWebElementInterface> createList(ClassLoader loader, ElementLocator locator, Class<CustomWebElementInterface> clazz) {

        InvocationHandler handler = new LocatingCustomElementListHandler(locator, clazz, elementContainer);
        return (List<CustomWebElementInterface>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
    }
}
