package de.brueckner.fph.acceptance.contentPeriod;


import com.sdl.selenium.web.utils.Utils;
import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static de.brueckner.fph.managers.ContentManager.*;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_LONG_TIME_FORMAT;

public class CampaignCurrentRollOverproductionAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(CampaignCurrentRollOverproductionAcceptanceTest.class);

    @Test
    public void campaignCurrentOverproductionAcceptanceTest() {
        logger.info("CampaignCurrentRollOverproductionAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.clickCampaign("campaign:160509041329419:1462767209419:null");
        contentManager.waitContentSpinner();

        //check current campaign overproduction
        WebElement topInfo = getWebDriver().findElement(By.cssSelector(CAMPAIGN_OVERVIEW_OUTPUT_INFO));
        Assert.assertEquals(topInfo.getText(), "OUTPUT\n" +
                "+\n" +
                "Roll\n" +
                "+08min\n" +
                "Overproduction\n" +
                "6,149 / 3,000m\n" +
                "Campaign\n" +
                "1.3  t\n" +
                "Produced\n" +
                "04h 30min\n" +
                "18.8 t\n" +
                "Remaining\n" +
                "1.3 / 20.1 t\n" +
                DateUtils.date("2016-05-11 14:30:32", DEFAULT_SHORT_DATE_LONG_TIME_FORMAT) + "\n" +
                "Expected End");

        //check that the progress bar has a full width
        List<WebElement> progressbar = getWebDriver().findElements(By.cssSelector(".progress-bar"));
        Assert.assertEquals(progressbar.get(0).getAttribute("style"), "width: 100%;");

        WebElement expandGraph = getWebDriver().findElement(By.cssSelector(CAMPAIGN_OVERVIEW_EXPAND_INFO_BUTTON));
        expandGraph.click();
        Utils.sleep(CAMPAIGN_OVERVIEW_EXPAND_INFO_ANIMATION_TIME);

        topInfo = getWebDriver().findElement(By.cssSelector(CAMPAIGN_OVERVIEW_OUTPUT_INFO));
        Assert.assertEquals(topInfo.getText(), "OUTPUT\n" +
                "-\n" +
                "Roll\n" +
                "+08min\n" +
                "+3,149 m\n" +
                "Overproduction\n" +
                "6,149 / 3,000m\n" +
                "Campaign\n" +
                "1.3  t\n" +
                "Produced\n" +
                "04h 30min\n" +
                "18.8 t\n" +
                "Remaining\n" +
                "20.1 t Planned\n" +
                "1.3 t Good Roll\n" +
                "0.0 t Pending\n" +
                DateUtils.date("2016-05-11 14:30:32", DEFAULT_SHORT_DATE_LONG_TIME_FORMAT) + "\n" +
                "Expected End");

    }

    @BeforeMethod
    public void reduceOverproduction() throws IOException {
        getMongoUtils().executeScript(REDUCE_OVERPRODUCTION);
    }

    @AfterMethod
    public void restoreOverproduction() throws IOException {
        getMongoUtils().executeScript(RESTORE_OVERPRODUCTION);
    }

}