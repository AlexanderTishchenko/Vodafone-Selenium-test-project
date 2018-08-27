package VodafoneAutotest.EnterpriseSite.WebElements.ServiceElement;

import VodafoneAutotest.Models.Interfaces.ElementContainer;
import VodafoneAutotest.WebElementsBase.ComplexElements.ComplexCustomElementBase;
import VodafoneAutotest.WebElementsBase.SimpleElements.ButtonElement;
import VodafoneAutotest.WebElementsBase.SimpleElements.ReadOnlyElement;
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
        return _showHideButton.GetClassListAsString().contains("changeButtonOpened");
    }

    public String getState() {
        return _serviceStatus.getText();
    }

    public boolean isActive() {
        String serviceState = _serviceStatus.getText();
        if (serviceState.equals("ACTIVE")) {
            return true;
        }
        if (serviceState.equals("INACTIVE")) {
            return false;
        }
        throw new Error("Unexpected service state: " + serviceState);
    }

    @FindBy(css = "button.changeButton")
    ButtonElement _showHideButton;

    @FindBy(css = "i.tag")
    ReadOnlyElement _serviceStatus;
}
