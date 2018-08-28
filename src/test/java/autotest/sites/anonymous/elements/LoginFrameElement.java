package autotest.sites.anonymous.elements;

import autotest.sites.enterprise.pages.MainLoggedInPage;
import autotest.models.interfaces.ElementContainer;
import autotest.models.users.UserCredentials;
import autotest.utilities.PageOpening;
import autotest.elements.complex.ComplexCustomElementBase;
import autotest.elements.simple.ButtonElement;
import autotest.elements.simple.TextFieldElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginFrameElement extends ComplexCustomElementBase {
    public LoginFrameElement(WebElement webElement, ElementContainer container) {
        super(webElement, container);
    }

    public static By getLocator() {
        return By.className("-login-box");
    }

    public MainLoggedInPage logIn(UserCredentials credentials) {
        _phoneNumberRow.fill(credentials.getPhoneNumber());
        _passwordRow.fill(credentials.getPassword());
        return PageOpening.open(browser, _continueButton, MainLoggedInPage.class, false);
    }

    @FindBy(css = "[value='Continue']")
    private ButtonElement _continueButton;

    @FindBy(id = "phnr")
    private TextFieldElement _phoneNumberRow;

    @FindBy(id = "pwd")
    private TextFieldElement _passwordRow;
}
