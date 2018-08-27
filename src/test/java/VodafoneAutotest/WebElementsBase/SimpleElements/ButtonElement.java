package VodafoneAutotest.WebElementsBase.SimpleElements;

import VodafoneAutotest.Models.Interfaces.ElementContainer;
import VodafoneAutotest.WebElementsBase.ElementBase;
import org.openqa.selenium.WebElement;

public class ButtonElement extends ElementBase {

    public ButtonElement(WebElement webElement, ElementContainer container) {
        super(webElement, container);
    }

    public String getText() {
        return getWrappedElement().getText();
    }
}
