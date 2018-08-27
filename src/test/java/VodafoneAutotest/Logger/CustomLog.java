package VodafoneAutotest.Logger;

import VodafoneAutotest.Core.BrowserDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomLog {
    public static Logger log = LoggerFactory.getLogger(CustomLog.class);

    public static void step(int step, String text) {
        log.info(String.format("Step %s: %s", step, text));
    }

    public static void traceInformation(String text) {
        log.info(text);
    }

    public static void traceError(String text) {
        log.error(text);
    }

    public static void traceDebug(String text) {
        log.debug(text);
    }

    public static void traceErrorWithScreenshot(String text, BrowserDriver driver, String screenshotName) {
        String path = captureScreenshot(screenshotName, driver);
        int index = text.indexOf("(Session info");
        if (index == -1) {
            index = text.indexOf("Build info");
            if (index == -1) {
                index = text.length();
            }
        }
        log.error(text.substring(0, index));
        if (path != null) {
            log.info("Screenshot: " + System.getProperty("user.dir") + path);
        }
    }

    public static void traceError(String text, Exception e) {
        log.error(text, e);
    }

    private static String captureScreenshot(String name, BrowserDriver driver) {
        File screenshot = ((TakesScreenshot) driver.getWebDriver()).getScreenshotAs(OutputType.FILE);
        String path = String.format(".\\target\\screenshots\\%s\\%s %s.png", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                name, LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss")));
        try {
            FileUtils.copyFile(screenshot, new File(path));
            return path.substring(1);
        } catch (IOException e) {
            return null;
        }
    }
}
