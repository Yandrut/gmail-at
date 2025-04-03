package org.yandrut.gmail_at.runners;

import com.epam.reportportal.junit5.ReportPortalExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.yandrut.gmail_at.utils.ScreenShootOnFailureExtension;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = "cucumber.glue",
                        value = "org.yandrut.gmail_at.steps,"
                            + "org.yandrut.gmail_at.hooks"
                            + "org.yandrut.gmail_at.utils.CucumberEventListener"
)
@ConfigurationParameter(key = "cucumber.plugin", value = "pretty, html:target/cucumber-reports.html")
@ConfigurationParameter(key = "cucumber.plugin", value = "pretty, com.epam.reportportal.cucumber.StepReporter")
@ExtendWith(ReportPortalExtension.class)
@ExtendWith(ScreenShootOnFailureExtension.class)
public class RunCucumberTest {

}
