package org.yandrut.gmail_at.configuration.manager;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class FirefoxManager {

    private FirefoxManager() {
    }

    public static void setupFirefox() {
        Configuration.browser = "firefox";
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("browser.privatebrowsing.autostart", true)
               .addArguments("--disable-notifications", "--disable-popup-blocking");
        Configuration.browserCapabilities = options;
    }
}