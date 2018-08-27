package VodafoneAutotest.Utilities;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Properties;
import VodafoneAutotest.Core.BrowserType;

public class ConfigReader {

    private Properties prop;
    private static Hashtable<String, String> AppSettings;
    String propFileName = "resources\\config.properties";

    public ConfigReader() {
        prop = new Properties();
        AppSettings = new Hashtable<>();

        try {
            this.prop.load(new FileInputStream(propFileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        for (String key : this.prop.stringPropertyNames()) {
            AppSettings.put(key, this.prop.getProperty(key));
        }
    }

    public static BrowserType getBrowser() {
        String browser = AppSettings.get("browser").toLowerCase();
        return BrowserType.valueOf(browser);
    }
}
