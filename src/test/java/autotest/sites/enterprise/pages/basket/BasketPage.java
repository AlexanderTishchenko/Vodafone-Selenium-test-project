package autotest.sites.enterprise.pages.basket;

import autotest.core.BrowserDriver;
import autotest.sites.enterprise.pages.EnterpriseSitePageBase;
import autotest.models.annotations.PageLoadingTimeout;
import autotest.models.annotations.PageUri;
import autotest.utilities.PageOpening;
import autotest.elements.simple.ReadOnlyElement;
import org.openqa.selenium.support.FindBy;

import java.net.URI;

@PageUri(uri = "https://www.vodafone.cz/muj/en/basket")
@PageLoadingTimeout(seconds = 60)
public class BasketPage extends EnterpriseSitePageBase {
    public BasketPage(BrowserDriver driver, URI uri) {
        super(driver, uri);
    }

    public String getSuccessMessageText() {
        return _successMessageElement.getText();
    }

    public BasketThankYouPage confirmChanges() {
        return PageOpening.open(browser, _confirmChangesButton, BasketThankYouPage.class, false);
    }

    @FindBy(css = "span.msgContent")
    private ReadOnlyElement _successMessageElement;

    @FindBy(id = "betest_basket_order_continue")
    private ReadOnlyElement _confirmChangesButton;
}
