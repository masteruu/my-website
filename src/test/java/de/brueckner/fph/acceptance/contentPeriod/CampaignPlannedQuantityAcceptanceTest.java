package de.brueckner.fph.acceptance.contentPeriod;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static de.brueckner.fph.managers.CampaignManager.CAMPAIGN_PLANNED_QUANTITY_INPUT;
import static de.brueckner.fph.managers.ContentManager.CAMPAIGN_OVERVIEW_OUTPUT_INFO;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_LONG_TIME_FORMAT;

public class CampaignPlannedQuantityAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(CampaignPlannedQuantityAcceptanceTest.class);

    @Test
    public void campaignManagerPlannedQuantityCurrentCampaignAcceptanceTest() {

        logger.info("campaignManagerPlannedQuantityCurrentCampaignAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.clickCampaign("campaign:160509041329419:1462767209419:null");

        campaignManager.open();

        //set the planned quantity to 1.9
        WebElement plannedQuantity = getWebDriver().findElement(By.cssSelector(CAMPAIGN_PLANNED_QUANTITY_INPUT));
        plannedQuantity.clear();
        plannedQuantity.sendKeys("1.9");

        campaignManager.save();
        campaignManager.close();
        contentManager.waitContentSpinner();

        WebElement campaignProgress = getWebDriver().findElement(By.cssSelector(CAMPAIGN_OVERVIEW_OUTPUT_INFO));
        Assert.assertEquals(campaignProgress.getText(), "OUTPUT\n" +
                "+\n" +
                "Roll\n" +
                "01h 53min\n" +
                "Remaining\n" +
                "6,149 / 46,900m\n" +
                "Campaign\n" +
                "1.3  t\n" +
                "Produced\n" +
                "09min\n" +
                "0.6 t\n" +
                "Remaining\n" +
                "1.3 / 1.9 t\n" +
                DateUtils.date("2016-05-11 10:09:02", DEFAULT_SHORT_DATE_LONG_TIME_FORMAT) + "\n" +
                "Expected End");

    }

    @Test
    public void campaignManagerOverproductiveAcceptanceTest() {

        logger.info("campaignManagerOverproductiveAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.clickCampaign("campaign:160509041329419:1462767209419:null");
        campaignManager.open();

        //set the planned quantity to 1 (for overproduction)
        WebElement plannedQuantity = getWebDriver().findElement(By.cssSelector(CAMPAIGN_PLANNED_QUANTITY_INPUT));
        plannedQuantity.clear();
        plannedQuantity.sendKeys("1");

        campaignManager.save();
        campaignManager.close();

        contentManager.waitContentSpinner();

        WebElement campaignProgress = getWebDriver().findElement(By.cssSelector(CAMPAIGN_OVERVIEW_OUTPUT_INFO));
        Assert.assertEquals(campaignProgress.getText(), "OUTPUT\n" +
                "+\n" +
                "Roll\n" +
                "01h 53min\n" +
                "Remaining\n" +
                "6,149 / 46,900m\n" +
                "Campaign\n" +
                "1.3  t\n" +
                "Produced\n" +
                "+03min\n" +
                "+0.3 t\n" +
                "Overproduction\n" +
                "1.3 / 1.0 t\n" +
                "11.05.16 11:56:07\n" +
                "Reached");

    }

    @Test
    public void campaignManagerPlannedQuantityAcceptanceTest() {

        logger.info("campaignManagerPlannedQuantityAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-04-05 22:00:00", "2016-04-06 22:00:00");

        timelineManager.clickCampaign("campaign:160406134725061:1459950445061:1459977371026");

        campaignManager.open();

        //set the planned quantity to 1.7
        WebElement plannedQuantity = getWebDriver().findElement(By.cssSelector(CAMPAIGN_PLANNED_QUANTITY_INPUT));
        plannedQuantity.clear();
        plannedQuantity.sendKeys("1.7");

        campaignManager.save();
        campaignManager.close();

        contentManager.waitContentSpinner();

        //check with a planned quantity
        WebElement producedPlanned = getWebDriver().findElement(By.cssSelector(".campaignProgress.campaignPlanned.campaignBefore .producedPlanned"));
        Assert.assertEquals(producedPlanned.getText(), "33.5  t\n" +
                "Produced\n" +
                "1.7  t\n" +
                "Planned");

        WebElement blueText = getWebDriver().findElement(By.cssSelector(".plannedValue.blueText"));
        Assert.assertEquals(blueText.getText(), "1.7  t");

        //set the planned quantity to 0
        campaignManager.open();
        plannedQuantity = getWebDriver().findElement(By.cssSelector(CAMPAIGN_PLANNED_QUANTITY_INPUT));
        plannedQuantity.clear();
        plannedQuantity.sendKeys("0");

        campaignManager.save();
        campaignManager.close();
        contentManager.waitContentSpinner();

        //check without a planned quantity
        producedPlanned = getWebDriver().findElement(By.cssSelector(".campaignProgress.campaignPlanned.campaignBefore .producedPlanned"));
        Assert.assertEquals(producedPlanned.getText(), "33.5  t\n" +
                "Produced");

    }

    @AfterMethod
    public void restoreCampaigns() throws IOException {
        getMongoUtils().executeScript(CAMPAIGNS_SCRIPT_NAME);
    }
}
