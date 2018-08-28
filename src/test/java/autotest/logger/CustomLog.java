package autotest.logger;

import autotest.core.BrowserDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomLog {
    public static Logger log = LoggerFactory.getLogger(CustomLog.class);

    public static void step(int step, String text) {
        log.info(String.format("Step %s: %s", step, text));
    }

    public static void info(String text) {
        log.info(text);
    }

    public static void error(String text) {
        log.error(text);
    }

    public static void debug(String text) {
        log.debug(text);
    }

    public static void errorWithScreenshot(String text, BrowserDriver driver, String screenshotName) {
        Path path = captureScreenshot(screenshotName, driver);
        int index = text.indexOf("(Session info");
        if (index == -1) {
            index = text.indexOf("Build info");
            if (index == -1) {
                index = text.length();
            }
        }
        error(text.substring(0, index));
        if (path != null) {
            info("Screenshot: " + path.toAbsolutePath());
        }
    }

    public static void error(String text, Exception e) {
        log.error(text, e);
    }

    private static Path captureScreenshot(String name, BrowserDriver driver) {
        File screenshot = ((TakesScreenshot) driver.getWebDriver()).getScreenshotAs(OutputType.FILE);
        Path folderPath = Paths.get("target", "screenshots", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        Path screenshotName = Paths.get(String.format("%s %s.png", name, LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss"))));
        Path fullPath = folderPath.resolve(screenshotName);
        try {
            FileUtils.copyFile(screenshot, fullPath.toFile());
            return fullPath;
        } catch (IOException e) {
            return null;
        }
    }
}
