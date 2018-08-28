package autotest.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;

import autotest.core.BrowserType;

public class ConfigReader {

    private static Hashtable<String, String> AppSettings;
    private String propFileName = "resources\\config.properties";

    public ConfigReader() {
        Properties prop = new Properties();
        AppSettings = new Hashtable<>();

        try {
            prop.load(new FileInputStream(propFileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        for (String key : prop.stringPropertyNames()) {
            AppSettings.put(key, prop.getProperty(key));
        }
    }

    public BrowserType getBrowser() {
        String browser = AppSettings.get("browser").toUpperCase();
        return BrowserType.valueOf(browser);
    }
}
