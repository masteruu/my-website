package de.brueckner.fph.acceptance.contentPeriod;


import com.sdl.selenium.web.WebLocator;
import com.sdl.selenium.web.utils.Utils;
import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.MouseUtils;
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
import static de.brueckner.fph.managers.CampaignManager.CAMPAIGN_PLANNED_QUANTITY_INPUT;
import static de.brueckner.fph.managers.ContentManager.*;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_LONG_TIME_FORMAT;

public class CampaignCurrentAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(CampaignCurrentAcceptanceTest.class);

    @Test
    public void campaignCurrentAcceptance() {
        logger.info("campaignCurrentAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //click the current campaign
        timelineManager.clickCampaign("campaign:160509041329419:1462767209419:null");
        contentManager.waitContentSpinner();

        //check current campaign content
        contentManager.checkContentPeriod(DateUtils.date("2016-05-09 04:13:00", DEFAULT_SHORT_DATE_FORMAT), "...");

        //check current campaign name and product
        contentManager.checkCampaignNameProduct("1605009_25u_450m_5.3 t", "25um Plain");

        WebElement campaignName = getWebDriver().findElement(By.cssSelector(CAMPAIGN_OVERVIEW_NAME));

        //check current campaign name tooltip
        MouseUtils.mouseOver(campaignName, getWebDriver());

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".tooltip-inner")));

        WebElement tooltip = getWebDriver().findElement(By.cssSelector(".tooltip-inner"));
        Assert.assertEquals(tooltip.getText(), "1605009_25u_450m_5.3t");

        //check current campaign product
        WebElement productName = getWebDriver().findElement(By.cssSelector(CAMPAIGN_OVERVIEW_PRODUCT));

        //check current campaign product tooltip
        MouseUtils.mouseOver(productName, getWebDriver());

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".tooltip-inner")));

        WebLocator tooltipProduct = new WebLocator().setClasses("tooltip-inner").setText("25um Plain");
        tooltipProduct.assertReady();

        //check name and product icons
        WebElement productIcon = getWebDriver().findElement(By.cssSelector(".productIcon"));
        WebElement campaignFlagSpan = getWebDriver().findElement(By.cssSelector(".campaignFlagSpan"));

        //check output information compacted
        WebElement topInfo = getWebDriver().findElement(By.cssSelector(CAMPAIGN_OVERVIEW_OUTPUT_INFO));
        Assert.assertEquals(topInfo.getText(), "OUTPUT\n" +
                "+\n" +
                "Roll\n" +
                "01h 53min\n" +
                "Remaining\n" +
                "6,149 / 46,900m\n" +
                "Campaign\n" +
                "1.3  t\n" +
                "Produced\n" +
                "04h 30min\n" +
                "18.8 t\n" +
                "Remaining\n" +
                "1.3 / 20.1 t\n" +
                DateUtils.date("2016-05-11 14:30:32", DEFAULT_SHORT_DATE_LONG_TIME_FORMAT) + "\n" +
                "Expected End");

        //check both progress bars
        List<WebElement> progress = getWebDriver().findElements(By.cssSelector(".progress-bar"));
        Assert.assertEquals(progress.get(0).getAttribute("style").substring(0, 12), "width: 13.11");
        Assert.assertEquals(progress.get(1).getAttribute("style").substring(0, 11), "width: 6.31");

        //check blue text
        WebElement blueText = getWebDriver().findElement(By.cssSelector(".bottomInfo .blueText"));
        Assert.assertEquals(blueText.getText(), "20.1");

        WebElement expandGraph = getWebDriver().findElement(By.cssSelector(CAMPAIGN_OVERVIEW_EXPAND_INFO_BUTTON));
        expandGraph.click();
        Utils.sleep(CAMPAIGN_OVERVIEW_EXPAND_INFO_ANIMATION_TIME);

        //check output information expanded
        topInfo = getWebDriver().findElement(By.cssSelector(CAMPAIGN_OVERVIEW_OUTPUT_INFO));
        Assert.assertEquals(topInfo.getText(), "OUTPUT\n" +
                "-\n" +
                "Roll\n" +
                "01h 53min\n" +
                "40,751 m\n" +
                "Remaining\n" +
                "6,149 / 46,900m\n" +
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

    @Test
    public void campaignCurrentNoPlannedQuantity() {
        logger.info("campaignCurrentNoPlannedQuantity");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //click the current campaign
        timelineManager.clickCampaign("campaign:160509041329419:1462767209419:null");
        contentManager.waitContentSpinner();

        campaignManager.open();

        WebElement plannedQuantity = getWebDriver().findElement(By.cssSelector(CAMPAIGN_PLANNED_QUANTITY_INPUT));
        plannedQuantity.clear();
        plannedQuantity.sendKeys("0");
        campaignManager.save();
        campaignManager.close();
        contentManager.waitContentSpinner();

        WebElement producedPlanned = getWebDriver().findElement(By.cssSelector(CAMPAIGN_OVERVIEW_OUTPUT_INFO));
        Assert.assertEquals(producedPlanned.getText().trim(), "OUTPUT\n" +
                "+\n" +
                "Roll\n" +
                "01h 53min\n" +
                "Remaining\n" +
                "6,149 / 46,900m\n" +
                "1.3  t\n" +
                "Produced\n" +
                DateUtils.date("2016-05-09 04:13:29", DEFAULT_SHORT_DATE_LONG_TIME_FORMAT) + "\n" +
                "Start");

    }

    @AfterMethod
    public void restoreCampaigns() throws IOException {
        getMongoUtils().executeScript(CAMPAIGNS_SCRIPT_NAME);
    }
}