package autotest.elements.complex;

import autotest.models.interfaces.CustomWebElementInterface;
import autotest.models.interfaces.ElementContainer;
import autotest.utilities.ExtendedFieldDecorator;
import autotest.elements.ElementBase;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public abstract class ComplexCustomElementBase extends ElementBase implements ElementContainer {
    public ComplexCustomElementBase(WebElement webElement, ElementContainer container) {
        super(webElement, container);
        initInternals();
    }

    public void initInternals(){
        PageFactory.initElements(new ExtendedFieldDecorator(this), this);
    }

    @Override
    public SearchContext getSearchContext() {
        return getWrappedElement();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return getWrappedElement().findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return getWrappedElement().findElement(by);
    }

    public <T extends ElementBase> T getElement(By by, Class<T> clazz) {
        WebElement element = findElement(by);
        return wrapWebElement(this, element, clazz);
    }

    public <T extends CustomWebElementInterface> List getElements(By by, Class<T> clazz) {
        List<WebElement> elements = findElements(by);
        List<T> listOfCustomElements = new ArrayList<>();
        for (WebElement element : elements) {
            listOfCustomElements.add((T) wrapWebElement(this, element, clazz));
        }
        return listOfCustomElements;
    }

    public <T extends ElementBase> T wrapWebElement(ElementContainer container, WebElement element, Class elementClass) {
        try {
            Class<?> clazz = Class.forName((elementClass.getName()));
            Constructor<?> ctor = clazz.getConstructor(WebElement.class, ElementContainer.class);
            return (T) ctor.newInstance(element, container);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Cannot wrap element of class: " + elementClass.toString(), e);
        }
    }
}
