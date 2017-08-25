package de.brueckner.fph.acceptance.componentsTimeline;


import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ContentManager.PRODUCTION_TREND_HEADER_BUTTON;
import static de.brueckner.fph.managers.TimelineManager.*;

public class TimelineProductionTrendCheckboxesAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(TimelineProductionTrendCheckboxesAcceptanceTest.class);

    @Test //TEST FAILING BECAUSE OF TREND BUG
    public void timelineTrendAcceptance() {
        logger.info("timelineTrendAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        WebElement prodTrend = getWebDriver().findElement(By.cssSelector(PRODUCTION_TREND_HEADER_BUTTON));
        prodTrend.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(TIMELINE_TREND_CONTAINER)));

        //uncheck thickness box and see if it is not appearing
        timelineSettingsTrendCheckboxes(TIMELINE_SETTINGS_TREND_THICKNESS_CHECKBOX, ".THICKNESS_axis");

        //uncheck speed box and see if it is not appearing
        timelineSettingsTrendCheckboxes(TIMELINE_SETTINGS_TREND_SPEED_CHECKBOX, ".SPEED_axis");

        //uncheck output box and see if it is not appearing
        timelineSettingsTrendCheckboxes(TIMELINE_SETTINGS_TREND_OUTPUT_CHECKBOX, ".THROUGHPUT_axis");

    }

    void timelineSettingsTrendCheckboxes(String checkbox, String container) {
        timelineManager.openTimelineSettings();
        WebElement trendSettings = getWebDriver().findElement(By.cssSelector(TIMELINE_SETTINGS_TREND));
        trendSettings.click();
        WebElement check = getWebDriver().findElement(By.cssSelector(checkbox));
        check.click();
        applicationManager.pressEnter();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(container)));
    }

    @AfterMethod
    public void restoreApplicationSettings() throws IOException {
        getMongoUtils().executeScript(USER_SETTINGS_SCRIPT_NAME);
    }
}