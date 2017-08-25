package de.brueckner.fph.general.tests;


import de.brueckner.fph.general.AbstractGeneralTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.TimelineManager.TIMELINE_SETTINGS_MODAL;
import static de.brueckner.fph.managers.TimelineManager.TIMELINE_SETTINGS_SAVE;

public class TimelineSettings extends AbstractGeneralTest {

    private static final Logger logger = LoggerFactory.getLogger(TimelineSettings.class);

    @Test
    public void timelineSettingsTest() {

        //Open Timeline Settings
        timelineManager.openTimelineSettings();

        logger.info("Timeline Settings opened");

        //check if the application crashes on save
        WebElement saveTimelineSettings = getWebDriver().findElement(By.cssSelector(TIMELINE_SETTINGS_SAVE));
        saveTimelineSettings.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(TIMELINE_SETTINGS_MODAL)));

        logger.info("Timeline Settings working on save");
    }
}