/*
package de.brueckner.fph.oldtests.acceptance.tables;

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
import org.testng.annotations.Test;

import java.util.List;


public class AlertTableFilteringAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(AlertTableFilteringAcceptanceTest.class);


    @Test
    public void alertTableFilteringAcceptanceTest() {
        logger.info("alertTableFilteringAcceptanceTest");
        ActionsUtils.loginPage(LoginCredentials.USER_DEV, LoginCredentials.PASSWORD);

        //setting the date picker to a specific date
        dateUtils.setDatePicker("2016-04-12 22:00:00", "2016-04-18 22:00:00");
        WebElement alert = driver.findElement(By.cssSelector(".mainContentTabs li:nth-child(4)"));
        alert.click();
        Utils.sleep(1500);
        WebElement container = driver.findElement(By.cssSelector(".ngCanvas"));
        Assert.assertEquals(container.getAttribute("style"), "height: 120px;");

        List<WebElement> active = driver.findElements(By.cssSelector(".ngHeaderButton"));
        active.get(1).click();
        Utils.sleep(700);
        container = driver.findElement(By.cssSelector(".ngCanvas"));
        Assert.assertEquals(container.getAttribute("style"), "height: 0px;");
        List<WebElement> chronological = driver.findElements(By.cssSelector(".ngHeaderButton"));
        chronological.get(2).click();
        container = driver.findElement(By.cssSelector(".ngCanvas"));
        Assert.assertEquals(container.getAttribute("style"), "height: 240px;");

        WebElement menu = driver.findElement(By.cssSelector(".stripes"));
        menu.click();

        List<WebElement> showFilters = driver.findElements(By.cssSelector(".tableMenu li:nth-child(1)"));
        showFilters.get(0).click();
        WebElement alarmPeriodTypeFilter = driver.findElement(By.cssSelector(".multidropdownHeader"));
        alarmPeriodTypeFilter.click();
        Utils.sleep(700);

        //Alarm Period Type filter
        List<WebElement> went = driver.findElements(By.cssSelector(".fakeCheckbox"));
        went.get(5).click();
        went = driver.findElements(By.cssSelector(".fakeCheckbox"));
        went.get(5).click();
        went = driver.findElements(By.cssSelector(".fakeCheckbox"));
        went.get(6).click();
        WebElement filterActive = driver.findElement(By.cssSelector(".isFilterActiveDecorator.active"));
        List<WebElement> row1 = driver.findElements(By.cssSelector(".colt0"));
        Assert.assertEquals(row1.get(1).getText(), "Alarm Period Type");
        Assert.assertEquals(row1.get(2).getText(), "  CAME AND WENT");
        Assert.assertEquals(row1.get(4).getText(), "  CAME AND WENT");
        Assert.assertEquals(row1.get(6).getText(), "  CAME AND WENT");
        Assert.assertEquals(row1.get(8).getText(), "  CAME AND WENT");
        Assert.assertEquals(row1.get(10).getText(), "  CAME AND WENT");
        Assert.assertEquals(row1.get(12).getText(), "  CAME AND WENT");
        went = driver.findElements(By.cssSelector(".fakeCheckbox"));
        went.get(6).click();

        went = driver.findElements(By.cssSelector(".fakeCheckbox"));
        went.get(7).click();
        went = driver.findElements(By.cssSelector(".fakeCheckbox"));
        went.get(7).click();
        went = driver.findElements(By.cssSelector(".fakeCheckbox"));
        went.get(8).click();
        went = driver.findElements(By.cssSelector(".fakeCheckbox"));
        went.get(8).click();

        went = driver.findElements(By.cssSelector(".fakeCheckbox"));
        went.get(5).click();
        went = driver.findElements(By.cssSelector(".fakeCheckbox"));
        went.get(6).click();
        went = driver.findElements(By.cssSelector(".fakeCheckbox"));
        went.get(7).click();
        went = driver.findElements(By.cssSelector(".fakeCheckbox"));
        went.get(8).click();
        row1 = driver.findElements(By.cssSelector(".colt0"));
        Assert.assertEquals(row1.get(1).getText(), "Alarm Period Type");
        Assert.assertEquals(row1.get(2).getText(), "  CAME AND WENT");
        Assert.assertEquals(row1.get(4).getText(), "  CAME AND WENT");
        Assert.assertEquals(row1.get(6).getText(), "  CAME AND WENT");
        Assert.assertEquals(row1.get(8).getText(), "  CAME AND WENT");
        Assert.assertEquals(row1.get(10).getText(), "  CAME AND WENT");
        Assert.assertEquals(row1.get(12).getText(), "  CAME AND WENT");

        went = driver.findElements(By.cssSelector(".fakeCheckbox"));
        went.get(5).click();
        went = driver.findElements(By.cssSelector(".fakeCheckbox"));
        went.get(6).click();
        went = driver.findElements(By.cssSelector(".fakeCheckbox"));
        went.get(7).click();
        went = driver.findElements(By.cssSelector(".fakeCheckbox"));
        went.get(8).click();

        WebElement diffClick = driver.findElement(By.cssSelector("#commonOverlay"));
        diffClick.click();

        menu = driver.findElement(By.cssSelector(".stripes"));
        menu.click();

        List<WebElement> customizeHeaders = driver.findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(0).click();

        WebElement alarmPeriodType = driver.findElement(By.cssSelector(".ngColList li:nth-child(1)"));
        alarmPeriodType.click();

        //type filter
        WebElement typeDropdown = driver.findElement(By.cssSelector(".dropdown-toggle"));
        typeDropdown.click();
        Utils.sleep(700);
        went = driver.findElements(By.cssSelector(".fakeCheckbox"));
        went.get(5).click();
        went = driver.findElements(By.cssSelector(".fakeCheckbox"));
        went.get(5).click();
        went = driver.findElements(By.cssSelector(".fakeCheckbox"));
        went.get(6).click();
        filterActive = driver.findElement(By.cssSelector(".isFilterActiveDecorator.active"));
        List<WebElement> row2 = driver.findElements(By.cssSelector(".colt1"));
        Assert.assertEquals(row2.get(1).getText(), "Type");
        Assert.assertEquals(row2.get(2).getText(), "  FAULT");
        Assert.assertEquals(row2.get(4).getText(), "  FAULT");
        Assert.assertEquals(row2.get(6).getText(), "  FAULT");
        Assert.assertEquals(row2.get(8).getText(), "  FAULT");
        Assert.assertEquals(row2.get(10).getText(), "  FAULT");
        Assert.assertEquals(row2.get(12).getText(), "  FAULT");
        went = driver.findElements(By.cssSelector(".fakeCheckbox"));
        went.get(6).click();

        went = driver.findElements(By.cssSelector(".fakeCheckbox"));
        went.get(7).click();
        went = driver.findElements(By.cssSelector(".fakeCheckbox"));
        went.get(7).click();

        went = driver.findElements(By.cssSelector(".fakeCheckbox"));
        went.get(5).click();
        went = driver.findElements(By.cssSelector(".fakeCheckbox"));
        went.get(6).click();
        went = driver.findElements(By.cssSelector(".fakeCheckbox"));
        went.get(7).click();

        row2 = driver.findElements(By.cssSelector(".colt1"));
        Assert.assertEquals(row2.get(1).getText(), "Type");
        Assert.assertEquals(row2.get(2).getText(), "  FAULT");
        Assert.assertEquals(row2.get(4).getText(), "  FAULT");
        Assert.assertEquals(row2.get(6).getText(), "  FAULT");
        Assert.assertEquals(row2.get(8).getText(), "  FAULT");
        Assert.assertEquals(row2.get(10).getText(), "  FAULT");
        Assert.assertEquals(row2.get(12).getText(), "  FAULT");

        diffClick = driver.findElement(By.cssSelector("#commonOverlay"));
        diffClick.click();

        menu = driver.findElement(By.cssSelector(".stripes"));
        menu.click();

        customizeHeaders = driver.findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(0).click();

        WebElement type = driver.findElement(By.cssSelector(".ngColList li:nth-child(2)"));
        type.click();

        //message filter
        List<WebElement> filter3 = driver.findElements(By.cssSelector(".filterColumnInput "));
        filter3.get(0).sendKeys("No result");
        filterActive = driver.findElement(By.cssSelector(".isFilterActiveDecorator.active"));
        List<WebElement> clearFilter = driver.findElements(By.cssSelector(".clearInput"));
        clearFilter.get(0).click();
        List<WebElement> row3 = driver.findElements(By.cssSelector(".colt2"));
        Assert.assertEquals(row3.get(1).getText(), "Message");
        Assert.assertEquals(row3.get(2).getText(), "  Stretching: TDO Outlet: #Drive Film Break Film Break 1");
        Assert.assertEquals(row3.get(4).getText(), "  Stretching: TDO Outlet: #Drive Film Break Film Break 2");
        Assert.assertEquals(row3.get(6).getText(), "  Stretching: TDO Outlet: #Drive Film Break Film Break 3");
        Assert.assertEquals(row3.get(8).getText(), "  Stretching: TDO Outlet: #Drive Film Break Film Break 1");
        Assert.assertEquals(row3.get(10).getText(), "  Stretching: TDO Outlet: #Drive Film Break Film Break 2");
        Assert.assertEquals(row3.get(12).getText(), "  Stretching: TDO Outlet: #Drive Film Break Film Break 3");

        filter3 = driver.findElements(By.cssSelector(".filterColumnInput "));
        filter3.get(0).sendKeys("tre");
        row3 = driver.findElements(By.cssSelector(".colt2"));
        Assert.assertEquals(row3.get(1).getText(), "Message");
        Assert.assertEquals(row3.get(2).getText(), "  Stretching: TDO Outlet: #Drive Film Break Film Break 1");
        Assert.assertEquals(row3.get(4).getText(), "  Stretching: TDO Outlet: #Drive Film Break Film Break 2");
        Assert.assertEquals(row3.get(6).getText(), "  Stretching: TDO Outlet: #Drive Film Break Film Break 3");
        Assert.assertEquals(row3.get(8).getText(), "  Stretching: TDO Outlet: #Drive Film Break Film Break 1");
        Assert.assertEquals(row3.get(10).getText(), "  Stretching: TDO Outlet: #Drive Film Break Film Break 2");
        Assert.assertEquals(row3.get(12).getText(), "  Stretching: TDO Outlet: #Drive Film Break Film Break 3");

        menu = driver.findElement(By.cssSelector(".stripes"));
        menu.click();

        customizeHeaders = driver.findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(0).click();

        WebElement message = driver.findElement(By.cssSelector(".ngColList li:nth-child(3)"));
        message.click();

        //date filter
        WebElement datepicker = driver.findElement(By.cssSelector(".datepickerCol"));
        datepicker.click();
        Utils.sleep(500);

        List<WebElement> save = driver.findElements(By.cssSelector(".dialogButton.dbSaveClose"));
        save.get(4).click();
        List<WebElement> operators = driver.findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        WebElement operator = driver.findElement(By.cssSelector(".select2-results li:nth-child(2)"));
        operator.click();
        filterActive = driver.findElement(By.cssSelector(".isFilterActiveDecorator.active"));
        List<WebElement> results = driver.findElements(By.cssSelector(".colt3"));
        Assert.assertEquals(results.get(1).getText().trim(), "Date");
        Assert.assertEquals(results.get(2).getText().trim(), DateUtils.date("2016-04-17 01:27:48", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));
        Assert.assertEquals(results.get(4).getText().trim(), DateUtils.date("2016-04-17 01:27:48", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));
        Assert.assertEquals(results.get(6).getText().trim(), DateUtils.date("2016-04-17 01:27:48", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));
        Assert.assertEquals(results.get(8).getText().trim(), DateUtils.date("2016-04-17 01:33:12", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));
        Assert.assertEquals(results.get(10).getText().trim(), DateUtils.date("2016-04-17 01:33:12", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));
        Assert.assertEquals(results.get(12).getText().trim(), DateUtils.date("2016-04-17 01:33:12", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));
        operators = driver.findElements(By.cssSelector(".select2-container"));
        operators.get(10).click();
        operator = driver.findElement(By.cssSelector(".select2-results li:nth-child(3)"));
        operator.click();
        results = driver.findElements(By.cssSelector(".colt3"));
        Assert.assertEquals(results.get(1).getText().trim(), "Date");
        Assert.assertEquals(results.get(2).getText().trim(), DateUtils.date("2016-04-17 01:27:48", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));
        Assert.assertEquals(results.get(4).getText().trim(), DateUtils.date("2016-04-17 01:27:48", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));
        Assert.assertEquals(results.get(6).getText().trim(), DateUtils.date("2016-04-17 01:27:48", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));
        Assert.assertEquals(results.get(8).getText().trim(), DateUtils.date("2016-04-17 01:33:12", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));
        Assert.assertEquals(results.get(10).getText().trim(), DateUtils.date("2016-04-17 01:33:12", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));
        Assert.assertEquals(results.get(12).getText().trim(), DateUtils.date("2016-04-17 01:33:12", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));
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
        results = driver.findElements(By.cssSelector(".colt3"));
        Assert.assertEquals(results.get(1).getText().trim(), "Date");
        Assert.assertEquals(results.get(2).getText().trim(), DateUtils.date("2016-04-17 01:27:48", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));
        Assert.assertEquals(results.get(4).getText().trim(), DateUtils.date("2016-04-17 01:27:48", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));
        Assert.assertEquals(results.get(6).getText().trim(), DateUtils.date("2016-04-17 01:27:48", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));
        Assert.assertEquals(results.get(8).getText().trim(), DateUtils.date("2016-04-17 01:33:12", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));
        Assert.assertEquals(results.get(10).getText().trim(), DateUtils.date("2016-04-17 01:33:12", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));
        Assert.assertEquals(results.get(12).getText().trim(), DateUtils.date("2016-04-17 01:33:12", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));
        menu = driver.findElement(By.cssSelector(".stripes"));
        menu.click();

        customizeHeaders = driver.findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(0).click();

        WebElement date = driver.findElement(By.cssSelector(".ngColList li:nth-child(4)"));
        date.click();

        //direction filter
        List<WebElement> filter5 = driver.findElements(By.cssSelector(".filterColumnInput "));
        filter5.get(0).sendKeys("No result");
        filterActive = driver.findElement(By.cssSelector(".isFilterActiveDecorator.active"));
        clearFilter = driver.findElements(By.cssSelector(".clearInput"));
        clearFilter.get(0).click();
        List<WebElement> row5 = driver.findElements(By.cssSelector(".colt4"));
        Assert.assertEquals(row5.get(1).getText(), "Direction");
        Assert.assertEquals(row5.get(2).getText(), "  CAME");
        Assert.assertEquals(row5.get(4).getText(), "  CAME");
        Assert.assertEquals(row5.get(6).getText(), "  CAME");
        Assert.assertEquals(row5.get(8).getText(), "  WENT");
        Assert.assertEquals(row5.get(10).getText(), "  WENT");
        Assert.assertEquals(row5.get(12).getText(), "  WENT");

        filter3 = driver.findElements(By.cssSelector(".filterColumnInput "));
        filter3.get(0).sendKeys("came");
        row5 = driver.findElements(By.cssSelector(".colt4"));
        Assert.assertEquals(row5.get(1).getText(), "Direction");
        Assert.assertEquals(row5.get(2).getText(), "  CAME");
        Assert.assertEquals(row5.get(4).getText(), "  CAME");
        Assert.assertEquals(row5.get(6).getText(), "  CAME");

        menu = driver.findElement(By.cssSelector(".stripes"));
        menu.click();

        customizeHeaders = driver.findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(0).click();

        WebElement direction = driver.findElement(By.cssSelector(".ngColList li:nth-child(5)"));
        direction.click();
        menu = driver.findElement(By.cssSelector(".stripes"));
        menu.click();

        //data point filter
        List<WebElement> filter6 = driver.findElements(By.cssSelector(".filterColumnInput "));
        filter6.get(0).sendKeys("No result");
        filter6.get(0).clear();
        List<WebElement> row6 = driver.findElements(By.cssSelector(".colt5"));
        Assert.assertEquals(row6.get(1).getText(), "Data Point");
        Assert.assertEquals(row6.get(2).getText(), "StrTdoOut.Fbr.AlaFbr001");
        Assert.assertEquals(row6.get(4).getText(), "StrTdoOut.Fbr.AlaFbr002");
        Assert.assertEquals(row6.get(6).getText(), "StrTdoOut.Fbr.AlaFbr003");
        Assert.assertEquals(row6.get(8).getText(), "StrTdoOut.Fbr.AlaFbr001");
        Assert.assertEquals(row6.get(10).getText(), "StrTdoOut.Fbr.AlaFbr002");
        Assert.assertEquals(row6.get(12).getText(), "StrTdoOut.Fbr.AlaFbr003");

        filter6 = driver.findElements(By.cssSelector(".filterColumnInput "));
        filter6.get(0).sendKeys("001");
        row6 = driver.findElements(By.cssSelector(".colt5"));
        Assert.assertEquals(row6.get(1).getText(), "Data Point");
        Assert.assertEquals(row6.get(2).getText(), "StrTdoOut.Fbr.AlaFbr001");
        Assert.assertEquals(row6.get(4).getText(), "StrTdoOut.Fbr.AlaFbr001");

        menu = driver.findElement(By.cssSelector(".stripes"));
        menu.click();

        customizeHeaders = driver.findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(0).click();

        WebElement dataPoint = driver.findElement(By.cssSelector(".ngColList li:nth-child(6)"));
        dataPoint.click();

        alarmPeriodType = driver.findElement(By.cssSelector(".ngColList li:nth-child(1)"));
        alarmPeriodType.click();
        type = driver.findElement(By.cssSelector(".ngColList li:nth-child(2)"));
        type.click();
        message = driver.findElement(By.cssSelector(".ngColList li:nth-child(3)"));
        message.click();
        date = driver.findElement(By.cssSelector(".ngColList li:nth-child(4)"));
        date.click();
        direction = driver.findElement(By.cssSelector(".ngColList li:nth-child(5)"));
        direction.click();
        dataPoint = driver.findElement(By.cssSelector(".ngColList li:nth-child(6)"));
        dataPoint.click();
        menu = driver.findElement(By.cssSelector(".stripes"));
        menu.click();

        //multiple filters
        row1 = driver.findElements(By.cssSelector(".colt0"));
        Assert.assertEquals(row1.get(1).getText(), "Alarm Period Type");
        Assert.assertEquals(row1.get(2).getText(), "  CAME AND WENT");
        row2 = driver.findElements(By.cssSelector(".colt1"));
        Assert.assertEquals(row2.get(1).getText(), "Type");
        Assert.assertEquals(row2.get(2).getText(), "  FAULT");
        row3 = driver.findElements(By.cssSelector(".colt2"));
        Assert.assertEquals(row3.get(1).getText(), "Message");
        Assert.assertEquals(row3.get(2).getText(), "  Stretching: TDO Outlet: #Drive Film Break Film Break 1");
        results = driver.findElements(By.cssSelector(".colt3"));
        Assert.assertEquals(results.get(1).getText().trim(), "Date");
        Assert.assertEquals(results.get(2).getText().trim(), DateUtils.date("2016-04-17 01:27:48", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));
        row5 = driver.findElements(By.cssSelector(".colt4"));
        Assert.assertEquals(row5.get(1).getText(), "Direction");
        Assert.assertEquals(row5.get(2).getText(), "  CAME");
        row6 = driver.findElements(By.cssSelector(".colt5"));
        Assert.assertEquals(row6.get(1).getText(), "Data Point");
        Assert.assertEquals(row6.get(2).getText(), "StrTdoOut.Fbr.AlaFbr001");
    }
}*/
