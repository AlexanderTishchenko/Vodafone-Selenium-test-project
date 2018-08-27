package VodafoneAutotest.EnterpriseSite.Pages;

import VodafoneAutotest.Core.BrowserDriver;
import VodafoneAutotest.Models.Annotations.PageUri;

import java.net.URI;

@PageUri(uri = "https://www.vodafone.cz/en/")
public class MainLoggedInPage extends EnterpriseSitePageBase {

    public MainLoggedInPage(BrowserDriver driver, URI uri) {
        super(driver, uri);
    }
}
