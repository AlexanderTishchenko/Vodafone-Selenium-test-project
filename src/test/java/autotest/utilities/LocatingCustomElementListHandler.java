package autotest.utilities;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import autotest.models.interfaces.CustomWebElementInterface;
import autotest.models.interfaces.ElementContainer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class LocatingCustomElementListHandler implements InvocationHandler {
    private final ElementLocator locator;
    private final Class<CustomWebElementInterface> clazz;
    private ElementContainer elementContainer;

    LocatingCustomElementListHandler(ElementLocator locator, Class<CustomWebElementInterface> clazz, ElementContainer elementContainer) {
        this.locator = locator;
        this.clazz = clazz;
        this.elementContainer = elementContainer;
    }

    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
        List<WebElement> elements = locator.findElements();
        List<CustomWebElementInterface> customs = new ArrayList<>();

        for (WebElement element : elements) {
            customs.add(WrapperFactory.createInstance(clazz, element, elementContainer));
        }
        try {
            return method.invoke(customs, objects);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}
