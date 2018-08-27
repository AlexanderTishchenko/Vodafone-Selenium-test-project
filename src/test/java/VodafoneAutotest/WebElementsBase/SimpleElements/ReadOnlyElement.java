package VodafoneAutotest.WebElementsBase.SimpleElements;

import VodafoneAutotest.Models.Interfaces.ElementContainer;
import VodafoneAutotest.WebElementsBase.ElementBase;
import org.openqa.selenium.WebElement;

public class ReadOnlyElement extends ElementBase {
    public ReadOnlyElement(WebElement wrappedElement, ElementContainer container) {
        super(wrappedElement, container);
    }

    public String getText() {
        return getWrappedElement().getText();
    }
}
