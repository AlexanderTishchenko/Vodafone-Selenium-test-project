package autotest.sites.anonymous.pages;

import autotest.core.BrowserDriver;
import autotest.models.annotations.PageLoadingTimeout;
import autotest.models.annotations.PageUri;
import autotest.elements.simple.ReadOnlyElement;
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
