package org.yandrut.gmail_at.driver;

import org.openqa.selenium.WebDriver;
import org.yandrut.gmail_at.driver.manager.ChromeManager;
import org.yandrut.gmail_at.driver.manager.FirefoxManager;
import org.yandrut.gmail_at.exception.DriverNotSupportedException;

public final class DriverFactory {

    private DriverFactory() {}

    public static WebDriver createDriver() {
        var browserType = System.getProperty("browser", "chrome");
        WebDriver driver;
        if (browserType.equalsIgnoreCase("chrome")) {
            driver = ChromeManager.getChromeDriver();
        } else if (browserType.equalsIgnoreCase("firefox")) {
            driver = FirefoxManager.getFirefoxDriver();
        } else {
            throw new DriverNotSupportedException("Provided driver is not supported");
        }
        driver.manage().window().maximize();
        return driver;
    }
}