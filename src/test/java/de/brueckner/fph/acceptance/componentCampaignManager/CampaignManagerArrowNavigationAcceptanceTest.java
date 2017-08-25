package de.brueckner.fph.acceptance.componentCampaignManager;

import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.CampaignManager.CAMPAIGN_NAME_INPUT;

public class CampaignManagerArrowNavigationAcceptanceTest extends AbstractCampaignManagerTest {

    private static final Logger logger = LoggerFactory.getLogger(CampaignManagerArrowNavigationAcceptanceTest.class);

    @Test
    public void campaignManagerArrowNavigationAcceptance() {

        logger.info("CampaignManagerArrowNavigationAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-13 22:00:00");

        timelineManager.clickCampaign("campaign:160413014141759:1460511701759:1460690794472");

        campaignManager.open();

        //key up
        checkShortcutKey(Keys.ARROW_UP, "160411_11p23u_400m_4.3t");

        //key down
        checkShortcutKey(Keys.ARROW_DOWN, "160411_11T23u_400m_4.3t");

        //key left
        checkShortcutKey(Keys.ARROW_LEFT, "160411_11T25u_400m_4.1t");

        //key right
        checkShortcutKey(Keys.ARROW_RIGHT, "160411_11T23u_400m_4.3t");

        //check shortcut after save
        WebElement keysScript = getWebDriver().findElement(By.cssSelector(CAMPAIGN_NAME_INPUT));
        applicationManager.sendKeysScript(keysScript, "TEST");

        campaignManager.save();

        //key left
        checkShortcutKey(Keys.ARROW_LEFT, "160411_11T25u_400m_4.1t");

    }

    private void checkShortcutKey(Keys arrowKey, String campaign) {
        Keyboard shortcut = ((RemoteWebDriver) getWebDriver()).getKeyboard();
        shortcut.pressKey(arrowKey);
        campaignManager.waitSpinnerCM();
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.textToBePresentInElementValue(By.cssSelector("#campaignNameInput"), campaign));
    }
}