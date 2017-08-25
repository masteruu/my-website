package de.brueckner.fph.acceptance.componentsTimeline;


import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.TimelineManager.TIMELINE_SETTINGS_TREND;

public class TimelineProductionTrendEagerStepAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(TimelineProductionTrendEagerStepAcceptanceTest.class);

    @Test
    public void timelineTrendEagerStepAcceptanceTest() {
        logger.info("TimelineProductionTrendEagerStepAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.openTimelineSettings();

        //set trend to eager step
        WebElement trendSettings = getWebDriver().findElement(By.cssSelector(TIMELINE_SETTINGS_TREND));
        trendSettings.click();

        WebLocator eager = new WebLocator().setText("Eager Step");
        eager.click();
    }
}