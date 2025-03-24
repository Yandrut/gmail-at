package org.yandrut.gmail_at.configuration.manager;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;

public final class ChromeManager {

    private ChromeManager() {
    }

    public static void configureChrome() {
        Configuration.browser = "chrome";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito",
            "--disable-notifications",
            "--disable-popup-blocking",
            "--incognito",
            "--disable-extensions",
            "--disable-blink-features=AutomationControlled",
            "--start-maximized");
        Configuration.browserCapabilities = options;
    }
}