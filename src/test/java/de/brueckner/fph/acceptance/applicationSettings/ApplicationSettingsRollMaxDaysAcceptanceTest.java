package de.brueckner.fph.acceptance.applicationSettings;

import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static de.brueckner.fph.managers.ContentManager.ROLLS_TAB_TEXT;

public class ApplicationSettingsRollMaxDaysAcceptanceTest extends AbstractApplicationSettingsTest {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationSettingsRollMaxDaysAcceptanceTest.class);

    @Test //roll max days is set to 3 days
    public void applicationSettingsRollMaxDaysAcceptanceTest() {
        logger.info("applicationSettingsRollMaxDaysAcceptanceTest");

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());
        timelineManager.setNewDateForDatePicker("2016-04-11 22:00:00", "2016-04-14 22:00:00");

        //check if roll tab exists for selection smaller than 3 days
        contentManager.openTab(ROLLS_TAB_TEXT);

        //make the selection larger than 3 days and check if the roll tab is hidden
        timelineManager.setNewDateForDatePicker("2016-04-11 22:00:00", "2016-04-14 22:01:00");
        contentManager.waitContentSpinner();

        List<WebElement> tabs = getWebDriver().findElements(By.cssSelector(".mainContentTabs li"));
        for (int i = 0; i < tabs.size(); i++) {
            Assert.assertNotEquals(tabs.get(i).getText().trim(), "ROLLS");
        }

        //test changing the value from the application settings (change roll max days to 2 days)
        applicationSettingsManager.openSettings();
        applicationSettingsManager.rollMaxDays("4");
        applicationSettingsManager.saveSettings();

        //check if roll tab exists for selection smaller than 3 days
        contentManager.openTab(ROLLS_TAB_TEXT);

    }
}