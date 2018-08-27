package VodafoneAutotest.Tests;

import VodafoneAutotest.Core.BrowserDriver;
import VodafoneAutotest.Logger.CustomLog;
import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SeleniumTestBase {
    private BrowserDriver driver;

    BrowserDriver getBrowser() {
        if (driver != null) {
            return driver;
        } else {
            driver = new BrowserDriver();
            return driver;
        }
    }

    @Before
    public void setUp() {
        CustomLog.traceInformation("Test start time: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }

    @Rule
    public TestRule screenshotRule = new TestWatcher() {

        @Override
        protected void failed(Throwable e, Description description) {
            logErrorAndTakeScreenshot("Test fails with reason: " + e.getLocalizedMessage(), description);
        }

        @Override
        protected void skipped(AssumptionViolatedException e, Description description) {
            logErrorAndTakeScreenshot("Test skipped with reason: " + e.getLocalizedMessage(), description);
        }
    };

    private void logErrorAndTakeScreenshot(String message, Description description) {
        CustomLog.traceErrorWithScreenshot(message, driver, description.getDisplayName());
    }

    @After
    public void tearDown() {
        CustomLog.traceInformation("Test end time: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }

    @AfterClass
    public static void generalTearDown() {
        BrowserDriver.quit();
        CustomLog.log.info("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
}
