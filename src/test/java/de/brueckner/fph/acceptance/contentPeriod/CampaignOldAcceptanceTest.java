package de.brueckner.fph.acceptance.contentPeriod;

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
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ContentManager.*;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_LONG_TIME_FORMAT;

public class CampaignOldAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(CampaignOldAcceptanceTest.class);

    @Test
    public void campaignOverviewDetailsAcceptanceTest() {
        logger.info("campaignOverviewDetailsAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-09 22:00:00", "2016-04-16 22:00:00");

        timelineManager.clickCampaign("campaign:160411140842345:1460383722345:1460461646310");
        contentManager.waitContentSpinner();

        //check if the selection is ok in breadcrum
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", "160411_11P25u_400m_4.1t"));

        //check overview content
        contentManager.checkCampaignNameProduct("160411_11P25u_400m_ 4.1t", "25um Plain");

        //check name and product legend
        WebElement productNameLegend = getWebDriver().findElement(By.cssSelector(".productNameLegend"));
        Assert.assertEquals(productNameLegend.getText().trim(), "Product");
        WebElement campaignNameLegend = getWebDriver().findElement(By.cssSelector(".campaignNameLegend"));
        Assert.assertEquals(campaignNameLegend.getText().trim(), "Campaign");

        //check output information
        List<WebElement> paddingBoxSettings = getWebDriver().findElements(By.cssSelector(".paddingBoxSettings"));
        Assert.assertEquals(paddingBoxSettings.get(1).getText().trim(), "OUTPUT");

        //check produced value
        WebElement bigValue = getWebDriver().findElement(By.cssSelector(".producedPlannedValue .bigValue"));
        Assert.assertEquals(bigValue.getText().trim(), "101.8  t");

        //check produced label
        WebElement producedPlannedLabel = getWebDriver().findElement(By.cssSelector(".producedPlannedLabel"));
        Assert.assertEquals(producedPlannedLabel.getText().trim(), "Produced");

        //check roll duration
        WebElement rollDuration = getWebDriver().findElement(By.cssSelector(".rollDuration"));
        Assert.assertEquals(rollDuration.getText().trim(), DateUtils.date("2016-04-11 14:08:42", DEFAULT_SHORT_DATE_LONG_TIME_FORMAT) + "\n" +
                "Start\n" +
                "21h 38min 43s\n" +
                "Duration\n" +
                DateUtils.date("2016-04-12 11:47:26", DEFAULT_SHORT_DATE_LONG_TIME_FORMAT) + "\n" +
                "End");

        //check efficiency label
        WebElement efficiencyTitle = getWebDriver().findElement(By.cssSelector(".efficiencyTitle"));
        Assert.assertEquals(efficiencyTitle.getText().trim(), "EFFICIENCY");

    }

    @Test
    public void campaignOverviewYieldAcceptanceTest() {
        logger.info("campaignOverviewYieldAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-09 22:00:00", "2016-04-16 22:00:00");

        timelineManager.clickCampaign("campaign:160411140842345:1460383722345:1460461646310");
        contentManager.waitContentSpinner();

        //check yield bar values total material, pending and good roll
        List<WebElement> barValues = getWebDriver().findElements(By.cssSelector(".barValues"));
        Assert.assertEquals(barValues.get(0).getText().trim(), "102,783 kg\n" +
                "Total Material");

        barValues = getWebDriver().findElements(By.cssSelector(".barValues"));
        Assert.assertEquals(barValues.get(2).getText().trim(), "101,794 kg\n" +
                "Pending");

        barValues = getWebDriver().findElements(By.cssSelector(".barValues"));
        Assert.assertEquals(barValues.get(3).getText().trim(), "0 kg\n" +
                "Good Roll");

        //check chart head and values
        WebElement chartHead = getWebDriver().findElement(By.cssSelector(".chartHead"));
        Assert.assertEquals(chartHead.getText().trim(), "0.95 % 979 kg");

        List<WebElement> value = getWebDriver().findElements(By.cssSelector(".value"));
        Assert.assertEquals(value.get(0).getText(), "99");
        Assert.assertEquals(value.get(1).getText(), "98.6");
        Assert.assertEquals(value.get(2).getText(), "84");

        //check tooltip text
        String yieldTooltiptext = getTabsTooltips(CAMPAIGN_YIELD, CAMPAIGN_YIELD + " .tabFormulaInfo");
        Assert.assertEquals(yieldTooltiptext, "101,794\n" +
                "kg Pending\n" +
                "+\n" +
                "0\n" +
                "kg Good Roll\n" +
                "102,783\n" +
                "kg Total Material\n" +
                "= 99\n" +
                "% Yield\n" +
                "* All weights calculated from dosing scales\n" +
                "Quality Perspective:\n" +
                "Good Roll - Rolls which fulfilled quality specifications\n" +
                "Pending - Rolls not yet qualified");
    }

    @Test
    public void campaignOverviewUptimeAcceptanceTest() {
        logger.info("campaignOverviewUptimeAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-09 22:00:00", "2016-04-16 22:00:00");

        timelineManager.clickCampaign("campaign:160411140842345:1460383722345:1460461646310");
        contentManager.waitContentSpinner();

        WebElement uptime = getWebDriver().findElement(By.cssSelector(CAMPAIGN_UPTIME));
        uptime.click();
        Utils.sleep(CAMPAIGN_OVERVIEW_TABS_ANIMATION);

        //check bar values total time and production
        List<WebElement> barChart = getWebDriver().findElements(By.cssSelector(".barchart-timeVal"));
        Assert.assertEquals(barChart.get(0).getText().trim(), "21h 38min");
        Assert.assertEquals(barChart.get(2).getText().trim(), "21h 20min");

        WebElement expandGraph = getWebDriver().findElement(By.cssSelector(".expandGraph"));
        expandGraph.click();
        Utils.sleep(CAMPAIGN_OVERVIEW_TABS_ANIMATION);

        WebElement subChartOpened = getWebDriver().findElement(By.cssSelector("#overview .subChartOpened"));
        Assert.assertEquals(subChartOpened.getText().trim(), "0.85 % THREADING\n" +
                "-\n" +
                "0.00 %\n" +
                "00min\n" +
                "CHR_THREAD\n" +
                "0.00 %\n" +
                "00min\n" +
                "MDO_THREAD\n" +
                "0.00 %\n" +
                "00min\n" +
                "TDO_THREAD\n" +
                "0.00 %\n" +
                "00min\n" +
                "TDO_SPEEDUP\n" +
                "0.74 %\n" +
                "09min\n" +
                "PUR_THREAD\n" +
                "0.10 %\n" +
                "01min\n" +
                "PUR_SPEEDUP\n" +
                "0.00 %\n" +
                "00min\n" +
                "CUTBACK\n" +
                "0.00 %\n" +
                "00min\n" +
                "UNDEFINED");

        String uptimeTooltiptext = getTabsTooltips(CAMPAIGN_UPTIME, CAMPAIGN_UPTIME + " .tabFormulaInfo");
        Assert.assertEquals(uptimeTooltiptext, "21h 20min\n" +
                "in production\n" +
                "21h 38min\n" +
                "Total Time\n" +
                "= 98.6\n" +
                "% Uptime");
    }

    @Test
    public void campaignOverviewCapacityAcceptanceTest() {
        logger.info("campaignOverviewCapacityAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-09 22:00:00", "2016-04-16 22:00:00");

        timelineManager.clickCampaign("campaign:160411140842345:1460383722345:1460461646310");
        contentManager.waitContentSpinner();
        //capacity
        WebElement capacity = getWebDriver().findElement(By.cssSelector(CAMPAIGN_CAPACITY));
        capacity.click();
        Utils.sleep(CAMPAIGN_OVERVIEW_TABS_ANIMATION);

        //check capacity bar values
        List<WebElement> capacityValueName = getWebDriver().findElements(By.cssSelector(".capacityValueName"));
        Assert.assertEquals(capacityValueName.get(0).getText().trim(), "5,407 kg/h");
        Assert.assertEquals(capacityValueName.get(1).getText().trim(), "4,536 kg/h");

        String capacityTooltiptext = getTabsTooltips(CAMPAIGN_CAPACITY, CAMPAIGN_CAPACITY + " .tabFormulaInfo");
        Assert.assertEquals(capacityTooltiptext, "4,536\n" +
                "kg/h Output on Winder\n" +
                "5,407\n" +
                "kg/h Nominal Output\n" +
                "= 84\n" +
                "% Capacity");

        WebElement chart = getWebDriver().findElement(By.cssSelector(".capacityChartContainer.fullHeight"));
        Assert.assertEquals(chart.getText().trim(), "Output on Winder kg/h\n" +
                "Speed m/min\n" +
                "100\n" +
                "200\n" +
                "300\n" +
                "500\n" +
                "0\n" +
                "1,000\n" +
                "2,000\n" +
                "3,000\n" +
                "4,000\n" +
                "5,000\n" +
                "6,000\n" +
                "12\n" +
                "15\n" +
                "18\n" +
                "20\n" +
                "25\n" +
                "30\n" +
                "35\n" +
                "40\n" +
                "50\n" +
                "60\n" +
                "4,536\n" +
                "398.2\n" +
                "22µm");

    }

    public String getTabsTooltips(String parent, String selector) {
        WebElement tabInfoTooltip = getWebDriver().findElement(By.cssSelector(selector));
        MouseUtils.mouseOver(tabInfoTooltip, getWebDriver());

        WebElement tooltipText = new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(parent + " .formulaTooltip")));

        String text = tooltipText.getText();

        return text;
    }

}