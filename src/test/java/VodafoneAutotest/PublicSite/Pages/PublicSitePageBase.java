package VodafoneAutotest.PublicSite.Pages;

import VodafoneAutotest.Core.BrowserDriver;
import VodafoneAutotest.EnterpriseSite.Pages.MainLoggedInPage;
import VodafoneAutotest.Models.PageBase;
import VodafoneAutotest.Models.Users.UserCredentials;
import VodafoneAutotest.PublicSite.WebElements.LoginFrameElement;
import VodafoneAutotest.WebElementsBase.SimpleElements.ReadOnlyElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.net.URI;

public abstract class PublicSitePageBase extends PageBase {

    protected PublicSitePageBase(BrowserDriver driver, URI uri){
        super(driver, uri);
    }

    protected LoginFrameElement openLoginFrame(){
        _serviceMenuItem.click();
        return getElement(new By[] {LoginFrameElement.getLocator()}, LoginFrameElement.class);
    }

    public MainLoggedInPage logIn(UserCredentials credentials) {
        return openLoginFrame().logIn(credentials);
    }

    @FindBy(id = "vf-service-menu-item-wsc")
    ReadOnlyElement _serviceMenuItem;
}
