package autotest.sites.enterprise.elements.service;

import autotest.models.interfaces.ElementContainer;
import autotest.elements.complex.ComplexCustomElementBase;
import autotest.elements.simple.ButtonElement;
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
        save();
    }

    public void save() {
        _saveButton.click();
    }

    @FindBy(xpath = ".//span[contains(text(),'Turn off') or contains(text(),'Inactive')]")
    private ButtonElement _turnOffButton;

    @FindBy(xpath = ".//span[contains(text(),'Turn on') or contains(text(),'Active')]")
    private ButtonElement _activeButton;

    @FindBy(css = "[type='submit']")
    private ButtonElement _saveButton;

}
