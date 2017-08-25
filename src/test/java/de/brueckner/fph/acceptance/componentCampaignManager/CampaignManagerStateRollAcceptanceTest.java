package de.brueckner.fph.acceptance.componentCampaignManager;

import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static de.brueckner.fph.managers.TimelineManager.PROD_NONPROD_DROPDOWN_OPTION;
import static de.brueckner.fph.managers.TimelineManager.UNDER_24H_DROPDOWN;

public class CampaignManagerStateRollAcceptanceTest extends AbstractCampaignManagerTest {

    private static final Logger logger = LoggerFactory.getLogger(CampaignManagerStateRollAcceptanceTest.class);

    @Test
    public void campaignManagerStateRollAcceptanceTest() {

        logger.info("CampaignManagerStateRollAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());
        contentManager.waitContentSpinner();

        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-13 22:00:00");

        contentManager.waitContentSpinner();

        timelineManager.timelineSettingsSetDropdownOption(UNDER_24H_DROPDOWN, PROD_NONPROD_DROPDOWN_OPTION);

        contentManager.waitContentSpinner();

        campaignManager.open();
        WebLocator roll = new WebLocator().setText("A 160413 1307").setRenderMillis(10000);
        roll.assertReady();

        List<WebElement> campaign = getWebDriver().findElements(By.cssSelector(".campaignManager .campaign0.timeBasedPossition"));
        campaign.get(2).click();
        contentManager.waitContentSpinner();
        roll = new WebLocator().setText("25");
        roll.assertReady();

    }

    @AfterMethod
    public void restoreApplicationSettings() throws IOException {
        getMongoUtils().executeScript(USER_SETTINGS_SCRIPT_NAME);

    }
}



