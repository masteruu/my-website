package de.brueckner.fph.acceptance.contentPeriod;

import com.sdl.selenium.web.utils.Utils;
import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
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

import java.util.List;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ContentManager.CAMPAIGN_OVERVIEW_TABS_ANIMATION;
import static de.brueckner.fph.managers.ContentManager.EFFICIENCY_TAB_TEXT;

public class PeriodEfficiencyTabAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(PeriodProductsTabAcceptanceTest.class);

    @Test
    public void periodEfficiencyTabAcceptanceTest() {
        logger.info("PeriodEfficiencyTabAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());


        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-13 22:00:00");

        contentManager.openTab(EFFICIENCY_TAB_TEXT);
        contentManager.waitContentSpinner();

        //check values and labels
        List<WebElement> numberVal = getWebDriver().findElements(By.cssSelector(".numberVal"));
        Assert.assertEquals(numberVal.get(0).getText().trim(), "101,791 kg");
        Assert.assertEquals(numberVal.get(4).getText().trim(), "1,370 kg");
        Assert.assertEquals(numberVal.get(5).getText().trim(), "1,370 kg");
        Assert.assertEquals(numberVal.get(6).getText().trim(), "0 kg");

        List<WebElement> label = getWebDriver().findElements(By.cssSelector(".label"));
        Assert.assertEquals(label.get(0).getText().trim(), "Total Material");
        Assert.assertEquals(label.get(2).getText().trim(), "Pending");
        Assert.assertEquals(label.get(3).getText().trim(), "Good Roll");
        Assert.assertEquals(label.get(4).getText().trim(), "Total Time");
        Assert.assertEquals(label.get(6).getText().trim(), "Production");

        //efficiency tooltip
        //for yield
        String tooltipText = getFormulaTooltips(".tab-content > div > div > div:nth-child(1) .efficiencyPeriodTitle > span.titleElement > span", 1);
        Assert.assertEquals(tooltipText, "99,982\n" +
                "kg, Good Roll\n" +
                "+\n" +
                "418\n" +
                "kg, Pending\n" +
                "101,791\n" +
                "kg, Total Material\n" +
                "= 99\n" +
                "% Yield\n" +
                "* All weights calculated from dosing scales\n" +
                "Quality Perspective:\n" +
                "Good Roll - Rolls which fulfilled quality specifications\n" +
                "Pending - Rolls not yet qualified");

        //for uptime
        tooltipText = getFormulaTooltips(".tab-content > div > div > div:nth-child(2) .efficiencyPeriodTitle > span.titleElement > span", 2);
        Assert.assertEquals(tooltipText, "22h 28min\n" +
                "in production\n" +
                "1d 00h 00min\n" +
                "Total Time\n" +
                "= 93.6\n" +
                "% Uptime");

        //efficiency bars and content
        List<WebElement> infoRow = getWebDriver().findElements(By.cssSelector(".infoRow"));
        Assert.assertEquals(infoRow.get(0).getText().trim(), "1.35 %\n" +
                "1,370 kg\n" +
                "Recyclable\n" +
                "0.00 %\n" +
                "0 kg\n" +
                "Not recyclable");
        Assert.assertEquals(infoRow.get(2).getText().trim(), "0.64 %\n" +
                "09min\n" +
                "Stabilizing\n" +
                "2.47 %\n" +
                "35min\n" +
                "Threading\n" +
                "0.20 %\n" +
                "02min\n" +
                "Purging\n" +
                "3.06 %\n" +
                "44min\n" +
                "Shutdown");

        //expand uptime and check content
        WebElement expandGraph = getWebDriver().findElement(By.cssSelector(".expandGraph"));
        expandGraph.click();
        Utils.sleep(CAMPAIGN_OVERVIEW_TABS_ANIMATION);

        infoRow = getWebDriver().findElements(By.cssSelector(".infoRow"));
        Assert.assertEquals(infoRow.get(3).getText().trim(), "0.26 %\n" +
                "03min\n" +
                "CHR_THREAD\n" +
                "1.37 %\n" +
                "19min\n" +
                "MDO_THREAD\n" +
                "0.16 %\n" +
                "02min\n" +
                "TDO_THREAD\n" +
                "0.03 %\n" +
                "00min\n" +
                "TDO_SPEEDUP\n" +
                "0.49 %\n" +
                "07min\n" +
                "PUR_THREAD\n" +
                "0.09 %\n" +
                "01min\n" +
                "PUR_SPEEDUP\n" +
                "0.08 %\n" +
                "01min\n" +
                "CUTBACK\n" +
                "0.00 %\n" +
                "00min\n" +
                "UNDEFINED");

        //compact uptime
        List<WebElement> closeSubChart = getWebDriver().findElements(By.cssSelector(".closeSubChart"));
        closeSubChart.get(1).click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".expandGraph")));


    }

    public String getFormulaTooltips(String selector, int index) {
        WebElement tabInfoTooltip = getWebDriver().findElement(By.cssSelector(selector));
        MouseUtils.mouseOver(tabInfoTooltip, getWebDriver());

        WebElement tooltipText = new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".tab-content.scrollbar > div > div > div:nth-child(" + index + ") > div > div.efficiencyPeriodTitle > span.titleElement > span > div")));

        String text = tooltipText.getText();

        return text;
    }

}
