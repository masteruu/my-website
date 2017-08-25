package de.brueckner.fph.acceptance.contentPeriod;

import com.sdl.selenium.web.utils.Utils;
import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ContentManager.CAMPAIGN_CAPACITY;
import static de.brueckner.fph.managers.ContentManager.CAMPAIGN_OVERVIEW_TABS_ANIMATION;

public class CampaignNonProductiveAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(CampaignNonProductiveAcceptanceTest.class);

    @Test
    public void campaignNonProductiveAcceptance() {
        logger.info("CampaignNonProductiveAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-05-08 22:00:00", "2016-05-11 10:00:00");

        //click a non productive campaign
        timelineManager.clickCampaign("campaign:160508234540232:1462751140232:1462767209419");
        contentManager.waitContentSpinner();

        WebElement capacity = getWebDriver().findElement(By.cssSelector(CAMPAIGN_CAPACITY));
        capacity.click();
        Utils.sleep(CAMPAIGN_OVERVIEW_TABS_ANIMATION);

        //check message for non productive campaign
        WebElement notAvailable = getWebDriver().findElement(By.cssSelector(".efficiencyContent li:nth-child(3) span:nth-child(2)"));
        Assert.assertEquals(notAvailable.getText(), "Capacity diagram available only for productive time periods.");
    }
}
