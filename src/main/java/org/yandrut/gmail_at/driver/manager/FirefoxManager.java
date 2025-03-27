package org.yandrut.gmail_at.driver.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class FirefoxManager {

    private FirefoxManager() {}

    public static WebDriver getFirefoxDriver() {
        return new FirefoxDriver(new FirefoxOptions()
                .addArguments(
                        "--disable-notifications",
                        "--disable-popup-blocking"
                ));
    }
}