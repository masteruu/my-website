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

public class RollsTableFilteringAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(RollsTableFilteringAcceptanceTest.class);

    @Test
    public void rollsTableFilteringAcceptance() {
        logger.info("rollsTableFilteringAcceptance");
        ActionsUtils.loginPage(LoginCredentials.USER_DEV, LoginCredentials.PASSWORD);

        dateUtils.setDatePicker("2016-04-12 11:47:00", "2016-04-13 10:47:00");

        //click on a campaign and check if the selection is done ok
        ActionsUtils.clickCampaign("campaign:160412114726310:1460461646310:1460511701759");
        ActionsUtils.waitSpinner();

        WebElement rolls = driver.findElement(By.cssSelector(".mainContentTabs li:nth-child(2)"));
        rolls.click();
        ActionsUtils.waitSpinner();

        WebElement menu = driver.findElement(By.cssSelector(".stripes"));
        menu.click();

        List<WebElement> showFilters = driver.findElements(By.cssSelector(".tableMenu li:nth-child(1)"));
        showFilters.get(0).click();
        Utils.sleep(1000);
        //visual quality filter
        List<WebElement> filters = driver.findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("No result");

        WebElement filterActive = driver.findElement(By.cssSelector(".isFilterActiveDecorator.active"));
        List<WebElement> clearFilter = driver.findElements(By.cssSelector(".clearInput"));
        clearFilter.get(0).click();
        filters = driver.findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("good");

        Utils.sleep(1000);
        List<WebElement> row0 = driver.findElements(By.cssSelector(".colt0"));
        Assert.assertEquals(row0.get(1).getText().trim(), "Visual Quality");
        Assert.assertEquals(row0.get(2).getText().trim(), "Good Roll");
        Assert.assertEquals(row0.get(4).getText().trim(), "Good Roll");
        Assert.assertEquals(row0.get(6).getText().trim(), "Good Roll");
        Assert.assertEquals(row0.get(8).getText().trim(), "Good Roll");
        Assert.assertEquals(row0.get(10).getText().trim(), "Good Roll");
        Assert.assertEquals(row0.get(12).getText().trim(), "Good Roll");
        Assert.assertEquals(row0.get(14).getText().trim(), "Good Roll");
        Assert.assertEquals(row0.get(16).getText().trim(), "Good Roll");
        menu = driver.findElement(By.cssSelector(".stripes"));
        menu.click();
        List<WebElement> customizeHeaders = driver.findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(0).click();
        WebElement visQuality = driver.findElement(By.cssSelector(".ngColList li:nth-child(1)"));
        visQuality.click();

        //lab quality filter
        filters = driver.findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("No result");
        Utils.sleep(1000);
        filterActive = driver.findElement(By.cssSelector(".isFilterActiveDecorator.active"));
        clearFilter = driver.findElements(By.cssSelector(".clearInput"));
        clearFilter.get(0).click();
        filters = driver.findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("goo");
        List<WebElement> row1 = driver.findElements(By.cssSelector(".colt1"));
        Assert.assertEquals(row1.get(1).getText().trim(), "Lab Quality");
        Assert.assertEquals(row1.get(2).getText().trim(), "Good Roll");
        clearFilter = driver.findElements(By.cssSelector(".clearInput"));
        clearFilter.get(0).click();
        menu = driver.findElement(By.cssSelector(".stripes"));
        menu.click();
        customizeHeaders = driver.findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(0).click();
        WebElement labQuality = driver.findElement(By.cssSelector(".ngColList li:nth-child(2)"));
        labQuality.click();

        //ID filter
        filters = driver.findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("No result");
        filterActive = driver.findElement(By.cssSelector(".isFilterActiveDecorator.active"));
        clearFilter = driver.findElements(By.cssSelector(".clearInput"));
        clearFilter.get(0).click();
        filters = driver.findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("231");
        List<WebElement> row2 = driver.findElements(By.cssSelector(".colt2"));
        Assert.assertEquals(row2.get(1).getText().trim(), "ID");
        Assert.assertEquals(row2.get(2).getText().trim(), "A 160412 2313");
        clearFilter = driver.findElements(By.cssSelector(".clearInput"));
        clearFilter.get(0).click();
        menu = driver.findElement(By.cssSelector(".stripes"));
        menu.click();
        customizeHeaders = driver.findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(0).click();
        WebElement id = driver.findElement(By.cssSelector(".ngColList li:nth-child(3)"));
        id.click();

        //T. Inside filter
        filters = driver.findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("No result");
        filterActive = driver.findElement(By.cssSelector(".isFilterActiveDecorator.active"));
        clearFilter = driver.findElements(By.cssSelector(".clearInput"));
        clearFilter.get(0).click();
        filters = driver.findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("on");
        List<WebElement> row3 = driver.findElements(By.cssSelector(".colt3"));
        Assert.assertEquals(row3.get(1).getText().trim(), "T. Inside");
        Assert.assertEquals(row3.get(2).getText().trim(), "None");
        Assert.assertEquals(row3.get(4).getText().trim(), "None");
        Assert.assertEquals(row3.get(6).getText().trim(), "None");
        Assert.assertEquals(row3.get(8).getText().trim(), "None");
        Assert.assertEquals(row3.get(10).getText().trim(), "None");
        Assert.assertEquals(row3.get(12).getText().trim(), "None");
        Assert.assertEquals(row3.get(14).getText().trim(), "None");
        Assert.assertEquals(row3.get(16).getText().trim(), "None");
        menu = driver.findElement(By.cssSelector(".stripes"));
        menu.click();
        customizeHeaders = driver.findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(0).click();
        WebElement tInside = driver.findElement(By.cssSelector(".ngColList li:nth-child(4)"));
        tInside.click();

        //T. Outside filter
        filters = driver.findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("No result");
        filterActive = driver.findElement(By.cssSelector(".isFilterActiveDecorator.active"));
        clearFilter = driver.findElements(By.cssSelector(".clearInput"));
        clearFilter.get(0).click();
        filters = driver.findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("on");
        List<WebElement> row4 = driver.findElements(By.cssSelector(".colt4"));
        Assert.assertEquals(row4.get(1).getText().trim(), "T. Outside");
        Assert.assertEquals(row4.get(2).getText().trim(), "None");
        Assert.assertEquals(row4.get(4).getText().trim(), "None");
        Assert.assertEquals(row4.get(6).getText().trim(), "None");
        Assert.assertEquals(row4.get(8).getText().trim(), "None");
        Assert.assertEquals(row4.get(10).getText().trim(), "None");
        Assert.assertEquals(row4.get(12).getText().trim(), "None");
        Assert.assertEquals(row4.get(14).getText().trim(), "None");
        Assert.assertEquals(row4.get(16).getText().trim(), "None");
        menu = driver.findElement(By.cssSelector(".stripes"));
        menu.click();
        customizeHeaders = driver.findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(0).click();
        WebElement tOutside = driver.findElement(By.cssSelector(".ngColList li:nth-child(5)"));
        tOutside.click();

        //recipe filter
        WebElement recipe = driver.findElement(By.cssSelector(".ngColList li:nth-child(6)"));
        recipe.click();

        //Thickness Set filter
        filters = driver.findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("24.9");
        List<WebElement> operators = driver.findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        WebElement operator = driver.findElement(By.cssSelector(".select2-results li:nth-child(2)"));
        operator.click();
        operators = driver.findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = driver.findElement(By.cssSelector(".select2-results li:nth-child(3)"));
        operator.click();

        filterActive = driver.findElement(By.cssSelector(".isFilterActiveDecorator.active"));
        List<WebElement> row6 = driver.findElements(By.cssSelector(".colt6"));
        Assert.assertEquals(row6.get(1).getText().trim(), "Thickness Set");
        Assert.assertEquals(row6.get(2).getText().trim(), "24.90");
        Assert.assertEquals(row6.get(4).getText().trim(), "24.90");
        Assert.assertEquals(row6.get(6).getText().trim(), "24.90");
        Assert.assertEquals(row6.get(8).getText().trim(), "24.90");
        Assert.assertEquals(row6.get(10).getText().trim(), "24.90");
        Assert.assertEquals(row6.get(12).getText().trim(), "24.90");
        operators = driver.findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = driver.findElement(By.cssSelector(".select2-results li:nth-child(4)"));
        operator.click();
        row6 = driver.findElements(By.cssSelector(".colt6"));
        Assert.assertEquals(row6.get(1).getText().trim(), "Thickness Set");
        Assert.assertEquals(row6.get(2).getText().trim(), "24.90");
        Assert.assertEquals(row6.get(4).getText().trim(), "24.90");
        Assert.assertEquals(row6.get(6).getText().trim(), "24.90");
        Assert.assertEquals(row6.get(8).getText().trim(), "24.90");
        Assert.assertEquals(row6.get(10).getText().trim(), "24.90");
        Assert.assertEquals(row6.get(12).getText().trim(), "24.90");
        operators = driver.findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = driver.findElement(By.cssSelector(".select2-results li:nth-child(5)"));
        operator.click();
        row6 = driver.findElements(By.cssSelector(".colt6"));
        Assert.assertEquals(row6.get(1).getText().trim(), "Thickness Set");
        Assert.assertEquals(row6.get(2).getText().trim(), "24.90");
        Assert.assertEquals(row6.get(4).getText().trim(), "24.90");
        Assert.assertEquals(row6.get(6).getText().trim(), "24.90");
        Assert.assertEquals(row6.get(8).getText().trim(), "24.90");
        Assert.assertEquals(row6.get(10).getText().trim(), "24.90");
        Assert.assertEquals(row6.get(12).getText().trim(), "24.90");
        operators = driver.findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = driver.findElement(By.cssSelector(".select2-results li:nth-child(6)"));
        operator.click();
        operators = driver.findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = driver.findElement(By.cssSelector(".select2-results li:nth-child(7)"));
        operator.click();
        clearFilter = driver.findElements(By.cssSelector(".clearInput"));
        clearFilter.get(0).click();
        menu = driver.findElement(By.cssSelector(".stripes"));
        menu.click();
        customizeHeaders = driver.findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(0).click();
        WebElement thicknessSet = driver.findElement(By.cssSelector(".ngColList li:nth-child(7)"));
        thicknessSet.click();

        //end time filter
        List<WebElement> datepicker = driver.findElements(By.cssSelector(".datepickerCol"));
        datepicker.get(0).click();
        Utils.sleep(500);
        List<WebElement> save = driver.findElements(By.cssSelector(".dialogButton.dbSaveClose"));
        save.get(4).click();
        operators = driver.findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = driver.findElement(By.cssSelector(".select2-results li:nth-child(2)"));
        operator.click();
        filterActive = driver.findElement(By.cssSelector(".isFilterActiveDecorator.active"));
        List<WebElement> row7 = driver.findElements(By.cssSelector(".colt7"));
        Assert.assertEquals(row7.get(1).getText().trim(), "End Time");
        Assert.assertEquals(row7.get(2).getText().trim(), DateUtils.date("2016-04-13 01:41:41", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(row7.get(4).getText().trim(), DateUtils.date("2016-04-12 23:48:33", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(row7.get(6).getText().trim(), DateUtils.date("2016-04-12 22:05:25", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(row7.get(8).getText().trim(), DateUtils.date("2016-04-12 20:22:16", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(row7.get(10).getText().trim(), DateUtils.date("2016-04-12 18:39:17", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(row7.get(12).getText().trim(), DateUtils.date("2016-04-12 16:56:19", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(row7.get(14).getText().trim(), DateUtils.date("2016-04-12 15:13:21", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(row7.get(16).getText().trim(), DateUtils.date("2016-04-12 13:30:24", DEFAULT_LONG_DATE_FORMAT));
        operators = driver.findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = driver.findElement(By.cssSelector(".select2-results li:nth-child(3)"));
        operator.click();
        row7 = driver.findElements(By.cssSelector(".colt7"));
        Assert.assertEquals(row7.get(1).getText().trim(), "End Time");
        Assert.assertEquals(row7.get(2).getText().trim(), DateUtils.date("2016-04-13 01:41:41", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(row7.get(4).getText().trim(), DateUtils.date("2016-04-12 23:48:33", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(row7.get(6).getText().trim(), DateUtils.date("2016-04-12 22:05:25", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(row7.get(8).getText().trim(), DateUtils.date("2016-04-12 20:22:16", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(row7.get(10).getText().trim(), DateUtils.date("2016-04-12 18:39:17", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(row7.get(12).getText().trim(), DateUtils.date("2016-04-12 16:56:19", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(row7.get(14).getText().trim(), DateUtils.date("2016-04-12 15:13:21", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(row7.get(16).getText().trim(), DateUtils.date("2016-04-12 13:30:24", DEFAULT_LONG_DATE_FORMAT));
        operators = driver.findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = driver.findElement(By.cssSelector(".select2-results li:nth-child(4)"));
        operator.click();
        operators = driver.findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = driver.findElement(By.cssSelector(".select2-results li:nth-child(5)"));
        operator.click();
        operators = driver.findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = driver.findElement(By.cssSelector(".select2-results li:nth-child(6)"));
        operator.click();
        operators = driver.findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = driver.findElement(By.cssSelector(".select2-results li:nth-child(7)"));
        operator.click();
        row7 = driver.findElements(By.cssSelector(".colt7"));
        Assert.assertEquals(row7.get(1).getText().trim(), "End Time");
        Assert.assertEquals(row7.get(2).getText().trim(), DateUtils.date("2016-04-13 01:41:41", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(row7.get(4).getText().trim(), DateUtils.date("2016-04-12 23:48:33", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(row7.get(6).getText().trim(), DateUtils.date("2016-04-12 22:05:25", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(row7.get(8).getText().trim(), DateUtils.date("2016-04-12 20:22:16", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(row7.get(10).getText().trim(), DateUtils.date("2016-04-12 18:39:17", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(row7.get(12).getText().trim(), DateUtils.date("2016-04-12 16:56:19", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(row7.get(14).getText().trim(), DateUtils.date("2016-04-12 15:13:21", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(row7.get(16).getText().trim(), DateUtils.date("2016-04-12 13:30:24", DEFAULT_LONG_DATE_FORMAT));
        menu = driver.findElement(By.cssSelector(".stripes"));
        menu.click();
        customizeHeaders = driver.findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(0).click();
        WebElement endTime = driver.findElement(By.cssSelector(".ngColList li:nth-child(8)"));
        endTime.click();

        //Length filter
        filters = driver.findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("40801");
        operators = driver.findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = driver.findElement(By.cssSelector(".select2-results li:nth-child(2)"));
        operator.click();
        List<WebElement> row8 = driver.findElements(By.cssSelector(".colt8"));
        Assert.assertEquals(row8.get(1).getText().trim(), "Length");
        Assert.assertEquals(row8.get(2).getText().trim(), "40,800");
        Assert.assertEquals(row8.get(4).getText().trim(), "40,800");
        operators = driver.findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = driver.findElement(By.cssSelector(".select2-results li:nth-child(3)"));
        operator.click();
        filterActive = driver.findElement(By.cssSelector(".isFilterActiveDecorator.active"));
        row8 = driver.findElements(By.cssSelector(".colt8"));
        Assert.assertEquals(row8.get(1).getText().trim(), "Length");
        Assert.assertEquals(row8.get(2).getText().trim(), "40,801");
        Assert.assertEquals(row8.get(4).getText().trim(), "40,800");
        Assert.assertEquals(row8.get(6).getText().trim(), "40,801");
        Assert.assertEquals(row8.get(8).getText().trim(), "40,800");
        Assert.assertEquals(row8.get(10).getText().trim(), "40,801");
        Assert.assertEquals(row8.get(12).getText().trim(), "40,801");
        Assert.assertEquals(row8.get(14).getText().trim(), "40,801");
        operators = driver.findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = driver.findElement(By.cssSelector(".select2-results li:nth-child(4)"));
        operator.click();
        row8 = driver.findElements(By.cssSelector(".colt8"));
        Assert.assertEquals(row8.get(1).getText().trim(), "Length");
        Assert.assertEquals(row8.get(2).getText().trim(), "40,801");
        Assert.assertEquals(row8.get(4).getText().trim(), "40,801");
        Assert.assertEquals(row8.get(6).getText().trim(), "40,801");
        Assert.assertEquals(row8.get(8).getText().trim(), "40,801");
        Assert.assertEquals(row8.get(10).getText().trim(), "40,801");
        operators = driver.findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = driver.findElement(By.cssSelector(".select2-results li:nth-child(5)"));
        operator.click();
        row8 = driver.findElements(By.cssSelector(".colt8"));
        Assert.assertEquals(row8.get(1).getText().trim(), "Length");
        Assert.assertEquals(row8.get(2).getText().trim(), "44,800");
        Assert.assertEquals(row8.get(4).getText().trim(), "40,801");
        Assert.assertEquals(row8.get(6).getText().trim(), "40,801");
        Assert.assertEquals(row8.get(8).getText().trim(), "40,801");
        Assert.assertEquals(row8.get(10).getText().trim(), "40,801");
        Assert.assertEquals(row8.get(12).getText().trim(), "40,801");
        operators = driver.findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = driver.findElement(By.cssSelector(".select2-results li:nth-child(6)"));
        operator.click();
        row8 = driver.findElements(By.cssSelector(".colt8"));
        Assert.assertEquals(row8.get(1).getText().trim(), "Length");
        Assert.assertEquals(row8.get(2).getText().trim(), "44,800");
        operators = driver.findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = driver.findElement(By.cssSelector(".select2-results li:nth-child(7)"));
        operator.click();
        row8 = driver.findElements(By.cssSelector(".colt8"));
        Assert.assertEquals(row8.get(1).getText().trim(), "Length");
        Assert.assertEquals(row8.get(2).getText().trim(), "44,800");
        Assert.assertEquals(row8.get(4).getText().trim(), "40,800");
        Assert.assertEquals(row8.get(6).getText().trim(), "40,800");
        menu = driver.findElement(By.cssSelector(".stripes"));
        menu.click();
        customizeHeaders = driver.findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(0).click();
        WebElement length = driver.findElement(By.cssSelector(".ngColList li:nth-child(9)"));
        length.click();

        //Density filter
        filters = driver.findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("90.5");
        operators = driver.findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = driver.findElement(By.cssSelector(".select2-results li:nth-child(2)"));
        operator.click();
        List<WebElement> row9 = driver.findElements(By.cssSelector(".colt9"));
        Assert.assertEquals(row9.get(1).getText().trim(), "Density");
        Assert.assertEquals(row9.get(2).getText().trim(), "90.4");
        Assert.assertEquals(row9.get(4).getText().trim(), "90.3");
        Assert.assertEquals(row9.get(6).getText().trim(), "90.1");
        Assert.assertEquals(row9.get(8).getText().trim(), "90.3");
        Assert.assertEquals(row9.get(10).getText().trim(), "89.9");
        operators = driver.findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = driver.findElement(By.cssSelector(".select2-results li:nth-child(3)"));
        operator.click();
        filterActive = driver.findElement(By.cssSelector(".isFilterActiveDecorator.active"));
        row9 = driver.findElements(By.cssSelector(".colt9"));
        Assert.assertEquals(row9.get(1).getText().trim(), "Density");
        Assert.assertEquals(row9.get(2).getText().trim(), "90.4");
        Assert.assertEquals(row9.get(4).getText().trim(), "90.3");
        Assert.assertEquals(row9.get(6).getText().trim(), "90.5");
        Assert.assertEquals(row9.get(8).getText().trim(), "90.1");
        Assert.assertEquals(row9.get(10).getText().trim(), "90.3");
        Assert.assertEquals(row9.get(12).getText().trim(), "89.9");
        operators = driver.findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = driver.findElement(By.cssSelector(".select2-results li:nth-child(4)"));
        operator.click();
        row9 = driver.findElements(By.cssSelector(".colt9"));
        Assert.assertEquals(row9.get(1).getText().trim(), "Density");
        Assert.assertEquals(row9.get(2).getText().trim(), "90.5");
        operators = driver.findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = driver.findElement(By.cssSelector(".select2-results li:nth-child(5)"));
        operator.click();
        row9 = driver.findElements(By.cssSelector(".colt9"));
        Assert.assertEquals(row9.get(1).getText().trim(), "Density");
        Assert.assertEquals(row9.get(2).getText().trim(), "90.6");
        Assert.assertEquals(row9.get(4).getText().trim(), "90.6");
        Assert.assertEquals(row9.get(6).getText().trim(), "90.5");
        operators = driver.findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = driver.findElement(By.cssSelector(".select2-results li:nth-child(6)"));
        operator.click();
        row9 = driver.findElements(By.cssSelector(".colt9"));
        Assert.assertEquals(row9.get(1).getText().trim(), "Density");
        Assert.assertEquals(row9.get(2).getText().trim(), "90.6");
        Assert.assertEquals(row9.get(4).getText().trim(), "90.6");
        operators = driver.findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = driver.findElement(By.cssSelector(".select2-results li:nth-child(7)"));
        operator.click();
        row9 = driver.findElements(By.cssSelector(".colt9"));
        Assert.assertEquals(row9.get(1).getText().trim(), "Density");
        Assert.assertEquals(row9.get(2).getText().trim(), "90.4");
        Assert.assertEquals(row9.get(4).getText().trim(), "90.3");
        Assert.assertEquals(row9.get(6).getText().trim(), "90.6");
        Assert.assertEquals(row9.get(8).getText().trim(), "90.6");
        Assert.assertEquals(row9.get(10).getText().trim(), "90.1");
        Assert.assertEquals(row9.get(12).getText().trim(), "90.3");
        Assert.assertEquals(row9.get(14).getText().trim(), "89.9");
        menu = driver.findElement(By.cssSelector(".stripes"));
        menu.click();
        customizeHeaders = driver.findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(0).click();
        WebElement density = driver.findElement(By.cssSelector(".ngColList li:nth-child(10)"));
        density.click();

        //Product filter
        filters = driver.findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("No result");
        filterActive = driver.findElement(By.cssSelector(".isFilterActiveDecorator.active"));
        clearFilter = driver.findElements(By.cssSelector(".clearInput"));
        clearFilter.get(0).click();
        filters = driver.findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("plain");
        Utils.sleep(1000);
        List<WebElement> row10 = driver.findElements(By.cssSelector(".colt10"));
        Assert.assertEquals(row10.get(1).getText().trim(), "Product");

    }

    @AfterMethod
    public void restoreApplicationSettings() throws IOException {
        getMongoUtils().executeScript(USER_SETTINGS_SCRIPT_NAME);
    }
}*/
