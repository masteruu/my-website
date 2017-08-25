package de.brueckner.fph.acceptance.componentCampaignManager;

import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;

public class CampaignManagerOpenClickCampaignNameAcceptanceTest extends AbstractCampaignManagerTest {

    private static final Logger logger = LoggerFactory.getLogger(CampaignManagerOpenClickCampaignNameAcceptanceTest.class);

    @Test
    public void campaignManagerOpenClickCampaignNameAcceptanceTest() {

        logger.info("CampaignManagerOpenClickCampaignNameAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-04-10 22:00:00", "2016-04-13 22:00:00");

        timelineManager.clickCampaign("campaign:160411100229908:1460368949908:1460383722345");

        contentManager.waitContentSpinner();

        WebElement campaignName = getWebDriver().findElement(By.cssSelector(".campaignName span:nth-child(2)"));
        campaignName.click();

        timelineManager.waitTimelineSpinner();

        //assert that it is opened
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".campaignManager")));
    }
}
