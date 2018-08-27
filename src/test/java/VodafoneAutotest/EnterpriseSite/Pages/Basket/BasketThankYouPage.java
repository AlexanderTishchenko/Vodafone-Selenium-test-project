package VodafoneAutotest.EnterpriseSite.Pages.Basket;

import VodafoneAutotest.Core.BrowserDriver;
import VodafoneAutotest.EnterpriseSite.Pages.EnterpriseSitePageBase;
import VodafoneAutotest.Models.Annotations.PageLoadingTimeout;
import VodafoneAutotest.Models.Annotations.PageUri;
import VodafoneAutotest.WebElementsBase.SimpleElements.ReadOnlyElement;
import org.openqa.selenium.support.FindBy;

import java.net.URI;

@PageUri(uri = "https://www.vodafone.cz/muj/en/basket/thank-you")
@PageLoadingTimeout(seconds = 30)
public class BasketThankYouPage extends EnterpriseSitePageBase {
    public BasketThankYouPage(BrowserDriver driver, URI uri) {
        super(driver, uri);
    }

    public String getHeaderText(){
        return _headerElement.getText();
    }

    public String getSuccessMessageText(){
        return _successMessageElement.getText();
    }


    @FindBy(css = "div.msgContent")
    private ReadOnlyElement _successMessageElement;

    @FindBy(className = "hdng")
    private ReadOnlyElement _headerElement;
}
