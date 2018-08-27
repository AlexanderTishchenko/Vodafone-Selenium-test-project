package VodafoneAutotest.Utilities;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import VodafoneAutotest.Models.Interfaces.CustomWebElementInterface;
import VodafoneAutotest.Models.Interfaces.ElementContainer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class LocatingCustomElementListHandler implements InvocationHandler {
    private final ElementLocator locator;
    private final Class<CustomWebElementInterface> clazz;
    protected ElementContainer elementContainer;

    public LocatingCustomElementListHandler(ElementLocator locator, Class<CustomWebElementInterface> clazz, ElementContainer elementContainer) {
        this.locator = locator;
        this.clazz = clazz;
        this.elementContainer = elementContainer;
    }

    public Object invoke(Object object, Method method,
                         Object[] objects) throws Throwable {
        // Находит список WebElement и обрабатывает каждый его элемент,
        // возвращает новый список с элементами кастомного класса
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
