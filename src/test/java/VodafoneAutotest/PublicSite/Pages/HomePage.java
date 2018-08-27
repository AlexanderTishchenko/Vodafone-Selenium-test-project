package VodafoneAutotest.PublicSite.Pages;

import VodafoneAutotest.Core.BrowserDriver;
import VodafoneAutotest.Models.Annotations.PageLoadingTimeout;
import VodafoneAutotest.Models.Annotations.PageUri;
import VodafoneAutotest.WebElementsBase.SimpleElements.ReadOnlyElement;
import org.openqa.selenium.support.FindBy;

import java.net.URI;

@PageUri(uri = "https://www.vodafone.cz/en/")
@PageLoadingTimeout(seconds = 20)
public class HomePage extends PublicSitePageBase {

    public HomePage(BrowserDriver driver, URI uri) {
        super(driver, uri);
    }

    public String getBannerText() {
        return _bannerTextContainer.getText();
    }

    @FindBy(className = "pageContainerInner")
    private ReadOnlyElement _bannerTextContainer;
}
