package org.yandrut.gmail_at.driver;

import java.util.Objects;
import org.openqa.selenium.WebDriver;

public class DriverProvider {
    private static WebDriver driver;

    private DriverProvider() {
    }

    public static WebDriver getDriver() {
        if (Objects.isNull(driver)) {
            driver = DriverFactory.createDriver();
        }
        return driver;
    }

    public static void quit() {
        if (Objects.nonNull(driver)) {
            driver.quit();
            driver = null;
        }
    }
}