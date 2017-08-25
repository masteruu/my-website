/*
package de.brueckner.fph.acceptance.tables;

import com.sdl.selenium.web.utils.Utils;
import de.brueckner.fph.oldtests.acceptance.appConfig.AbstractAcceptanceTest;
import de.brueckner.fph.oldtests.acceptance.appConfig.LoginCredentials;
import de.brueckner.fph.oldtests.util.ActionsUtils;
import de.brueckner.fph.oldtests.util.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class MaterialTableMoreDetailsSortingAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(MaterialTableMoreDetailsSortingAcceptanceTest.class);

    @Test
    public void materialTableMoreDetailsSortingAcceptanceTest() {
        logger.info("materialTableMoreDetailsSortingAcceptanceTest");
        ActionsUtils.loginPage(LoginCredentials.USER_DEV, LoginCredentials.PASSWORD);

        //setting the date picker to a specific date
        dateUtils.setDatePicker("2016-04-12 23:00:00", "2016-04-13 22:00:00");

        //click on a roll and check if the selection is done ok
        ActionsUtils.clickRoll("roll:A160413064950:1460530190433:1460535116127");
        ActionsUtils.waitSpinner();
        WebElement rollId = driver.findElement(By.cssSelector(".rollCampaignName"));
        Assert.assertEquals(rollId.getText(), "A 160413 1449");
        WebElement materialTab = driver.findElement(By.cssSelector(".mainContentTabs li:nth-child(4)"));
        materialTab.click();
        ActionsUtils.waitSpinner();
        WebElement viewMoreButton = driver.findElement(By.cssSelector(".viewMoreButton "));
        viewMoreButton.click();
        ActionsUtils.waitSpinner();
        List<WebElement> closeGroup = driver.findElements(By.cssSelector(".ngRemoveGroup"));
        closeGroup.get(2).click();
        Utils.sleep(500);
        closeGroup.get(3).click();
        Utils.sleep(1000);

        //validity sorting
        List<WebElement> sortedElements = driver.findElements(By.cssSelector(".colt1"));
        sortedElements.get(30).click();
        Assert.assertEquals(sortedElements.get(30).getText().trim(), "Validity");
        Assert.assertEquals(sortedElements.get(32).getText().trim(), "Valid");
        Assert.assertEquals(sortedElements.get(34).getText().trim(), "Valid");
        Assert.assertEquals(sortedElements.get(36).getText().trim(), "Valid");
        Assert.assertEquals(sortedElements.get(38).getText().trim(), "Valid");
        Assert.assertEquals(sortedElements.get(40).getText().trim(), "Valid");
        Assert.assertEquals(sortedElements.get(42).getText().trim(), "Valid");
        Assert.assertEquals(sortedElements.get(44).getText().trim(), "Valid");
        Assert.assertEquals(sortedElements.get(46).getText().trim(), "Valid");
        sortedElements.get(30).click();
        Assert.assertEquals(sortedElements.get(30).getText().trim(), "Validity");
        Assert.assertEquals(sortedElements.get(32).getText().trim(), "Valid");
        Assert.assertEquals(sortedElements.get(34).getText().trim(), "Valid");
        Assert.assertEquals(sortedElements.get(36).getText().trim(), "Valid");
        Assert.assertEquals(sortedElements.get(38).getText().trim(), "Valid");
        Assert.assertEquals(sortedElements.get(40).getText().trim(), "Valid");
        Assert.assertEquals(sortedElements.get(42).getText().trim(), "Valid");
        Assert.assertEquals(sortedElements.get(44).getText().trim(), "Valid");
        Assert.assertEquals(sortedElements.get(46).getText().trim(), "Valid");

        //productivity sorting
        sortedElements = driver.findElements(By.cssSelector(".colt2"));
        sortedElements.get(30).click();
        Assert.assertEquals(sortedElements.get(30).getText().trim(), "Productivity");
        Assert.assertEquals(sortedElements.get(32).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(sortedElements.get(34).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(sortedElements.get(36).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(sortedElements.get(38).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(sortedElements.get(40).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(sortedElements.get(42).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(sortedElements.get(44).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(sortedElements.get(46).getText().trim(), "PRODUCTIVE");
        sortedElements.get(30).click();
        Assert.assertEquals(sortedElements.get(30).getText().trim(), "Productivity");
        Assert.assertEquals(sortedElements.get(32).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(sortedElements.get(34).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(sortedElements.get(36).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(sortedElements.get(38).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(sortedElements.get(40).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(sortedElements.get(42).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(sortedElements.get(44).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(sortedElements.get(46).getText().trim(), "PRODUCTIVE");

        //dosing sorting
        sortedElements = driver.findElements(By.cssSelector(".colt3"));
        sortedElements.get(30).click();
        Assert.assertEquals(sortedElements.get(30).getText().trim(), "Dosing");
        Assert.assertEquals(sortedElements.get(32).getText().trim(), "SilCox001.Dos001.CmpTotAct");
        Assert.assertEquals(sortedElements.get(34).getText().trim(), "SilCox001.Dos002.CmpTotAct");
        Assert.assertEquals(sortedElements.get(36).getText().trim(), "SilCox001.Dos003.CmpTotAct");
        Assert.assertEquals(sortedElements.get(38).getText().trim(), "SilCox001.Dos004.CmpTotAct");
        Assert.assertEquals(sortedElements.get(40).getText().trim(), "SilCox002.Dos001.CmpTotAct");
        Assert.assertEquals(sortedElements.get(42).getText().trim(), "SilCox002.Dos002.CmpTotAct");
        Assert.assertEquals(sortedElements.get(44).getText().trim(), "SilCox002.Dos003.CmpTotAct");
        Assert.assertEquals(sortedElements.get(46).getText().trim(), "SilCox002.Dos004.CmpTotAct");
        sortedElements.get(30).click();
        Assert.assertEquals(sortedElements.get(30).getText().trim(), "Dosing");
        Assert.assertEquals(sortedElements.get(32).getText().trim(), "SilSto.Dos003.CmpTotAct");
        Assert.assertEquals(sortedElements.get(34).getText().trim(), "SilSto.Dos002.CmpTotAct");
        Assert.assertEquals(sortedElements.get(36).getText().trim(), "SilSto.Dos001.CmpTotAct");
        Assert.assertEquals(sortedElements.get(38).getText().trim(), "SilMne.Dos004.CmpTotAct");
        Assert.assertEquals(sortedElements.get(40).getText().trim(), "SilMne.Dos003.CmpTotAct");
        Assert.assertEquals(sortedElements.get(42).getText().trim(), "SilMne.Dos002.CmpTotAct");
        Assert.assertEquals(sortedElements.get(44).getText().trim(), "SilCox002.Dos004.CmpTotAct");
        Assert.assertEquals(sortedElements.get(46).getText().trim(), "SilCox002.Dos003.CmpTotAct");

        //extruder sorting
        sortedElements = driver.findElements(By.cssSelector(".colt4"));
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Extruder");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "Dosing Coextrusion 1:");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "Dosing Coextrusion 1:");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "Dosing Coextrusion 1:");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "Dosing Coextrusion 1:");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "Dosing Coextrusion 2:");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "Dosing Coextrusion 2:");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "Dosing Coextrusion 2:");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "Dosing Coextrusion 2:");
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Extruder");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "Storage");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "Storage");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "Storage");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "Dosing Main Extrusion:");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "Dosing Main Extrusion:");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "Dosing Main Extrusion:");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "Dosing Coextrusion 2:");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "Dosing Coextrusion 2:");

        //roll id sorting
        sortedElements = driver.findElements(By.cssSelector(".colt5"));
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Roll ID");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "A 160413 1449");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "A 160413 1449");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "A 160413 1449");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "A 160413 1449");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "A 160413 1449");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "A 160413 1449");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "A 160413 1449");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "A 160413 1449");
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Roll ID");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "A 160413 1449");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "A 160413 1449");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "A 160413 1449");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "A 160413 1449");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "A 160413 1449");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "A 160413 1449");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "A 160413 1449");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "A 160413 1449");

        //Material sorting
        sortedElements = driver.findElements(By.cssSelector(".colt6"));
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Material");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "ABPP936");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "#T30(FD)");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "Undefined");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "Undefined");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "#T30(FD)");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "dirty");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "Undefined");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "Undefined");

        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Material");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "#T30(FD)");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "#T30(FD)");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "#T30H(ZJG)");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "*S1003(SH)");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "ABPP936");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "dirty");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "T38F(HD)");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "Undefined");

        //quantity sorting
        sortedElements = driver.findElements(By.cssSelector(".colt7"));
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Quantity[kg]");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "0.0");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "0.0");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "0.0");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "0.0");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "0.0");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "0.0");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "0.0");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "4.6");
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Quantity[kg]");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "3,069.8");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "2,046.5");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "325.3");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "264.7");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "260.4");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "4.8");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "4.6");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "0.0");

        //Line Status sorting
        sortedElements = driver.findElements(By.cssSelector(".colt8"));
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Line Status");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "PRODUCTION");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "PRODUCTION");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "PRODUCTION");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "PRODUCTION");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "PRODUCTION");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "PRODUCTION");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "PRODUCTION");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "PRODUCTION");
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Line Status");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "PRODUCTION");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "PRODUCTION");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "PRODUCTION");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "PRODUCTION");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "PRODUCTION");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "PRODUCTION");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "PRODUCTION");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "PRODUCTION");

        //start time sorting
        sortedElements = driver.findElements(By.cssSelector(".colt9"));
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Start Time");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(4).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(6).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(8).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(10).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(12).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(14).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(16).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Start Time");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(4).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(6).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(8).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(10).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(12).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(14).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(16).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));

        //end time sorting
        sortedElements = driver.findElements(By.cssSelector(".colt10"));
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "End Time");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), DateUtils.date("2016-04-13 08:11:56", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE));
        Assert.assertEquals(sortedElements.get(4).getText().trim(), DateUtils.date("2016-04-13 08:11:56", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE));
        Assert.assertEquals(sortedElements.get(6).getText().trim(), DateUtils.date("2016-04-13 08:11:56", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE));
        Assert.assertEquals(sortedElements.get(8).getText().trim(), DateUtils.date("2016-04-13 08:11:56", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE));
        Assert.assertEquals(sortedElements.get(10).getText().trim(), DateUtils.date("2016-04-13 08:11:56", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE));
        Assert.assertEquals(sortedElements.get(12).getText().trim(), DateUtils.date("2016-04-13 08:11:56", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE));
        Assert.assertEquals(sortedElements.get(14).getText().trim(), DateUtils.date("2016-04-13 08:11:56", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE));
        Assert.assertEquals(sortedElements.get(16).getText().trim(), DateUtils.date("2016-04-13 08:11:56", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE));
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "End Time");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), DateUtils.date("2016-04-13 08:11:56", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE));
        Assert.assertEquals(sortedElements.get(4).getText().trim(), DateUtils.date("2016-04-13 08:11:56", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE));
        Assert.assertEquals(sortedElements.get(6).getText().trim(), DateUtils.date("2016-04-13 08:11:56", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE));
        Assert.assertEquals(sortedElements.get(8).getText().trim(), DateUtils.date("2016-04-13 08:11:56", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE));
        Assert.assertEquals(sortedElements.get(10).getText().trim(), DateUtils.date("2016-04-13 08:11:56", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE));
        Assert.assertEquals(sortedElements.get(12).getText().trim(), DateUtils.date("2016-04-13 08:11:56", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE));
        Assert.assertEquals(sortedElements.get(14).getText().trim(), DateUtils.date("2016-04-13 08:11:56", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE));
        Assert.assertEquals(sortedElements.get(16).getText().trim(), DateUtils.date("2016-04-13 08:11:56", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE));

        //duration sorting
        sortedElements = driver.findElements(By.cssSelector(".colt11"));
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Duration");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "01:22:05");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "01:22:05");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "01:22:05");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "01:22:05");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "01:22:05");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "01:22:05");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "01:22:05");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "01:22:05");
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Duration");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "01:22:05");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "01:22:05");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "01:22:05");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "01:22:05");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "01:22:05");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "01:22:05");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "01:22:05");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "01:22:05");

    }

    @AfterMethod
    public void restoreApplicationSettings() throws IOException {
        getMongoUtils().executeScript(USER_SETTINGS_SCRIPT_NAME);
    }
}*/
