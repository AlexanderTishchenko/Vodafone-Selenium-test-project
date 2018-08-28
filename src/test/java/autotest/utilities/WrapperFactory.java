package autotest.utilities;

import autotest.models.interfaces.CustomWebElementInterface;
import autotest.models.interfaces.ElementContainer;
import org.openqa.selenium.WebElement;

class WrapperFactory {
    static CustomWebElementInterface createInstance(Class<CustomWebElementInterface> clazz, WebElement element, ElementContainer elementContainer) {
        try {
            return clazz.getConstructor(WebElement.class, ElementContainer.class).newInstance(element, elementContainer);
        } catch (Exception e) {
            throw new AssertionError("WebElement can't be represented as " + clazz.getTypeName());
        }
    }
}

