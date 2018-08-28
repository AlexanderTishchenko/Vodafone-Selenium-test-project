package autotest.sites.enterprise.pages;

import autotest.core.BrowserDriver;
import autotest.models.annotations.PageUri;

import java.net.URI;

@PageUri(uri = "https://www.vodafone.cz/en/")
public class MainLoggedInPage extends EnterpriseSitePageBase {

    public MainLoggedInPage(BrowserDriver driver, URI uri) {
        super(driver, uri);
    }
}
