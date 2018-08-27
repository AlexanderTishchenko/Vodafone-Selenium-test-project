package VodafoneAutotest.EnterpriseSite.WebElements.ServiceElement;

import VodafoneAutotest.Models.Interfaces.ElementContainer;
import VodafoneAutotest.WebElementsBase.ComplexElements.ComplexCustomElementBase;
import VodafoneAutotest.WebElementsBase.SimpleElements.ButtonElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ServiceContentBox extends ComplexCustomElementBase {
    public ServiceContentBox(WebElement webElement, ElementContainer container) {
        super(webElement, container);
    }

    public void setState(Boolean state) {
        if (state) {
            _activeButton.click();
        } else {
            _turnOffButton.click();
        }
        Save();
    }

    public void Save() {
        _saveButton.click();
    }

    @FindBy(xpath = ".//span[contains(text(),'Turn off') or contains(text(),'Inactive')]")
    ButtonElement _turnOffButton;

    @FindBy(xpath = ".//span[contains(text(),'Turn on') or contains(text(),'Active')]")
    ButtonElement _activeButton;

    @FindBy(css = "[type='submit']")
    ButtonElement _saveButton;

}
