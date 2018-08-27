package VodafoneAutotest.Core;

import VodafoneAutotest.Utilities.ConfigReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BrowserDriver {

    public static BrowserType BrowserType;
    public ConfigReader Config;
    private static WebDriver webDriver;


    public BrowserDriver() {
        Config = new ConfigReader();
        BrowserType = Config.getBrowser();
    }

    public static WebDriver getWebDriver() {
        if (isBrowserOpened()) {
            return webDriver;
        } else {
            forceBrowserDriver(BrowserType);
            return webDriver;
        }
    }

    private static Boolean isBrowserOpened() {
        return webDriver != null;
    }

    private static void forceBrowserDriver(BrowserType browser) {
        if (isBrowserOpened()) {
            quit();
        }
        switch (browser) {
            case chrome: {
                System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
                webDriver = new ChromeDriver();
                break;
            }
            case firefox: {
                System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
                webDriver = new FirefoxDriver();
                break;
            }
            case edge: {
                System.setProperty("webdriver.edge.driver", "MicrosoftWebDriver.exe");
                webDriver = new EdgeDriver();
                break;
            }
            default: {
                throw new Error("Incorrect browser in settings");
            }
        }
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    public URI getUri() {
        try {
            return new URI(getWebDriver().getCurrentUrl());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }

    public void switchToNewWindow() {
        String parentWindow = getWebDriver().getWindowHandle();
        Set<String> windowHandles = getWebDriver().getWindowHandles();
        for (String windowHandle : windowHandles) {
            if (!windowHandle.equals(parentWindow)) {
                getWebDriver().switchTo().window(windowHandle);
            }
        }
    }


    public static void quit() {
        if (isBrowserOpened()) {
            webDriver.quit();
        }
    }

    public static Object executeScript(String script) {
        return ((JavascriptExecutor)webDriver).executeScript(script);
    }

    public static Object executeScript(String script, Object[] parameters) {
        return ((JavascriptExecutor)webDriver).executeScript(script, parameters);
    }
}
