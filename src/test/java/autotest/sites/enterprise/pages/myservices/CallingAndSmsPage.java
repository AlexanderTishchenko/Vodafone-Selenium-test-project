package autotest.sites.enterprise.pages.myservices;

import autotest.core.BrowserDriver;
import autotest.sites.enterprise.pages.basket.BasketPage;
import autotest.sites.enterprise.pages.EnterpriseSitePageBase;
import autotest.sites.enterprise.elements.service.MainServiceBox;
import autotest.sites.enterprise.elements.service.ServiceContentBox;
import autotest.models.annotations.PageUri;
import autotest.models.interfaces.FooFunctional;
import autotest.utilities.PageOpening;
import autotest.elements.complex.RowWithSubrow;
import autotest.elements.simple.DropDownElement;
import autotest.elements.simple.ReadOnlyElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;

import java.net.URI;

@PageUri(uri = "https://www.vodafone.cz/muj/en/service-settings/call-and-sms")
public class CallingAndSmsPage extends EnterpriseSitePageBase {
    private RowWithSubrow<MainServiceBox, ServiceContentBox> missedCallsAlertFrame;

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

    public String getMissedCallsAlertServiceStateText() {
        return _missedCallsAlertBox.getState();
    }

    public BasketPage toggleMissedCallsAlert() {
        Boolean serviceState = getMissedCallsAlertFrame().row.isActive();
        FooFunctional func = () -> {
            getMissedCallsAlertFrame().subrow.setState(!serviceState);
        };
        return PageOpening.open(browser, func, BasketPage.class, false);
    }

    private RowWithSubrow<MainServiceBox, ServiceContentBox> getMissedCallsAlertFrame() {
        if (missedCallsAlertFrame == null) {
            _missedCallsAlertBox.expand(true);
            ServiceContentBox content = getElement(By.id("missed_call_alert"), ServiceContentBox.class);
            missedCallsAlertFrame = new RowWithSubrow<>(_missedCallsAlertBox, content);
        }
        return missedCallsAlertFrame;
    }

    public void selectPhoneNumber(String phoneNumber) {
        _phoneNumberDropDown.select(phoneNumber);
    }

    @FindBy(className = "switcherToggler")
    private DropDownElement _phoneNumberDropDown;

    @FindBy(css = "[data-connect-id='missed_call_alert']")
    private MainServiceBox _missedCallsAlertBox;

    @FindBy(css = ".dashboardWarning .msgContent")
    private ReadOnlyElement _warningElement;
}
