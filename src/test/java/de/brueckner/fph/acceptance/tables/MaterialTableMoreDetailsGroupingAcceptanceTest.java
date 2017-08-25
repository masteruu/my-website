package de.brueckner.fph.acceptance.tables;


import com.sdl.selenium.web.utils.Utils;
import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
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
import static de.brueckner.fph.managers.ContentManager.MATERIAL_TAB_TEXT;
import static de.brueckner.fph.managers.ContentManager.SHOW_DETAILS_MATERIAL_TABLE;
import static de.brueckner.fph.util.DateUtils.DEFAULT_LONG_DATE_FORMAT;

public class MaterialTableMoreDetailsGroupingAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(MaterialTableMoreDetailsGroupingAcceptanceTest.class);

    @Test
    public void materialTableMoreDetailsGroupingAcceptance() {
        logger.info("materialTableMoreDetailsGroupingAcceptance");

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-12 23:00:00", "2016-04-13 22:00:00");

        //click on a roll and check if the selection is done ok
        timelineManager.clickRoll("roll:A160413064950:1460530190433:1460535116127");
        contentManager.waitContentSpinner();

        contentManager.openTab(MATERIAL_TAB_TEXT);

        logger.debug("Wait for show details button to be clickable");
        WebDriverWait wait = new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS);
        WebElement showDetails = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(SHOW_DETAILS_MATERIAL_TABLE)));
        showDetails.click();
        contentManager.waitContentSpinner();

        List<WebElement> expandNodes = getWebDriver().findElements(By.cssSelector(".ngAggArrowCollapsed"));
        expandNodes.get(0).click();
        Utils.sleep(500);
        expandNodes = getWebDriver().findElements(By.cssSelector(".ngAggArrowCollapsed"));
        expandNodes.get(0).click();
        Utils.sleep(500);
        expandNodes = getWebDriver().findElements(By.cssSelector(".ngAggArrowCollapsed"));
        expandNodes.get(0).click();
        Utils.sleep(500);

        List<WebElement> tableMainRows = getWebDriver().findElements(By.cssSelector(".ngAggregate"));
        Assert.assertEquals(tableMainRows.get(0).getText(), "*S1003(SH)3,069.7 kg");
        Assert.assertEquals(tableMainRows.get(1).getText(), "PRODUCTIVE3,069.7 kg");
        Assert.assertEquals(tableMainRows.get(2).getText(), "T38F(HD)2,046.5 kg");
        Assert.assertEquals(tableMainRows.get(3).getText(), "PRODUCTIVE2,046.5 kg");
        Assert.assertEquals(tableMainRows.get(4).getText(), "#T30(FD)525.1 kg");
        Assert.assertEquals(tableMainRows.get(5).getText(), "PRODUCTIVE525.1 kg");
        Assert.assertEquals(tableMainRows.get(6).getText(), "Undefined334.6 kg");

        List<WebElement> tableExpandedRows = getWebDriver().findElements(By.cssSelector(".ngRow"));
        Assert.assertEquals(tableExpandedRows.get(14).getText().trim(), "Good Roll\n" +
                "  Valid\n" +
                "  SilSto.Dos003.CmpTotAct\n" +
                "  Storage\n" +
                "  A 160413 1449\n" +
                "  3,069.8\n" +
                "  PRODUCTION\n" +
                "  " + DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT) + "\n" +
                "  " + DateUtils.date("2016-04-13 08:11:56", DEFAULT_LONG_DATE_FORMAT) + "\n" +
                "01:22:05");

        Assert.assertEquals(tableExpandedRows.get(15).getText().trim(), "Good Roll\n" +
                "  Valid\n" +
                "  SilSto.Dos002.CmpTotAct\n" +
                "  Storage\n" +
                "  A 160413 1449\n" +
                "  2,046.5\n" +
                "  PRODUCTION\n" +
                "  " + DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT) + "\n" +
                "  " + DateUtils.date("2016-04-13 08:11:56", DEFAULT_LONG_DATE_FORMAT) + "\n" +
                "01:22:05");

        Assert.assertEquals(tableExpandedRows.get(16).getText().trim(), "Good Roll\n" +
                "  Valid\n" +
                "  SilCox002.Dos001.CmpTotAct\n" +
                "  Dosing Coextrusion 2:\n" +
                "  A 160413 1449\n" +
                "  264.7\n" +
                "  PRODUCTION\n" +
                "  " + DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT) + "\n" +
                "  " + DateUtils.date("2016-04-13 08:11:56", DEFAULT_LONG_DATE_FORMAT) + "\n" +
                "01:22:05");
        Assert.assertEquals(tableExpandedRows.get(17).getText().trim(), "Good Roll\n" +
                "  Valid\n" +
                "  SilCox001.Dos001.CmpTotAct\n" +
                "  Dosing Coextrusion 1:\n" +
                "  A 160413 1449\n" +
                "  260.4\n" +
                "  PRODUCTION\n" +
                "  " + DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT) + "\n" +
                "  " + DateUtils.date("2016-04-13 08:11:56", DEFAULT_LONG_DATE_FORMAT) + "\n" +
                "01:22:05");

        List<WebElement> tableColapsedRows = getWebDriver().findElements(By.cssSelector(".ngAggArrowExpanded"));
        tableColapsedRows.get(0).click();
        Utils.sleep(500);
        tableColapsedRows = getWebDriver().findElements(By.cssSelector(".ngAggArrowExpanded"));
        tableColapsedRows.get(0).click();
        Utils.sleep(500);
        tableColapsedRows = getWebDriver().findElements(By.cssSelector(".ngAggArrowExpanded"));
        tableColapsedRows.get(0).click();
        Utils.sleep(500);
        tableColapsedRows = getWebDriver().findElements(By.cssSelector(".ngAggArrowExpanded"));
        tableColapsedRows.get(0).click();
        Utils.sleep(500);
        tableColapsedRows = getWebDriver().findElements(By.cssSelector(".ngAggArrowExpanded"));
        tableColapsedRows.get(0).click();
        Utils.sleep(500);
        tableColapsedRows = getWebDriver().findElements(By.cssSelector(".ngAggArrowExpanded"));
        tableColapsedRows.get(0).click();
        Utils.sleep(500);
        tableColapsedRows = getWebDriver().findElements(By.cssSelector(".ngAggArrowExpanded"));
        tableColapsedRows.get(0).click();
        Utils.sleep(500);

        tableMainRows = getWebDriver().findElements(By.cssSelector(".ngAggregate"));
        Assert.assertEquals(tableMainRows.get(0).getText(), "*S1003(SH)3,069.7 kg");
        Assert.assertEquals(tableMainRows.get(1).getText(), "T38F(HD)2,046.5 kg");
        Assert.assertEquals(tableMainRows.get(2).getText(), "#T30(FD)525.1 kg");
        Assert.assertEquals(tableMainRows.get(3).getText(), "Undefined334.6 kg");
        Assert.assertEquals(tableMainRows.get(4).getText(), "#T30H(ZJG)0 kg");
        Assert.assertEquals(tableMainRows.get(5).getText(), "dirty0 kg");
        Assert.assertEquals(tableMainRows.get(6).getText(), "ABPP9360 kg");

        List<WebElement> closeGroup = getWebDriver().findElements(By.cssSelector(".ngRemoveGroup"));
        closeGroup.get(2).click();
        Utils.sleep(800);
        expandNodes = getWebDriver().findElements(By.cssSelector(".ngAggArrowCollapsed"));
        expandNodes.get(0).click();

        tableExpandedRows = getWebDriver().findElements(By.cssSelector(".ngRow"));
        Assert.assertEquals(tableExpandedRows.get(14).getText().trim(), "Good Roll\n" +
                "  Valid\n" +
                "  SilSto.Dos003.CmpTotAct\n" +
                "  Storage\n" +
                "  A 160413 1449\n" +
                "  *S1003(SH)\n" +
                "  3,069.8\n" +
                "  PRODUCTION\n" +
                "  " + DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT) + "\n" +
                "  " + DateUtils.date("2016-04-13 08:11:56", DEFAULT_LONG_DATE_FORMAT) + "\n" +
                "01:22:05");

    }

    @AfterMethod
    public void restoreApplicationSettings() throws IOException {
        getMongoUtils().executeScript(USER_SETTINGS_SCRIPT_NAME);
    }
}
