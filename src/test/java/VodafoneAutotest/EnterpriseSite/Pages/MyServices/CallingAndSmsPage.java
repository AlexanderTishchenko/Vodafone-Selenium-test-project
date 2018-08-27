package VodafoneAutotest.EnterpriseSite.Pages.MyServices;

import VodafoneAutotest.Core.BrowserDriver;
import VodafoneAutotest.EnterpriseSite.Pages.Basket.BasketPage;
import VodafoneAutotest.EnterpriseSite.Pages.EnterpriseSitePageBase;
import VodafoneAutotest.EnterpriseSite.WebElements.ServiceElement.MainServiceBox;
import VodafoneAutotest.EnterpriseSite.WebElements.ServiceElement.ServiceContentBox;
import VodafoneAutotest.Models.Annotations.PageUri;
import VodafoneAutotest.Models.Interfaces.FooFunctional;
import VodafoneAutotest.Utilities.PageOpening;
import VodafoneAutotest.WebElementsBase.ComplexElements.RowWithSubrow;
import VodafoneAutotest.WebElementsBase.SimpleElements.DropDownElement;
import VodafoneAutotest.WebElementsBase.SimpleElements.ReadOnlyElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;

import java.net.URI;

@PageUri(uri = "https://www.vodafone.cz/muj/en/service-settings/call-and-sms")
public class CallingAndSmsPage extends EnterpriseSitePageBase {

    public CallingAndSmsPage(BrowserDriver driver, URI uri) {
        super(driver, uri);
    }

    public String getWarningText() {
        try {
            return _warningElement.getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public String getMissedCallsAlertServiceState(){
        return _missedCallsAlertBox.getState();
    }

    public BasketPage toggleMissedCallsAlert() {
        Boolean serviceState = getMissedCallsAlertFrame().Row.isActive();
        FooFunctional func = () -> {
            getMissedCallsAlertFrame().Subrow.setState(!serviceState);
        };
        return PageOpening.open(Browser, func, BasketPage.class, false);
    }

    private RowWithSubrow<MainServiceBox, ServiceContentBox> MissedCallsAlertFrame;

    private RowWithSubrow<MainServiceBox, ServiceContentBox> getMissedCallsAlertFrame() {
        if (MissedCallsAlertFrame == null) {
            _missedCallsAlertBox.expand(true);
            ServiceContentBox content = getElement(new By[]{By.id("missed_call_alert")}, ServiceContentBox.class);
            MissedCallsAlertFrame = new RowWithSubrow<>(_missedCallsAlertBox, content);
        }
        return MissedCallsAlertFrame;
    }

    public void selectPhoneNumber(String phoneNumber){
        _phoneNumberDropDown.select(phoneNumber);
    }

    @FindBy(className = "switcherToggler")
    DropDownElement _phoneNumberDropDown;

    @FindBy(css = "[data-connect-id='missed_call_alert']")
    MainServiceBox _missedCallsAlertBox;

    @FindBy(css = ".dashboardWarning .msgContent")
    ReadOnlyElement _warningElement;
}
