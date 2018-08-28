package autotest.utilities;

import autotest.core.BrowserDriver;
import autotest.models.interfaces.Action;
import autotest.models.interfaces.PageInterface;
import autotest.elements.ElementBase;
import org.openqa.selenium.NoSuchElementException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URI;

public class PageOpening {
    public static <T extends PageInterface> T open(BrowserDriver driver, Class<T> pageClass) {
        return open(driver, pageClass, UriBuilder.getUri(pageClass));
    }

    public static <T extends PageInterface> T open(BrowserDriver driver, Class<T> pageClass, URI uri) {
        Action func = () -> {
            try {
                driver.getWebDriver().navigate().to(uri.toURL());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        };
        return (T) open(driver, pageClass, func, false);
    }

    public static <T extends PageInterface> T open(BrowserDriver driver, ElementBase element, Class pageClass, Boolean inOtherTab) {
        return (T) open(driver, pageClass, () -> element.click(), inOtherTab);
    }

    public static <T extends PageInterface> T open(BrowserDriver driver, Action actionToOpen, Class pageClass, Boolean inOtherTab) {
        return (T) open(driver, pageClass, actionToOpen, inOtherTab);
    }

    private static PageInterface open(BrowserDriver driver, Class pageClass, Action actionToOpen, Boolean inOtherTab) {
        actionToOpen.invoke();
        try {
            return createPage(driver, pageClass, inOtherTab);
        } catch (NoSuchElementException e) {
            String textError = "Problem with creation of page " + pageClass.getTypeName();
            throw new NoSuchElementException(textError, e);
        }
    }

    private static PageInterface createPage(BrowserDriver driver, Class pageClass, Boolean inNewTab) {
        try {
            if (inNewTab) {
                driver.switchToNewWindow();
            }
            Wait.waitForPageLoad(driver, pageClass);
            URI pageUrl = driver.getUri();

            Constructor<?> ctor = pageClass.getConstructor(BrowserDriver.class, URI.class);
            return (PageInterface) ctor.newInstance(driver, pageUrl);
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException("Problem with creation of page" + pageClass.getTypeName(), e);
        }
    }
}
