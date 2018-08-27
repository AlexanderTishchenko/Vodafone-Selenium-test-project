package VodafoneAutotest.Models;

import VodafoneAutotest.Core.BrowserDriver;
import VodafoneAutotest.Logger.CustomLog;
import VodafoneAutotest.Models.Annotations.PageLoadingTimeout;
import VodafoneAutotest.Models.Interfaces.ElementContainer;
import VodafoneAutotest.Models.Interfaces.PageInterface;
import VodafoneAutotest.Utilities.ExtendedFieldDecorator;
import VodafoneAutotest.Utilities.Wait;
import VodafoneAutotest.WebElementsBase.ElementBase;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public abstract class PageBase implements PageInterface, ElementContainer {

    protected PageBase(BrowserDriver driver, URI uri) {
        Browser = driver;
        load(uri);
    }

    public BrowserDriver Browser;
    protected URI Uri;

    public void reinit() {
        load(Uri);
    }

    private void load(URI uri) {
        if (uri == null) {
            throw new IllegalArgumentException("URL is not set for page by default and not passed to constructor");
        }
        Uri = uri;
        Wait.waitForPageLoad(Browser, this.getClass());
        PageFactory.initElements(new ExtendedFieldDecorator(this), this);
    }

    @Override
    public PageBase getPage() {
        return this;
    }

    @Override
    public SearchContext getSearchContext() {
        return Browser.getWebDriver();
    }

    @Override
    public URI getUri() {
        return Uri;
    }

    @Override
    public List<WebElement> findElements(By by) {
        return Browser.getWebDriver().findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return Browser.getWebDriver().findElement(by);
    }

    public <T extends ElementBase> T getElement(By[] bys, Class<T> clazz) {
        WebElement element = findElement(bys[0]);
        return wrapWebElement(this, element, clazz);
    }

    public <T extends ElementBase> List getElements(By[] bys, Class<T> clazz) {
        List<WebElement> elements = findElements(bys[0]);
        List<T> listOfCustomElements = new ArrayList<>();

        for (WebElement element : elements) {
            listOfCustomElements.add(wrapWebElement(this, element, clazz));
        }

        return listOfCustomElements;
    }

    private  <T extends ElementBase> T wrapWebElement(ElementContainer container, WebElement element, Class<T> elementClass) {
        try {
            Constructor<?> ctor = elementClass.getConstructor(WebElement.class, ElementContainer.class);
            return (T) ctor.newInstance(element, container);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Cannot wrap element of class: " + elementClass.toString(), e);
        }
    }

    public static int getPageLoadingTimeout(Class clazz) {
        PageLoadingTimeout annotation = ((PageLoadingTimeout) clazz.getAnnotation(PageLoadingTimeout.class));
        if (annotation == null) {
            return 15;
        }
        return annotation.seconds();
    }
}
