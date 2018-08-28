package autotest.sites.enterprise.pages.basket;

import autotest.core.BrowserDriver;
import autotest.sites.enterprise.pages.EnterpriseSitePageBase;
import autotest.models.annotations.PageLoadingTimeout;
import autotest.models.annotations.PageUri;
import autotest.elements.simple.ReadOnlyElement;
import org.openqa.selenium.support.FindBy;

import java.net.URI;

@PageUri(uri = "https://www.vodafone.cz/muj/en/basket/thank-you")
@PageLoadingTimeout(seconds = 30)
public class BasketThankYouPage extends EnterpriseSitePageBase {
    public BasketThankYouPage(BrowserDriver driver, URI uri) {
        super(driver, uri);
    }

    public String getHeaderText() {
        return _headerElement.getText();
    }

    public String getSuccessMessageText() {
        return _successMessageElement.getText();
    }

    @FindBy(css = "div.msgContent")
    private ReadOnlyElement _successMessageElement;

    @FindBy(className = "hdng")
    private ReadOnlyElement _headerElement;
}
