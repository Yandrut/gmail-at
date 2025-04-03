package org.yandrut.gmail_at.hooks;

import static com.codeborne.selenide.Selenide.closeWebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.yandrut.gmail_at.configuration.SelenideConfiguration;
import org.yandrut.gmail_at.utils.LoggingUtils;

public class CucumberHooks {

    @Before
    public void configure() {
        SelenideConfiguration.configureSelenide();
    }

    @After
    public void closeBrowser() {
        LoggingUtils.attachScreenShot();
        closeWebDriver();
    }
}
