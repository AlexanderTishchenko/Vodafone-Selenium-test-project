package VodafoneAutotest.PublicSite.WebElements;

import VodafoneAutotest.EnterpriseSite.Pages.MainLoggedInPage;
import VodafoneAutotest.Models.Interfaces.ElementContainer;
import VodafoneAutotest.Models.Users.UserCredentials;
import VodafoneAutotest.Utilities.PageOpening;
import VodafoneAutotest.WebElementsBase.ComplexElements.ComplexCustomElementBase;
import VodafoneAutotest.WebElementsBase.SimpleElements.ButtonElement;
import VodafoneAutotest.WebElementsBase.SimpleElements.TextFieldElement;
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
        return PageOpening.open(Browser, _continueButton, MainLoggedInPage.class, false);
    }

    @FindBy(css = "*[value='Continue']")
    ButtonElement _continueButton;

    @FindBy(id = "phnr")
    TextFieldElement _phoneNumberRow;

    @FindBy(id = "pwd")
    TextFieldElement _passwordRow;
}
