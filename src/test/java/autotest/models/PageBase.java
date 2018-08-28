package autotest.models;

import autotest.core.BrowserDriver;
import autotest.models.annotations.PageLoadingTimeout;
import autotest.models.interfaces.ElementContainer;
import autotest.models.interfaces.PageInterface;
import autotest.utilities.ExtendedFieldDecorator;
import autotest.utilities.Wait;
import autotest.elements.ElementBase;
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
    public BrowserDriver browser;
    protected URI uri;

    protected PageBase(BrowserDriver browser, URI uri) {
        this.browser = browser;
        load(uri);
    }

    public void reinit() {
        load(uri);
    }

    private void load(URI uri) {
        if (uri == null) {
            throw new IllegalArgumentException("URL is not set for page by default and not passed to constructor");
        }
        this.uri = uri;
        Wait.waitForPageLoad(browser, this.getClass());
        PageFactory.initElements(new ExtendedFieldDecorator(this), this);
    }

    @Override
    public PageBase getPage() {
        return this;
    }

    @Override
    public SearchContext getSearchContext() {
        return browser.getWebDriver();
    }

    @Override
    public URI getUri() {
        return uri;
    }

    @Override
    public List<WebElement> findElements(By by) {
        return browser.getWebDriver().findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return browser.getWebDriver().findElement(by);
    }

    public <T extends ElementBase> T getElement(By by, Class<T> clazz) {
        WebElement element = findElement(by);
        return wrapWebElement(this, element, clazz);
    }

    public <T extends ElementBase> List getElements(By by, Class<T> clazz) {
        List<WebElement> elements = findElements(by);
        List<T> listOfCustomElements = new ArrayList<>();
        for (WebElement element : elements) {
            listOfCustomElements.add(wrapWebElement(this, element, clazz));
        }
        return listOfCustomElements;
    }

    private <T extends ElementBase> T wrapWebElement(ElementContainer container, WebElement element, Class<T> elementClass) {
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
