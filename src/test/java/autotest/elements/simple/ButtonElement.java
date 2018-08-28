package autotest.elements.simple;

import autotest.models.interfaces.ElementContainer;
import autotest.elements.ElementBase;
import org.openqa.selenium.WebElement;

public class ButtonElement extends ElementBase {

    public ButtonElement(WebElement webElement, ElementContainer container) {
        super(webElement, container);
    }

    public String getText() {
        return getWrappedElement().getText();
    }
}
