package VodafoneAutotest.WebElementsBase.ComplexElements;

import VodafoneAutotest.Models.Interfaces.CustomWebElementInterface;
import VodafoneAutotest.Models.Interfaces.ElementContainer;
import VodafoneAutotest.Utilities.ExtendedFieldDecorator;
import VodafoneAutotest.WebElementsBase.ElementBase;
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

    public <T extends ElementBase> T getElement(By[] bys, Class<T> clazz) {
        WebElement element = findElement(bys[0]);
        T wrappedElement = wrapWebElement(this, element, clazz);
        return wrappedElement;
    }

    public <T extends CustomWebElementInterface> List getElements(By[] bys, Class<T> clazz) {
        List<WebElement> elements = findElements(bys[0]);
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new Error("cannot wrap element");
    }
}
