package de.brueckner.fph.acceptance.contentPeriod;


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
import java.util.List;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ContentManager.*;

public class ThicknessTrendSettingsToleranceAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(ThicknessTrendSettingsToleranceAcceptanceTest.class);

    @Test
    public void thicknessTrendSettingsToleranceAcceptanceTest() {
        logger.info("ThicknessTrendSettingsToleranceAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-22 11:04:00", "2016-04-22 16:19:00");

        contentManager.openTab(THICKNESS_TAB_TEXT);

        WebElement area = getWebDriver().findElement(By.cssSelector(".area"));
        String firstAreaWidth = area.getAttribute("height");

        //open thickness settings modal
        WebElement thicknessSettings = getWebDriver().findElement(By.cssSelector(THICKNESS_TAB_SETTINGS));
        thicknessSettings.click();
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(THICKNESS_MODAL)));

        //insert 20 and 20 in tolerance min and max
        List<WebElement> tolerance = getWebDriver().findElements(By.cssSelector(".thicknessTrendSettingsModal input"));
        tolerance.get(0).clear();
        tolerance.get(0).sendKeys("20");

        tolerance = getWebDriver().findElements(By.cssSelector(".thicknessTrendSettingsModal input"));
        tolerance.get(1).clear();
        tolerance.get(1).sendKeys("20");

        WebElement saveSettings = getWebDriver().findElement(By.cssSelector(THICKNESS_TAB_SETTINGS_SAVE));
        saveSettings.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(THICKNESS_MODAL)));

        area = getWebDriver().findElement(By.cssSelector(".area"));
        String secondAreaHeight = area.getAttribute("height");

        //check that the area height is bigger after changing the tolerance
        if (Math.round(Double.valueOf(firstAreaWidth)) >= Math.round(Double.valueOf(secondAreaHeight))) {
            Assert.fail();
        }
    }

    @AfterMethod
    public void restoreApplicationSettings() throws IOException {
        getMongoUtils().executeScript(USER_SETTINGS_SCRIPT_NAME);
    }
}
