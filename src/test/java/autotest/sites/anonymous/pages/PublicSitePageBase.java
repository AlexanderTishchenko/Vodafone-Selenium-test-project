package autotest.sites.anonymous.pages;

import autotest.core.BrowserDriver;
import autotest.elements.simple.ReadOnlyElement;
import autotest.models.PageBase;
import autotest.models.users.UserCredentials;
import autotest.sites.anonymous.elements.LoginFrameElement;
import autotest.sites.enterprise.pages.MainLoggedInPage;
import org.openqa.selenium.support.FindBy;

import java.net.URI;

public abstract class PublicSitePageBase extends PageBase {

    PublicSitePageBase(BrowserDriver driver, URI uri) {
        super(driver, uri);
    }

    private LoginFrameElement openLoginFrame() {
        _serviceMenuItem.click();
        return getElement(LoginFrameElement.getLocator(), LoginFrameElement.class);
    }

    public MainLoggedInPage logIn(UserCredentials credentials) {
        return openLoginFrame().logIn(credentials);
    }

    @FindBy(id = "vf-service-menu-item-wsc")
    private ReadOnlyElement _serviceMenuItem;
}
