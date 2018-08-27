package VodafoneAutotest.Utilities;

import VodafoneAutotest.Models.Interfaces.CustomWebElementInterface;
import VodafoneAutotest.Models.Interfaces.ElementContainer;
import org.openqa.selenium.WebElement;

public class WrapperFactory {

    /**
     * Создает экземпляр класса,
     * реализующий IElement интерфейс,
     * вызывая конструктор с аргументом WebElement
     */
    public static CustomWebElementInterface createInstance(Class<CustomWebElementInterface> clazz, WebElement element, ElementContainer elementContainer) {
        try {
            return clazz.getConstructor(WebElement.class, ElementContainer.class).newInstance(element, elementContainer);
        } catch (Exception e) {
            throw new AssertionError("WebElement can't be represented as " + clazz);
        }
    }
}

