package org.yandrut.gmail_at.driver.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class ChromeManager {

    private ChromeManager(){}

    public static WebDriver getChromeDriver() {
        return new ChromeDriver(new ChromeOptions()
            .addArguments(
            "--disable-notifications",
            "--disable-popup-blocking",
            "--incognito"
        ));
    }
}