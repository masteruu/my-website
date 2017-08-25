package de.brueckner.fph.acceptance.componentsTimeline;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ContentManager.PRODUCTION_TREND_HEADER_BUTTON;

public class TimelineAxisAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(TimelineAxisAcceptanceTest.class);

    @Test
    public void timelineAxisAcceptance() {
        logger.info("TimelineAxisAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-13 22:00:00");

        WebElement toggleTrendTimelineButton = getWebDriver().findElement(By.cssSelector(PRODUCTION_TREND_HEADER_BUTTON));
        toggleTrendTimelineButton.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.className("timelineTrendContainer")));

        //check if the axis display the correct values
        WebElement axes = getWebDriver().findElement(By.cssSelector(".axes"));
        Assert.assertEquals(axes.getText(), "500\n" +
                "1,000\n" +
                "1,500\n" +
                "2,000\n" +
                "2,500\n" +
                "µm THICKNESS\n" +
                "500\n" +
                "600\n" +
                "700\n" +
                "800\n" +
                "900\n" +
                "m/min SPEED\n" +
                "500\n" +
                "1,000\n" +
                "1,500\n" +
                "2,000\n" +
                "2,500\n" +
                "kg/h OUTPUT");

    }

    @AfterMethod
    public void restoreApplicationSettings() throws IOException {
        getMongoUtils().executeScript(USER_SETTINGS_SCRIPT_NAME);
    }
}