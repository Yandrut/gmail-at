package org.yandrut.gmail_at.utils;

import com.codeborne.selenide.Selenide;
import com.epam.reportportal.message.ReportPortalMessage;
import com.epam.reportportal.service.ReportPortal;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.openqa.selenium.OutputType;

public class LoggingUtils {

    private LoggingUtils() {
    }

    public static void attachScreenShot() {
        try {
            File screenshot = Selenide.screenshot(OutputType.FILE);
            ReportPortalMessage message = new ReportPortalMessage(screenshot, "Browser screenshot");
            ReportPortal.emitLog(message, "info", new Date());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}