package VodafoneAutotest.EnterpriseSite.Pages.Basket;

import VodafoneAutotest.Core.BrowserDriver;
import VodafoneAutotest.EnterpriseSite.Pages.EnterpriseSitePageBase;
import VodafoneAutotest.Models.Annotations.PageLoadingTimeout;
import VodafoneAutotest.Models.Annotations.PageUri;
import VodafoneAutotest.Utilities.PageOpening;
import VodafoneAutotest.WebElementsBase.SimpleElements.ReadOnlyElement;
import org.openqa.selenium.support.FindBy;

import java.net.URI;

@PageUri(uri = "https://www.vodafone.cz/muj/en/basket")
@PageLoadingTimeout(seconds = 60)
public class BasketPage extends EnterpriseSitePageBase {
    public BasketPage(BrowserDriver driver, URI uri) {
        super(driver, uri);
    }

    public String getSuccessMessageText(){
        return _successMessageElement.getText();
    }

    public BasketThankYouPage confirmChanges() {
        return PageOpening.open(Browser, _confirmChangesButton, BasketThankYouPage.class, false);
    }

    @FindBy(css = "span.msgContent")
    ReadOnlyElement _successMessageElement;

    @FindBy(id = "betest_basket_order_continue")
    ReadOnlyElement _confirmChangesButton;
}
