package de.brueckner.fph.acceptance.contentRoll;

import de.brueckner.fph.acceptance.applicationSettings.AbstractApplicationSettingsTest;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ContentManager.ROLLS_TAB_TEXT;

/**
 * Test the content of the rolls tab for a specific campaign
 */
public class RollsTableInCampaignAcceptanceTest extends AbstractApplicationSettingsTest {

    private static final Logger logger = LoggerFactory.getLogger(RollsTableInCampaignAcceptanceTest.class);

    @Test
    public void rollsTableFilteringAcceptance() {
        logger.info("RollsTableInCampaignAcceptanceTest");

        //perform the login
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());
        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-11 06:00:00", "2016-04-11 15:00:00");
        //select the campaign to which the roll belongs
        timelineManager.clickCampaign("campaign:160411062629088:1460355989088:1460368949908");

        String xPath = "//span[../../../div[contains(@class, 'campaignName') and contains(@class, 'splittableName')] and not(contains(@class, 'campaignFlagSpan'))]";
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
        contentManager.waitContentSpinner();

        //click on the quality tab
        contentManager.openTab(ROLLS_TAB_TEXT);
        contentManager.waitContentSpinner();

        xPath = "//div[contains(@class, 'col_name_rollLabelId')]/div/div/span[contains(text(), 'A 160411 1629') or contains(text(), 'A 160411 1426')]";
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
    }
}
