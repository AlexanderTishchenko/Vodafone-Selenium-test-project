package VodafoneAutotest.PublicSite.Pages;

import VodafoneAutotest.Core.BrowserDriver;
import VodafoneAutotest.Models.Annotations.PageUri;
import VodafoneAutotest.WebElementsBase.SimpleElements.ReadOnlyElement;
import org.openqa.selenium.support.FindBy;

import java.net.URI;

@PageUri(uri = "https://www.vodafone.cz/muj/en/logout-success")
public class LogoutSuccessPage extends PublicSitePageBase {
    public LogoutSuccessPage(BrowserDriver driver, URI uri) {
        super(driver, uri);
    }

    public String getHeaderText(){
        return _headerElement.getText();
    }

    public String getMessageText(){
        return _messageElement.getText();
    }

    @FindBy(css = ".logoutNotification p:nth-child(3)")
    private ReadOnlyElement _messageElement;

    @FindBy(css = ".logoutNotification strong")
    private ReadOnlyElement _headerElement;
}
