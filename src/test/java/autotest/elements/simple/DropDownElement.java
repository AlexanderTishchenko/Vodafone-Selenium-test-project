package autotest.elements.simple;

import autotest.models.interfaces.ElementContainer;
import autotest.utilities.Wait;
import autotest.elements.ElementBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DropDownElement extends ElementBase {
    public DropDownElement(WebElement webElement, ElementContainer container) {
        super(webElement, container);
    }

    public void select(String phoneNumber) {
        click();
        Wait.waitForAjax(browser, 2);
        List<ReadOnlyElement> listOfNumbers = getPage().getElements(By.xpath(".//*[@class = 'userNumbers']/*"), ReadOnlyElement.class);
        for (ReadOnlyElement element : listOfNumbers) {
            String text = element.getText();
            if (text.equals(phoneNumber)) {
                element.click();
                break;
            }
            if (text.replaceAll("\\s", "").equals(phoneNumber)) {
                element.click();
                break;
            }
        }
    }
}
