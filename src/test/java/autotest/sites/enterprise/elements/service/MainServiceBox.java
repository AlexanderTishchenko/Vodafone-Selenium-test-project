package autotest.sites.enterprise.elements.service;

import autotest.models.interfaces.ElementContainer;
import autotest.elements.complex.ComplexCustomElementBase;
import autotest.elements.simple.ButtonElement;
import autotest.elements.simple.ReadOnlyElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainServiceBox extends ComplexCustomElementBase {
    public MainServiceBox(WebElement webElement, ElementContainer container) {
        super(webElement, container);
    }

    public void expand(Boolean isExpand) {
        if (isExpanded() != isExpand) {
            _showHideButton.click();
        }
    }

    public boolean isExpanded() {
        return _showHideButton.getClassListAsString().contains("changeButtonOpened");
    }

    public String getState() {
        return _serviceStatus.getText();
    }

    public boolean isActive() {
        return _serviceStatus.getText().equals("ACTIVE");
    }

    @FindBy(css = "button.changeButton")
    private ButtonElement _showHideButton;

    @FindBy(css = "i.tag")
    private ReadOnlyElement _serviceStatus;
}
