package autotest.elements.simple;

import autotest.models.interfaces.ElementContainer;
import autotest.elements.ElementBase;
import org.openqa.selenium.WebElement;

public class ReadOnlyElement extends ElementBase {
    public ReadOnlyElement(WebElement wrappedElement, ElementContainer container) {
        super(wrappedElement, container);
    }

    public String getText() {
        return getWrappedElement().getText();
    }
}
