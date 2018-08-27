package VodafoneAutotest.WebElementsBase.SimpleElements;

import VodafoneAutotest.Models.Interfaces.ElementContainer;
import VodafoneAutotest.WebElementsBase.ElementBase;
import org.openqa.selenium.WebElement;

public class TextFieldElement extends ElementBase {
    public TextFieldElement(WebElement wrappedElement, ElementContainer container) {
        super(wrappedElement, container);
    }

    public void fill(String text){
        getWrappedElement().sendKeys(text);
    }
}
