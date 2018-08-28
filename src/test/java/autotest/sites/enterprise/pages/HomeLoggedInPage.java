package autotest.sites.enterprise.pages;

import autotest.core.BrowserDriver;
import autotest.models.annotations.PageUri;
import autotest.elements.simple.ReadOnlyElement;
import org.openqa.selenium.support.FindBy;

import java.net.URI;

@PageUri(uri = "https://www.vodafone.cz/muj/en/")
public class HomeLoggedInPage extends EnterpriseSitePageBase {

    public HomeLoggedInPage(BrowserDriver driver, URI uri) {
        super(driver, uri);
    }

    public String getCredit() {
        return _creditForTelephoneNumber.getText();
    }

    @FindBy(id = "actual-balance-total")
    private ReadOnlyElement _creditForTelephoneNumber;
}
