package VodafoneAutotest.Tests;

import VodafoneAutotest.EnterpriseSite.Pages.Basket.BasketPage;
import VodafoneAutotest.EnterpriseSite.Pages.Basket.BasketThankYouPage;
import VodafoneAutotest.EnterpriseSite.Pages.HomeLoggedInPage;
import VodafoneAutotest.EnterpriseSite.Pages.MainLoggedInPage;
import VodafoneAutotest.EnterpriseSite.Pages.MyServices.CallingAndSmsPage;
import VodafoneAutotest.Logger.CustomLog;
import VodafoneAutotest.Models.Users.User;
import VodafoneAutotest.Models.Users.Users;
import VodafoneAutotest.PublicSite.Pages.HomePage;
import VodafoneAutotest.PublicSite.Pages.LogoutSuccessPage;
import VodafoneAutotest.Utilities.PageOpening;
import VodafoneAutotest.Utilities.UriBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assume.assumeThat;

@RunWith(Parameterized.class)
public class ChangeMissedCallAlertStateTest extends SeleniumTestBase {
    private User user;

    public ChangeMissedCallAlertStateTest(User user) {
        this.user = user;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Users.TestUser}
        });
    }

    @Test
    public void test() {
        CustomLog.step(1, "Open Vodafone webpage: " + UriBuilder.getUri(HomePage.class));
        HomePage homePage = PageOpening.open(getBrowser(), HomePage.class);
        Assert.assertThat("Text of banner is not equal with expected", homePage.getBannerText(), is(containsString("The future is exciting.\nReady?")));

        CustomLog.step(2, "Login to My Vodafone section");
        MainLoggedInPage mainLoggedInPage = homePage.logIn(user.getCredentials());
        String accountInformation = mainLoggedInPage.getAccountInformation();
        Assert.assertThat("Account information doesn't contain user name", accountInformation, is(containsString(user.getName())));
        HomeLoggedInPage loggedInHomePage = mainLoggedInPage.openHomePage();

        CustomLog.step(3, "Log Credit for telephone number");
        String credit = loggedInHomePage.getCredit();
        CustomLog.traceInformation("Credit for telephone number: " + credit);
        Assert.assertThat("Credit for telephone number doesn't match regex pattern", Pattern.compile("\\d+,\\d{2} CZK").matcher(credit).matches(), is(true));

        CustomLog.step(4, "Go to My services – Calling & SMS");
        CallingAndSmsPage callingAndSmsPage = PageOpening.open(getBrowser(), CallingAndSmsPage.class);

        CustomLog.step(5, String.format("Select your number (%s) in dropdown list", user.getPhoneNumber()));
        callingAndSmsPage.selectPhoneNumber(user.getPhoneNumber());
        String warningText = callingAndSmsPage.getWarningText();

        if (warningText != null) {
            assumeThat("Impossible to change 'Missed Calls Alert' state, probably you have run this test recently. Try to run test later",
                    warningText, is(not(containsString("You will be able to change service settings once we process your request. Thank you."))));
        }

        CustomLog.step(6, "Deactivate (or Activate) Missed Call Alert");
        BasketPage basketPage = callingAndSmsPage.toggleMissedCallsAlert();
        Assert.assertThat("Success message on 'Basket' page is not equal with expected", basketPage.getSuccessMessageText(), is(equalTo("The service Missed calls alert was added to the list of changes")));

        BasketThankYouPage basketThankYouPage =  basketPage.confirmChanges();
        Assert.assertThat("Success message on 'Basket Thank You' page is not equal with expected", basketThankYouPage.getSuccessMessageText(), is(equalTo("Your request is being processed. We will send you an SMS once we finish your changes.")));
        Assert.assertThat("Header on 'Basket Thank You' page is not equal with expected", basketThankYouPage.getHeaderText(), is(equalTo("Thank You for Your Order")));

        callingAndSmsPage = PageOpening.open(getBrowser(), CallingAndSmsPage.class);
        Assert.assertThat("Service state of 'Missed Calls Alert' is not equal with expected", callingAndSmsPage.getMissedCallsAlertServiceState(), is((equalTo("PROCESSING"))));

        CustomLog.step(7, "Log out");
        LogoutSuccessPage logoutSuccessPage = callingAndSmsPage.logOut();
        Assert.assertThat("Header on 'Logout Success' page is not equal with expected", logoutSuccessPage.getHeaderText(), is(equalTo("You were successfully logged out from My Vodafone.")));
        Assert.assertThat("Message text on 'Logout Success' page is not equal with expected", logoutSuccessPage.getMessageText(), is(equalTo("If you haven't completed everything you wanted, you may log in again.")));
    }
}
