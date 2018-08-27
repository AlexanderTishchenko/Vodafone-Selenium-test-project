package VodafoneAutotest.EnterpriseSite.Pages;

import VodafoneAutotest.Core.BrowserDriver;
import VodafoneAutotest.Models.Interfaces.FooFunctional;
import VodafoneAutotest.Models.PageBase;
import VodafoneAutotest.PublicSite.Pages.LogoutSuccessPage;
import VodafoneAutotest.Utilities.PageOpening;
import VodafoneAutotest.WebElementsBase.SimpleElements.ButtonElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.URI;

public abstract class EnterpriseSitePageBase extends PageBase {

    protected EnterpriseSitePageBase(BrowserDriver driver, URI uri) {
        super(driver, uri);
    }

    public HomeLoggedInPage openHomePage() {
        return PageOpening.open(Browser, _homePageMenuButton, HomeLoggedInPage.class, false);
    }

    public String getAccountInformation(){
        return _accountInformationButtom.getText();
    }

    public LogoutSuccessPage logOut() {
        _accountInformationButtom.hover();
        WebElement submenu = findElement(By.xpath(".//a[contains(text(),'Log out')]"));
        FooFunctional func = () -> {
            String js = "arguments[0].focus(); arguments[1].focus(); arguments[1].click();";
            Object[] args = {_accountInformationButtom.getWrappedElement(), submenu};
            BrowserDriver.executeScript(js, args);
        };
        return PageOpening.open(Browser, func, LogoutSuccessPage.class, false);
    }

    @FindBy(css = "[data-s-object-id='menu:Home']")
    private ButtonElement _homePageMenuButton;

    @FindBy(css = "[data-s-object-id='top menu:prihlaseni']")
    private ButtonElement _accountInformationButtom;
}
