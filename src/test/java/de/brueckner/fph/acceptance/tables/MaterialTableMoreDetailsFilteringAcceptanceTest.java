package de.brueckner.fph.acceptance.tables;

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
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static de.brueckner.fph.util.DateUtils.DEFAULT_LONG_DATE_FORMAT;

public class MaterialTableMoreDetailsFilteringAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(MaterialTableMoreDetailsFilteringAcceptanceTest.class);

    @Test
    public void materialTableMoreDetailsFilteringAcceptanceTest() {
        logger.info("materialTableMoreDetailsFilteringAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-12 23:00:00", "2016-04-13 22:00:00");

        //click on a roll and check if the selection is done ok
        timelineManager.clickRoll("roll:A160413064950:1460530190433:1460535116127");
        contentManager.waitContentSpinner();

        contentManager.openDetailedMaterialTabUngrouped();

        contentManager.materialTableFilters();

        //quality filter
        List<WebElement> filters = getWebDriver().findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("No result");
        WebElement filterActive = getWebDriver().findElement(By.cssSelector(".isFilterActiveDecorator.active"));
        WebElement result = getWebDriver().findElement(By.cssSelector(".ngCanvas"));
        Assert.assertEquals(result.getAttribute("style"), "height: 560px;");
        List<WebElement> footer = getWebDriver().findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(1).getText(), "Total Quantity 0 kg");


        List<WebElement> clearFilter = getWebDriver().findElements(By.cssSelector(".clearInput"));
        clearFilter.get(0).click();

        contentManager.materialTableOpenMenu();

        List<WebElement> customizeHeaders = getWebDriver().findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(2).click();
        List<WebElement> quality = getWebDriver().findElements(By.cssSelector(".ngColList li:nth-child(1)"));
        quality.get(1).click();

        //validity filter
        filters = getWebDriver().findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("No result");
        filterActive = getWebDriver().findElement(By.cssSelector(".isFilterActiveDecorator.active"));
        result = getWebDriver().findElement(By.cssSelector(".ngCanvas"));
        Assert.assertEquals(result.getAttribute("style"), "height: 560px;");
        footer = getWebDriver().findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(1).getText(), "Total Quantity 0 kg");
        clearFilter = getWebDriver().findElements(By.cssSelector(".clearInput"));
        clearFilter.get(0).click();

        List<WebElement> results = getWebDriver().findElements(By.cssSelector(".colt1"));
        Assert.assertEquals(results.get(31).getText().trim(), "Validity");
        Assert.assertEquals(results.get(32).getText().trim(), "Valid");
        Assert.assertEquals(results.get(34).getText().trim(), "Valid");
        Assert.assertEquals(results.get(36).getText().trim(), "Valid");
        Assert.assertEquals(results.get(38).getText().trim(), "Valid");
        Assert.assertEquals(results.get(40).getText().trim(), "Valid");
        Assert.assertEquals(results.get(42).getText().trim(), "Valid");
        Assert.assertEquals(results.get(44).getText().trim(), "Valid");
        Assert.assertEquals(results.get(46).getText().trim(), "Valid");
        filters = getWebDriver().findElements(By.cssSelector(".filterColumnInput"));
        footer = getWebDriver().findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(1).getText(), "Total Quantity 5,976.1 kg");
        filters.get(0).sendKeys("Val");

        Assert.assertEquals(results.get(31).getText().trim(), "Validity");
        Assert.assertEquals(results.get(32).getText().trim(), "Valid");
        Assert.assertEquals(results.get(34).getText().trim(), "Valid");
        Assert.assertEquals(results.get(36).getText().trim(), "Valid");
        Assert.assertEquals(results.get(38).getText().trim(), "Valid");
        Assert.assertEquals(results.get(40).getText().trim(), "Valid");
        Assert.assertEquals(results.get(42).getText().trim(), "Valid");
        Assert.assertEquals(results.get(44).getText().trim(), "Valid");
        Assert.assertEquals(results.get(46).getText().trim(), "Valid");
        clearFilter = getWebDriver().findElements(By.cssSelector(".clearInput"));
        clearFilter.get(0).click();
        contentManager.materialTableOpenMenu();
        customizeHeaders = getWebDriver().findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(2).click();
        List<WebElement> validity = getWebDriver().findElements(By.cssSelector(".ngColList li:nth-child(2)"));
        validity.get(1).click();

        //productivity filter
        filters = getWebDriver().findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("No result");
        filterActive = getWebDriver().findElement(By.cssSelector(".isFilterActiveDecorator.active"));
        result = getWebDriver().findElement(By.cssSelector(".ngCanvas"));
        Assert.assertEquals(result.getAttribute("style"), "height: 560px;");
        footer = getWebDriver().findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(1).getText(), "Total Quantity 0 kg");
        clearFilter = getWebDriver().findElements(By.cssSelector(".clearInput"));
        clearFilter.get(0).click();
        results = getWebDriver().findElements(By.cssSelector(".colt2"));
        Assert.assertEquals(results.get(31).getText().trim(), "Productivity");
        Assert.assertEquals(results.get(32).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(results.get(34).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(results.get(36).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(results.get(38).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(results.get(40).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(results.get(42).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(results.get(44).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(results.get(46).getText().trim(), "PRODUCTIVE");
        footer = getWebDriver().findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(1).getText(), "Total Quantity 5,976.1 kg");
        filters = getWebDriver().findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("rod");
        Assert.assertEquals(results.get(31).getText().trim(), "Productivity");
        Assert.assertEquals(results.get(32).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(results.get(34).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(results.get(36).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(results.get(38).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(results.get(40).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(results.get(42).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(results.get(44).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(results.get(46).getText().trim(), "PRODUCTIVE");
        clearFilter = getWebDriver().findElements(By.cssSelector(".clearInput"));
        clearFilter.get(0).click();
        contentManager.materialTableOpenMenu();
        customizeHeaders = getWebDriver().findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(2).click();
        List<WebElement> prod = getWebDriver().findElements(By.cssSelector(".ngColList li:nth-child(3)"));
        prod.get(1).click();

        //Dosing filter
        filters = getWebDriver().findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("No result");
        filterActive = getWebDriver().findElement(By.cssSelector(".isFilterActiveDecorator.active"));
        result = getWebDriver().findElement(By.cssSelector(".ngCanvas"));
        Assert.assertEquals(result.getAttribute("style"), "height: 560px;");
        footer = getWebDriver().findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(1).getText(), "Total Quantity 0 kg");
        clearFilter = getWebDriver().findElements(By.cssSelector(".clearInput"));
        clearFilter.get(0).click();
        results = getWebDriver().findElements(By.cssSelector(".colt3"));
        Assert.assertEquals(results.get(31).getText().trim(), "Dosing");
        Assert.assertEquals(results.get(32).getText().trim(), "SilSto.Dos003.CmpTotAct");
        Assert.assertEquals(results.get(34).getText().trim(), "SilSto.Dos002.CmpTotAct");
        Assert.assertEquals(results.get(36).getText().trim(), "SilMne.Dos002.CmpTotAct");
        Assert.assertEquals(results.get(38).getText().trim(), "SilCox002.Dos001.CmpTotAct");
        Assert.assertEquals(results.get(40).getText().trim(), "SilCox001.Dos001.CmpTotAct");
        Assert.assertEquals(results.get(42).getText().trim(), "SilCox001.Dos003.CmpTotAct");
        Assert.assertEquals(results.get(44).getText().trim(), "SilCox002.Dos003.CmpTotAct");
        Assert.assertEquals(results.get(46).getText().trim(), "SilCox001.Dos002.CmpTotAct");
        footer = getWebDriver().findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(1).getText(), "Total Quantity 5,976.1 kg");
        filters = getWebDriver().findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("SilSto.Dos003.CmpTotAct");
        Assert.assertEquals(results.get(31).getText().trim(), "Dosing");
        Assert.assertEquals(results.get(32).getText().trim(), "SilSto.Dos003.CmpTotAct");
        footer = getWebDriver().findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(1).getText(), "Total Quantity 3,069.7 kg");
        clearFilter = getWebDriver().findElements(By.cssSelector(".clearInput"));
        clearFilter.get(0).click();
        contentManager.materialTableOpenMenu();
        customizeHeaders = getWebDriver().findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(2).click();
        List<WebElement> dosing = getWebDriver().findElements(By.cssSelector(".ngColList li:nth-child(4)"));
        dosing.get(1).click();

        //extruder filter
        filters = getWebDriver().findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("No result");
        filterActive = getWebDriver().findElement(By.cssSelector(".isFilterActiveDecorator.active"));
        result = getWebDriver().findElement(By.cssSelector(".ngCanvas"));
        Assert.assertEquals(result.getAttribute("style"), "height: 560px;");
        footer = getWebDriver().findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(1).getText(), "Total Quantity 0 kg");
        clearFilter = getWebDriver().findElements(By.cssSelector(".clearInput"));
        clearFilter.get(0).click();
        results = getWebDriver().findElements(By.cssSelector(".colt4"));
        Assert.assertEquals(results.get(1).getText().trim(), "Extruder");
        Assert.assertEquals(results.get(2).getText().trim(), "Storage");
        Assert.assertEquals(results.get(4).getText().trim(), "Storage");
        Assert.assertEquals(results.get(6).getText().trim(), "Dosing Main Extrusion:");
        Assert.assertEquals(results.get(8).getText().trim(), "Dosing Coextrusion 2:");
        Assert.assertEquals(results.get(10).getText().trim(), "Dosing Coextrusion 1:");
        Assert.assertEquals(results.get(12).getText().trim(), "Dosing Coextrusion 1:");
        Assert.assertEquals(results.get(14).getText().trim(), "Dosing Coextrusion 2:");
        Assert.assertEquals(results.get(16).getText().trim(), "Dosing Coextrusion 1:");

        footer = getWebDriver().findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(1).getText(), "Total Quantity 5,976.1 kg");
        filters = getWebDriver().findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("stor");
        Assert.assertEquals(results.get(1).getText().trim(), "Extruder");
        Assert.assertEquals(results.get(2).getText().trim(), "Storage");
        Assert.assertEquals(results.get(4).getText().trim(), "Storage");
        Assert.assertEquals(results.get(6).getText().trim(), "Storage");

        footer = getWebDriver().findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(1).getText(), "Total Quantity 5,116.3 kg");

        clearFilter = getWebDriver().findElements(By.cssSelector(".clearInput"));
        clearFilter.get(0).click();

        contentManager.materialTableOpenMenu();
        customizeHeaders = getWebDriver().findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(2).click();
        WebElement extruder = getWebDriver().findElement(By.cssSelector(".ngColList li:nth-child(5)"));
        extruder.click();

        //roll id filter
        filters = getWebDriver().findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("No result");
        filterActive = getWebDriver().findElement(By.cssSelector(".isFilterActiveDecorator.active"));
        result = getWebDriver().findElement(By.cssSelector(".ngCanvas"));
        Assert.assertEquals(result.getAttribute("style"), "height: 560px;");
        footer = getWebDriver().findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(1).getText(), "Total Quantity 0 kg");
        clearFilter = getWebDriver().findElements(By.cssSelector(".clearInput"));
        clearFilter.get(0).click();
        results = getWebDriver().findElements(By.cssSelector(".colt5"));

        Assert.assertEquals(results.get(1).getText().trim(), "Roll ID");
        Assert.assertEquals(results.get(2).getText().trim(), "A 160413 1449");
        Assert.assertEquals(results.get(4).getText().trim(), "A 160413 1449");
        Assert.assertEquals(results.get(6).getText().trim(), "A 160413 1449");
        Assert.assertEquals(results.get(8).getText().trim(), "A 160413 1449");
        Assert.assertEquals(results.get(10).getText().trim(), "A 160413 1449");
        Assert.assertEquals(results.get(12).getText().trim(), "A 160413 1449");
        Assert.assertEquals(results.get(14).getText().trim(), "A 160413 1449");
        Assert.assertEquals(results.get(16).getText().trim(), "A 160413 1449");

        footer = getWebDriver().findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(1).getText(), "Total Quantity 5,976.1 kg");
        filters = getWebDriver().findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("A 160413 1449");
        Assert.assertEquals(results.get(1).getText().trim(), "Roll ID");
        Assert.assertEquals(results.get(2).getText().trim(), "A 160413 1449");
        Assert.assertEquals(results.get(4).getText().trim(), "A 160413 1449");
        Assert.assertEquals(results.get(6).getText().trim(), "A 160413 1449");
        Assert.assertEquals(results.get(8).getText().trim(), "A 160413 1449");
        Assert.assertEquals(results.get(10).getText().trim(), "A 160413 1449");
        Assert.assertEquals(results.get(12).getText().trim(), "A 160413 1449");
        Assert.assertEquals(results.get(14).getText().trim(), "A 160413 1449");
        Assert.assertEquals(results.get(16).getText().trim(), "A 160413 1449");
        contentManager.materialTableOpenMenu();
        customizeHeaders = getWebDriver().findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(2).click();
        WebElement rollI = getWebDriver().findElement(By.cssSelector(".ngColList li:nth-child(6)"));
        rollI.click();

        //Material set filter filter
        filters = getWebDriver().findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("No result");

        result = getWebDriver().findElement(By.cssSelector(".ngCanvas"));
        Assert.assertEquals(result.getAttribute("style"), "height: 560px;");
        footer = getWebDriver().findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(1).getText(), "Total Quantity 0 kg");

        clearFilter = getWebDriver().findElements(By.cssSelector(".clearInput"));
        clearFilter.get(0).click();

        results = getWebDriver().findElements(By.cssSelector(".colt6"));
        Assert.assertEquals(results.get(1).getText().trim(), "Material");
        Assert.assertEquals(results.get(2).getText().trim(), "*S1003(SH)");
        Assert.assertEquals(results.get(4).getText().trim(), "T38F(HD)");
        Assert.assertEquals(results.get(6).getText().trim(), "Undefined");
        Assert.assertEquals(results.get(8).getText().trim(), "#T30(FD)");
        Assert.assertEquals(results.get(10).getText().trim(), "#T30(FD)");
        Assert.assertEquals(results.get(12).getText().trim(), "Undefined");
        Assert.assertEquals(results.get(14).getText().trim(), "Undefined");
        Assert.assertEquals(results.get(16).getText().trim(), "Undefined");

        footer = getWebDriver().findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(1).getText(), "Total Quantity 5,976.1 kg");
        filters = getWebDriver().findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("#T30(FD)");


        results = getWebDriver().findElements(By.cssSelector(".colt6"));
        Assert.assertEquals(results.get(1).getText().trim(), "Material");
        Assert.assertEquals(results.get(2).getText().trim(), "#T30(FD)");
        Assert.assertEquals(results.get(4).getText().trim(), "#T30(FD)");

        footer = getWebDriver().findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(1).getText(), "Total Quantity 525.1 kg");

        contentManager.materialTableOpenMenu();
        customizeHeaders = getWebDriver().findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(2).click();
        WebElement material = getWebDriver().findElement(By.cssSelector(".ngColList li:nth-child(7)"));
        material.click();

        //quantity filter
        filters = getWebDriver().findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("325.3");
        List<WebElement> operators = getWebDriver().findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        WebElement operator = getWebDriver().findElement(By.cssSelector(".select2-results li:nth-child(2)"));
        operator.click();
        results = getWebDriver().findElements(By.cssSelector(".colt7"));
        Assert.assertEquals(results.get(1).getText().trim(), "Quantity[kg]");
        Assert.assertEquals(results.get(2).getText().trim(), "264.7");
        Assert.assertEquals(results.get(4).getText().trim(), "260.4");
        Assert.assertEquals(results.get(6).getText().trim(), "4.8");
        Assert.assertEquals(results.get(8).getText().trim(), "4.6");
        Assert.assertEquals(results.get(10).getText().trim(), "0.0");
        Assert.assertEquals(results.get(12).getText().trim(), "0.0");
        Assert.assertEquals(results.get(14).getText().trim(), "0.0");
        footer = getWebDriver().findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(1).getText(), "Total Quantity 534.4 kg");

        operators = getWebDriver().findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = getWebDriver().findElement(By.cssSelector(".select2-results li:nth-child(3)"));
        operator.click();
        filterActive = getWebDriver().findElement(By.cssSelector(".isFilterActiveDecorator.active"));
        results = getWebDriver().findElements(By.cssSelector(".colt7"));
        Assert.assertEquals(results.get(1).getText().trim(), "Quantity[kg]");
        Assert.assertEquals(results.get(2).getText().trim(), "325.3");
        Assert.assertEquals(results.get(4).getText().trim(), "264.7");
        Assert.assertEquals(results.get(6).getText().trim(), "260.4");
        Assert.assertEquals(results.get(8).getText().trim(), "4.8");
        Assert.assertEquals(results.get(10).getText().trim(), "4.6");
        Assert.assertEquals(results.get(12).getText().trim(), "0.0");
        Assert.assertEquals(results.get(14).getText().trim(), "0.0");
        footer = getWebDriver().findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(1).getText(), "Total Quantity 859.8 kg");
        operators = getWebDriver().findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = getWebDriver().findElement(By.cssSelector(".select2-results li:nth-child(4)"));
        operator.click();
        results = getWebDriver().findElements(By.cssSelector(".colt7"));
        Assert.assertEquals(results.get(1).getText().trim(), "Quantity[kg]");
        Assert.assertEquals(results.get(2).getText().trim(), "325.3");

        footer = getWebDriver().findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(1).getText(), "Total Quantity 325.3 kg");

        operators = getWebDriver().findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = getWebDriver().findElement(By.cssSelector(".select2-results li:nth-child(5)"));
        operator.click();
        results = getWebDriver().findElements(By.cssSelector(".colt7"));
        Assert.assertEquals(results.get(1).getText().trim(), "Quantity[kg]");
        Assert.assertEquals(results.get(2).getText().trim(), "3,069.8");
        Assert.assertEquals(results.get(4).getText().trim(), "2,046.5");
        Assert.assertEquals(results.get(6).getText().trim(), "325.3");
        footer = getWebDriver().findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(1).getText(), "Total Quantity 5,441.6 kg");


        operators = getWebDriver().findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = getWebDriver().findElement(By.cssSelector(".select2-results li:nth-child(6)"));
        operator.click();
        results = getWebDriver().findElements(By.cssSelector(".colt7"));
        Assert.assertEquals(results.get(1).getText().trim(), "Quantity[kg]");
        Assert.assertEquals(results.get(2).getText().trim(), "3,069.8");
        Assert.assertEquals(results.get(4).getText().trim(), "2,046.5");
        footer = getWebDriver().findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(1).getText(), "Total Quantity 5,116.3 kg");
        operators = getWebDriver().findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = getWebDriver().findElement(By.cssSelector(".select2-results li:nth-child(7)"));
        operator.click();
        results = getWebDriver().findElements(By.cssSelector(".colt7"));
        Assert.assertEquals(results.get(1).getText().trim(), "Quantity[kg]");
        Assert.assertEquals(results.get(2).getText().trim(), "3,069.8");
        Assert.assertEquals(results.get(4).getText().trim(), "2,046.5");
        Assert.assertEquals(results.get(6).getText().trim(), "264.7");
        Assert.assertEquals(results.get(8).getText().trim(), "260.4");
        Assert.assertEquals(results.get(10).getText().trim(), "4.8");
        Assert.assertEquals(results.get(12).getText().trim(), "4.6");
        Assert.assertEquals(results.get(14).getText().trim(), "0.0");
        contentManager.materialTableOpenMenu();
        customizeHeaders = getWebDriver().findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(2).click();
        WebElement quantity = getWebDriver().findElement(By.cssSelector(".ngColList li:nth-child(8)"));
        quantity.click();

        //Line status filter
        filters = getWebDriver().findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("No result");
        filterActive = getWebDriver().findElement(By.cssSelector(".isFilterActiveDecorator.active"));
        result = getWebDriver().findElement(By.cssSelector(".ngCanvas"));
        Assert.assertEquals(result.getAttribute("style"), "height: 560px;");
        footer = getWebDriver().findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(1).getText(), "Total Quantity 0 kg");
        clearFilter = getWebDriver().findElements(By.cssSelector(".clearInput"));
        clearFilter.get(0).click();
        results = getWebDriver().findElements(By.cssSelector(".colt8"));

        Assert.assertEquals(results.get(1).getText().trim(), "Line Status");
        Assert.assertEquals(results.get(2).getText().trim(), "PRODUCTION");
        Assert.assertEquals(results.get(4).getText().trim(), "PRODUCTION");
        Assert.assertEquals(results.get(6).getText().trim(), "PRODUCTION");
        Assert.assertEquals(results.get(8).getText().trim(), "PRODUCTION");
        Assert.assertEquals(results.get(10).getText().trim(), "PRODUCTION");
        Assert.assertEquals(results.get(12).getText().trim(), "PRODUCTION");
        Assert.assertEquals(results.get(14).getText().trim(), "PRODUCTION");
        Assert.assertEquals(results.get(16).getText().trim(), "PRODUCTION");

        footer = getWebDriver().findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(1).getText(), "Total Quantity 5,976.1 kg");
        filters = getWebDriver().findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("PRODUCTION");
        Assert.assertEquals(results.get(1).getText().trim(), "Line Status");
        Assert.assertEquals(results.get(2).getText().trim(), "PRODUCTION");
        Assert.assertEquals(results.get(4).getText().trim(), "PRODUCTION");
        Assert.assertEquals(results.get(6).getText().trim(), "PRODUCTION");
        Assert.assertEquals(results.get(8).getText().trim(), "PRODUCTION");
        Assert.assertEquals(results.get(10).getText().trim(), "PRODUCTION");
        Assert.assertEquals(results.get(12).getText().trim(), "PRODUCTION");
        Assert.assertEquals(results.get(14).getText().trim(), "PRODUCTION");
        Assert.assertEquals(results.get(16).getText().trim(), "PRODUCTION");
        contentManager.materialTableOpenMenu();
        customizeHeaders = getWebDriver().findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(2).click();
        WebElement lineS = getWebDriver().findElement(By.cssSelector(".ngColList li:nth-child(9)"));
        lineS.click();

        //start time filter
        List<WebElement> datepicker = getWebDriver().findElements(By.cssSelector(".datepickerCol"));
        datepicker.get(0).click();
        Utils.sleep(500);
        List<WebElement> save = getWebDriver().findElements(By.cssSelector(".dialogButton.dbSaveClose"));
        save.get(4).click();
        operators = getWebDriver().findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = getWebDriver().findElement(By.cssSelector(".select2-results li:nth-child(2)"));
        operator.click();
        filterActive = getWebDriver().findElement(By.cssSelector(".isFilterActiveDecorator.active"));
        results = getWebDriver().findElements(By.cssSelector(".colt9"));
        Assert.assertEquals(results.get(1).getText().trim(), "Start Time");
        Assert.assertEquals(results.get(2).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(results.get(4).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(results.get(6).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(results.get(8).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(results.get(10).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(results.get(12).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(results.get(14).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(results.get(16).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        operators = getWebDriver().findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = getWebDriver().findElement(By.cssSelector(".select2-results li:nth-child(3)"));
        operator.click();
        results = getWebDriver().findElements(By.cssSelector(".colt9"));
        Assert.assertEquals(results.get(1).getText().trim(), "Start Time");
        Assert.assertEquals(results.get(2).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(results.get(4).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(results.get(6).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(results.get(8).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(results.get(10).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(results.get(12).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(results.get(14).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(results.get(16).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        operators = getWebDriver().findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = getWebDriver().findElement(By.cssSelector(".select2-results li:nth-child(4)"));
        operator.click();
        result = getWebDriver().findElement(By.cssSelector(".ngCanvas"));
        Assert.assertEquals(result.getAttribute("style"), "height: 560px;");
        operators = getWebDriver().findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = getWebDriver().findElement(By.cssSelector(".select2-results li:nth-child(5)"));
        operator.click();
        result = getWebDriver().findElement(By.cssSelector(".ngCanvas"));
        Assert.assertEquals(result.getAttribute("style"), "height: 560px;");
        footer = getWebDriver().findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(1).getText(), "Total Quantity 0 kg");
        operators = getWebDriver().findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = getWebDriver().findElement(By.cssSelector(".select2-results li:nth-child(6)"));
        operator.click();
        result = getWebDriver().findElement(By.cssSelector(".ngCanvas"));
        Assert.assertEquals(result.getAttribute("style"), "height: 560px;");
        footer = getWebDriver().findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(1).getText(), "Total Quantity 0 kg");
        operators = getWebDriver().findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = getWebDriver().findElement(By.cssSelector(".select2-results li:nth-child(7)"));
        operator.click();
        results = getWebDriver().findElements(By.cssSelector(".colt9"));
        Assert.assertEquals(results.get(1).getText().trim(), "Start Time");
        Assert.assertEquals(results.get(2).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(results.get(4).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(results.get(6).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(results.get(8).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(results.get(10).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(results.get(12).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(results.get(14).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(results.get(16).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));

        contentManager.materialTableOpenMenu();
        customizeHeaders = getWebDriver().findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(2).click();

        quality = getWebDriver().findElements(By.cssSelector(".ngColList li:nth-child(1)"));
        quality.get(1).click();
        validity = getWebDriver().findElements(By.cssSelector(".ngColList li:nth-child(2)"));
        validity.get(1).click();
        prod = getWebDriver().findElements(By.cssSelector(".ngColList li:nth-child(3)"));
        prod.get(1).click();
        dosing = getWebDriver().findElements(By.cssSelector(".ngColList li:nth-child(4)"));
        dosing.get(1).click();
        extruder = getWebDriver().findElement(By.cssSelector(".ngColList li:nth-child(5)"));
        extruder.click();
        rollI = getWebDriver().findElement(By.cssSelector(".ngColList li:nth-child(6)"));
        rollI.click();
        material = getWebDriver().findElement(By.cssSelector(".ngColList li:nth-child(7)"));
        material.click();
        quantity = getWebDriver().findElement(By.cssSelector(".ngColList li:nth-child(8)"));
        quantity.click();
        lineS = getWebDriver().findElement(By.cssSelector(".ngColList li:nth-child(9)"));
        lineS.click();

        contentManager.materialTableOpenMenu();

        List<WebElement> filter2 = getWebDriver().findElements(By.cssSelector(".colt1"));
        Assert.assertEquals(filter2.get(31).getText().trim(), "Validity");
        Assert.assertEquals(filter2.get(32).getText().trim(), "Valid");
        Assert.assertEquals(filter2.get(34).getText().trim(), "Valid");

        List<WebElement> filter3 = getWebDriver().findElements(By.cssSelector(".colt2"));
        Assert.assertEquals(filter3.get(31).getText().trim(), "Productivity");
        Assert.assertEquals(filter3.get(32).getText().trim(), "PRODUCTIVE");
        Assert.assertEquals(filter3.get(34).getText().trim(), "PRODUCTIVE");

        List<WebElement> filter4 = getWebDriver().findElements(By.cssSelector(".colt3"));
        Assert.assertEquals(filter4.get(31).getText().trim(), "Dosing");
        Assert.assertEquals(filter4.get(32).getText().trim(), "SilCox001.Dos001.CmpTotAct");
        Assert.assertEquals(filter4.get(34).getText().trim(), "SilCox002.Dos001.CmpTotAct");

        List<WebElement> filter5 = getWebDriver().findElements(By.cssSelector(".colt4"));
        Assert.assertEquals(filter5.get(1).getText().trim(), "Extruder");
        Assert.assertEquals(filter5.get(2).getText().trim(), "Dosing Coextrusion 1:");
        Assert.assertEquals(filter5.get(4).getText().trim(), "Dosing Coextrusion 2:");

        List<WebElement> filter6 = getWebDriver().findElements(By.cssSelector(".colt5"));
        Assert.assertEquals(filter6.get(1).getText().trim(), "Roll ID");
        Assert.assertEquals(filter6.get(2).getText().trim(), "A 160413 1449");
        Assert.assertEquals(filter6.get(4).getText().trim(), "A 160413 1449");

        List<WebElement> filter7 = getWebDriver().findElements(By.cssSelector(".colt6"));
        Assert.assertEquals(filter7.get(1).getText().trim(), "Material");
        Assert.assertEquals(filter7.get(2).getText().trim(), "#T30(FD)");
        Assert.assertEquals(filter7.get(4).getText().trim(), "#T30(FD)");

        List<WebElement> filter8 = getWebDriver().findElements(By.cssSelector(".colt7"));
        Assert.assertEquals(filter8.get(1).getText().trim(), "Quantity[kg]");
        Assert.assertEquals(filter8.get(2).getText().trim(), "260.4");
        Assert.assertEquals(filter8.get(4).getText().trim(), "264.7");

        List<WebElement> filter9 = getWebDriver().findElements(By.cssSelector(".colt8"));
        Assert.assertEquals(filter9.get(1).getText().trim(), "Line Status");
        Assert.assertEquals(filter9.get(2).getText().trim(), "PRODUCTION");
        Assert.assertEquals(filter9.get(4).getText().trim(), "PRODUCTION");

        List<WebElement> filter10 = getWebDriver().findElements(By.cssSelector(".colt9"));
        Assert.assertEquals(filter10.get(1).getText().trim(), "Start Time");
        Assert.assertEquals(filter10.get(2).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(filter10.get(4).getText().trim(), DateUtils.date("2016-04-13 06:49:50", DEFAULT_LONG_DATE_FORMAT));

        List<WebElement> filter11 = getWebDriver().findElements(By.cssSelector(".colt10"));
        Assert.assertEquals(filter11.get(1).getText().trim(), "End Time");
        Assert.assertEquals(filter11.get(2).getText().trim(), DateUtils.date("2016-04-13 08:11:56", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(filter11.get(4).getText().trim(), DateUtils.date("2016-04-13 08:11:56", DEFAULT_LONG_DATE_FORMAT));

        List<WebElement> filter12 = getWebDriver().findElements(By.cssSelector(".colt11"));
        Assert.assertEquals(filter12.get(1).getText().trim(), "Duration");
        Assert.assertEquals(filter12.get(2).getText().trim(), "01:22:05");
        Assert.assertEquals(filter12.get(4).getText().trim(), "01:22:05");
    }

    @AfterMethod
    public void restoreApplicationSettings() throws IOException {
        getMongoUtils().executeScript(USER_SETTINGS_SCRIPT_NAME);
    }
}
