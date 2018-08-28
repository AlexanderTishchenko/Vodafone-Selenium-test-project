package autotest.elements;

import autotest.core.BrowserDriver;
import autotest.models.interfaces.CustomWebElementInterface;
import autotest.models.interfaces.ElementContainer;
import autotest.models.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public abstract class ElementBase implements CustomWebElementInterface {
    private ElementContainer elementContainer;
    private WebElement wrappedElement;
    public BrowserDriver browser;

    public ElementBase(WebElement wrappedElement, ElementContainer elementContainer) {
        this.wrappedElement = wrappedElement;
        this.elementContainer = elementContainer;
        browser = getPage().browser;
    }

    public String getClassListAsString() {
        return getWrappedElement().getAttribute("class");
    }

    public WebElement getWrappedElement() {
        return wrappedElement;
    }

    public PageBase getPage() {
        return elementContainer.getPage();
    }

    public void click() {
        getWrappedElement().click();
    }

    public void hover() {
        Actions action = new Actions(browser.getWebDriver());
        action.moveToElement(getWrappedElement()).build().perform();
    }
}
