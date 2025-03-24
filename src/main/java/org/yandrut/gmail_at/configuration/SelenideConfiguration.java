package org.yandrut.gmail_at.configuration;

import com.codeborne.selenide.Configuration;
import org.yandrut.gmail_at.configuration.manager.ChromeManager;
import org.yandrut.gmail_at.configuration.manager.FirefoxManager;
import org.yandrut.gmail_at.exception.DriverNotSupportedException;

public class SelenideConfiguration {
    private static final String BROWSER_TYPE = System.getProperty("browser", "chrome");

    private SelenideConfiguration() {
    }

    public static void configureSelenide() {
        Configuration.headless = false;
        Configuration.timeout = 15000;
        Configuration.screenshots = true;
        Configuration.savePageSource = true;
        Configuration.fastSetValue = true;
        Configuration.pageLoadStrategy = "normal";
        Configuration.webdriverLogsEnabled = true;
        Configuration.clickViaJs = true;

        configureBrowser();
    }

    private static void configureBrowser() {
        if (BROWSER_TYPE.equalsIgnoreCase("chrome")) {
            ChromeManager.configureChrome();
        } else if (BROWSER_TYPE.equalsIgnoreCase("firefox")) {
            FirefoxManager.setupFirefox();
        } else {
            throw new DriverNotSupportedException("Provided driver is not supported");
        }
    }
}