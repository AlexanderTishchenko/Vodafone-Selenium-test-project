package VodafoneAutotest.WebElementsBase.SimpleElements;

import VodafoneAutotest.Models.Interfaces.ElementContainer;
import VodafoneAutotest.Utilities.Wait;
import VodafoneAutotest.WebElementsBase.ElementBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DropDownElement extends ElementBase {
    public DropDownElement(WebElement webElement, ElementContainer container) {
        super(webElement, container);
    }

    public void select(String phoneNumber) {
        click();
        Wait.waitForAjax(Browser, 2);
        List<ReadOnlyElement> listOfNumbers = getPage().getElements(new By[] {By.xpath(".//*[@class = 'userNumbers']/*")}, ReadOnlyElement.class);
        for (ReadOnlyElement element: listOfNumbers){
            String text = element.getText();
            if (text.equals(phoneNumber)){
                element.click();
                break;
            }
            if (text.replaceAll("\\s","").equals(phoneNumber)){
                element.click();
                break;
            }
        }
    }
}
