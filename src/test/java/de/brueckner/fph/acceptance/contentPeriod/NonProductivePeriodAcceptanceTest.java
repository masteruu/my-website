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
import org.testng.annotations.Test;

import java.util.List;

import static de.brueckner.fph.managers.ContentManager.CAMPAIGN_OVERVIEW_TABS_ANIMATION;
import static de.brueckner.fph.managers.ContentManager.CONTENT_NAME;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_LONG_TIME_FORMAT;

public class NonProductivePeriodAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(NonProductivePeriodAcceptanceTest.class);

    @Test
    public void nonProductivePeriodAcceptance() {
        logger.info("nonProductivePeriodAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-13 22:00:00");

        treeManager.openTree();
        treeManager.expandNode(0);

        //select a non productive period
        List<WebElement> treeElement = getWebDriver().findElements(By.cssSelector(".name.treeRow"));
        treeElement.get(3).click();
        contentManager.waitContentSpinner();

        //check name and time in content
        contentManager.checkContentPeriod(DateUtils.date("2016-04-13 08:52:00", DEFAULT_SHORT_DATE_FORMAT), DateUtils.date("2016-04-13 10:24:00", DEFAULT_SHORT_DATE_FORMAT));

        WebElement contentName = getWebDriver().findElement(By.cssSelector(CONTENT_NAME));
        Assert.assertEquals(contentName.getText(), "NON_PRODUCTIVE");

        //check section title
        WebElement info = getWebDriver().findElement(By.cssSelector(".sectionTitle"));
        Assert.assertEquals(info.getText().trim(), "INFO");

        //check non productive duration
        WebElement infoDuration = getWebDriver().findElement(By.cssSelector(".rollDuration"));
        Assert.assertEquals(infoDuration.getText().trim(), DateUtils.date("2016-04-13 08:52:42", DEFAULT_SHORT_DATE_LONG_TIME_FORMAT) + "\n" +
                "Start\n" +
                "01h 31min 44s\n" +
                "Duration\n" +
                DateUtils.date("2016-04-13 10:24:26", DEFAULT_SHORT_DATE_LONG_TIME_FORMAT) + "\n" +
                "End");

        //check graph content compacted
        List<WebElement> infoRow = getWebDriver().findElements(By.cssSelector(".barChartContainer .infoRow"));
        Assert.assertEquals(infoRow.get(0).getText(), "9.97 %\n" +
                "09min\n" +
                "Stabilizing\n" +
                "38.84 %\n" +
                "35min\n" +
                "Threading\n" +
                "3.20 %\n" +
                "02min\n" +
                "Purging\n" +
                "47.99 %\n" +
                "44min\n" +
                "Shutdown");

        //check graph content expanded
        WebElement expandBars = getWebDriver().findElement(By.cssSelector(".expandGraph"));
        expandBars.click();
        Utils.sleep(CAMPAIGN_OVERVIEW_TABS_ANIMATION);

        infoRow = getWebDriver().findElements(By.cssSelector(".barChartContainer .infoRow"));
        Assert.assertEquals(infoRow.get(1).getText(), "4.14 %\n" +
                "03min\n" +
                "CHR_THREAD\n" +
                "21.43 %\n" +
                "19min\n" +
                "MDO_THREAD\n" +
                "2.48 %\n" +
                "02min\n" +
                "TDO_THREAD\n" +
                "0.55 %\n" +
                "00min\n" +
                "TDO_SPEEDUP\n" +
                "7.69 %\n" +
                "07min\n" +
                "PUR_THREAD\n" +
                "1.35 %\n" +
                "01min\n" +
                "PUR_SPEEDUP\n" +
                "1.20 %\n" +
                "01min\n" +
                "CUTBACK\n" +
                "0.00 %\n" +
                "00min\n" +
                "UNDEFINED");

    }
}