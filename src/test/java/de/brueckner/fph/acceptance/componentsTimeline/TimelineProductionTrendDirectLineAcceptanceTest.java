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

public class TimelineProductionTrendDirectLineAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(TimelineProductionTrendDirectLineAcceptanceTest.class);

    @Test
    public void timelineTrendDirectLineAcceptance() {
        logger.info("TimelineProductionTrendDirectLineAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //set trend to direct line
        timelineManager.openTimelineSettings();
        WebElement trendSettings = getWebDriver().findElement(By.cssSelector(TIMELINE_SETTINGS_TREND));
        trendSettings.click();

        WebLocator directLine = new WebLocator().setText("Direct Line");
        directLine.click();
    }
}