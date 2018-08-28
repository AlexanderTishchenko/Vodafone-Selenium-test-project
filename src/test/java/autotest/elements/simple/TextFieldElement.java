package autotest.elements.simple;

import autotest.models.interfaces.ElementContainer;
import autotest.elements.ElementBase;
import org.openqa.selenium.WebElement;

public class TextFieldElement extends ElementBase {
    public TextFieldElement(WebElement wrappedElement, ElementContainer container) {
        super(wrappedElement, container);
    }

    public void fill(String text) {
        getWrappedElement().sendKeys(text);
    }
}
