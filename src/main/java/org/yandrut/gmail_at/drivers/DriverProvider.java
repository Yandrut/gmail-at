package org.yandrut.gmail_at.drivers;

import java.util.Objects;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DriverProvider {
    private static WebDriver driver;

    public DriverProvider() {}

    @Bean
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