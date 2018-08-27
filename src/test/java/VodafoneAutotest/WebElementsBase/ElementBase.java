package VodafoneAutotest.WebElementsBase;

import VodafoneAutotest.Core.BrowserDriver;
import VodafoneAutotest.Models.Interfaces.CustomWebElementInterface;
import VodafoneAutotest.Models.Interfaces.ElementContainer;
import VodafoneAutotest.Models.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public abstract class ElementBase implements CustomWebElementInterface {
    private By[] locator;
    private ElementContainer elementContainer;
    private WebElement wrappedElement;

    public BrowserDriver Browser;

    public ElementBase(WebElement wrappedElement, ElementContainer container) {
        this.wrappedElement = wrappedElement;
        elementContainer = container;
        Browser = getPage().Browser;
    }

    public String GetClassListAsString() {
        return wrappedElement.getAttribute("class");
    }

    @Override
    public ElementContainer getElementContainer() {
        return elementContainer;
    }

    public WebElement getWrappedElement() {
        return wrappedElement;
    }

    public PageBase getPage() {
        return elementContainer.getPage();
    }

    public void click() {
        wrappedElement.click();
    }

    public void hover() {
        Actions action = new Actions(Browser.getWebDriver());
        action.moveToElement(wrappedElement).build().perform();
    }
}
