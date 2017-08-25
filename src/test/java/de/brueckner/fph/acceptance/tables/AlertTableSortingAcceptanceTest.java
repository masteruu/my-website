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

public class AlertTableSortingAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(AlertTableSortingAcceptanceTest.class);

    @Test
    public void rollAlertTableSortingAcceptance() {
        logger.info("rollAlertTableSortingAcceptance");
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

        List<WebElement> row1 = driver.findElements(By.cssSelector(".colt0"));
        row1.get(1).click();
        Assert.assertEquals(row1.get(1).getText(), "Alarm Period Type");
        Assert.assertEquals(row1.get(2).getText(), "  CAME AND WENT");
        Assert.assertEquals(row1.get(4).getText(), "  CAME AND WENT");
        Assert.assertEquals(row1.get(6).getText(), "  CAME AND WENT");
        Assert.assertEquals(row1.get(8).getText(), "  CAME AND WENT");
        Assert.assertEquals(row1.get(10).getText(), "  CAME AND WENT");
        Assert.assertEquals(row1.get(12).getText(), "  CAME AND WENT");
        row1.get(1).click();
        row1 = driver.findElements(By.cssSelector(".colt0"));
        Assert.assertEquals(row1.get(1).getText(), "Alarm Period Type");
        Assert.assertEquals(row1.get(2).getText(), "  CAME AND WENT");
        Assert.assertEquals(row1.get(4).getText(), "  CAME AND WENT");
        Assert.assertEquals(row1.get(6).getText(), "  CAME AND WENT");
        Assert.assertEquals(row1.get(8).getText(), "  CAME AND WENT");
        Assert.assertEquals(row1.get(10).getText(), "  CAME AND WENT");
        Assert.assertEquals(row1.get(12).getText(), "  CAME AND WENT");

        List<WebElement> row2 = driver.findElements(By.cssSelector(".colt1"));
        row2.get(1).click();
        Assert.assertEquals(row2.get(1).getText(), "Type");
        Assert.assertEquals(row2.get(2).getText(), "  FAULT");
        Assert.assertEquals(row2.get(4).getText(), "  FAULT");
        Assert.assertEquals(row2.get(6).getText(), "  FAULT");
        Assert.assertEquals(row2.get(8).getText(), "  FAULT");
        Assert.assertEquals(row2.get(10).getText(), "  FAULT");
        Assert.assertEquals(row2.get(12).getText(), "  FAULT");
        row2.get(1).click();
        row2 = driver.findElements(By.cssSelector(".colt1"));
        Assert.assertEquals(row2.get(1).getText(), "Type");
        Assert.assertEquals(row2.get(2).getText(), "  FAULT");
        Assert.assertEquals(row2.get(4).getText(), "  FAULT");
        Assert.assertEquals(row2.get(6).getText(), "  FAULT");
        Assert.assertEquals(row2.get(8).getText(), "  FAULT");
        Assert.assertEquals(row2.get(10).getText(), "  FAULT");
        Assert.assertEquals(row2.get(12).getText(), "  FAULT");

        List<WebElement> row3 = driver.findElements(By.cssSelector(".colt2"));
        row3.get(1).click();
        Assert.assertEquals(row3.get(1).getText(), "Message");
        Assert.assertEquals(row3.get(2).getText(), "  Stretching: TDO Outlet: #Drive Film Break Film Break 1");
        Assert.assertEquals(row3.get(4).getText(), "  Stretching: TDO Outlet: #Drive Film Break Film Break 1");
        Assert.assertEquals(row3.get(6).getText(), "  Stretching: TDO Outlet: #Drive Film Break Film Break 2");
        Assert.assertEquals(row3.get(8).getText(), "  Stretching: TDO Outlet: #Drive Film Break Film Break 2");
        Assert.assertEquals(row3.get(10).getText(), "  Stretching: TDO Outlet: #Drive Film Break Film Break 3");
        Assert.assertEquals(row3.get(12).getText(), "  Stretching: TDO Outlet: #Drive Film Break Film Break 3");
        row3.get(1).click();
        Assert.assertEquals(row3.get(1).getText(), "Message");
        Assert.assertEquals(row3.get(2).getText(), "  Stretching: TDO Outlet: #Drive Film Break Film Break 3");
        Assert.assertEquals(row3.get(4).getText(), "  Stretching: TDO Outlet: #Drive Film Break Film Break 3");
        Assert.assertEquals(row3.get(6).getText(), "  Stretching: TDO Outlet: #Drive Film Break Film Break 2");
        Assert.assertEquals(row3.get(8).getText(), "  Stretching: TDO Outlet: #Drive Film Break Film Break 2");
        Assert.assertEquals(row3.get(10).getText(), "  Stretching: TDO Outlet: #Drive Film Break Film Break 1");
        Assert.assertEquals(row3.get(12).getText(), "  Stretching: TDO Outlet: #Drive Film Break Film Break 1");

        List<WebElement> row4 = driver.findElements(By.cssSelector(".colt3"));
        row4.get(1).click();
        Assert.assertEquals(row4.get(1).getText(), "Date");
        Assert.assertEquals(row4.get(2).getText(), DateUtils.date("2016-04-17 01:27:48", "  dd.MM.YY  HH:mm:ss"));
        Assert.assertEquals(row4.get(4).getText(), DateUtils.date("2016-04-17 01:27:48", "  dd.MM.YY  HH:mm:ss"));
        Assert.assertEquals(row4.get(6).getText(), DateUtils.date("2016-04-17 01:27:48", "  dd.MM.YY  HH:mm:ss"));
        Assert.assertEquals(row4.get(8).getText(), DateUtils.date("2016-04-17 01:33:12", "  dd.MM.YY  HH:mm:ss"));
        Assert.assertEquals(row4.get(10).getText(), DateUtils.date("2016-04-17 01:33:12", "  dd.MM.YY  HH:mm:ss"));
        Assert.assertEquals(row4.get(12).getText(), DateUtils.date("2016-04-17 01:33:12", "  dd.MM.YY  HH:mm:ss"));
        row4.get(1).click();
        Assert.assertEquals(row4.get(1).getText(), "Date");
        Assert.assertEquals(row4.get(2).getText(), DateUtils.date("2016-04-17 01:33:12", "  dd.MM.YY  HH:mm:ss"));
        Assert.assertEquals(row4.get(4).getText(), DateUtils.date("2016-04-17 01:33:12", "  dd.MM.YY  HH:mm:ss"));
        Assert.assertEquals(row4.get(6).getText(), DateUtils.date("2016-04-17 01:33:12", "  dd.MM.YY  HH:mm:ss"));
        Assert.assertEquals(row4.get(8).getText(), DateUtils.date("2016-04-17 01:27:48", "  dd.MM.YY  HH:mm:ss"));
        Assert.assertEquals(row4.get(10).getText(), DateUtils.date("2016-04-17 01:27:48", "  dd.MM.YY  HH:mm:ss"));
        Assert.assertEquals(row4.get(12).getText(), DateUtils.date("2016-04-17 01:27:48", "  dd.MM.YY  HH:mm:ss"));

        List<WebElement> row5 = driver.findElements(By.cssSelector(".colt4"));
        row5.get(1).click();
        Assert.assertEquals(row5.get(1).getText(), "Direction");
        Assert.assertEquals(row5.get(2).getText(), "  CAME");
        Assert.assertEquals(row5.get(4).getText(), "  CAME");
        Assert.assertEquals(row5.get(6).getText(), "  CAME");
        Assert.assertEquals(row5.get(8).getText(), "  WENT");
        Assert.assertEquals(row5.get(10).getText(), "  WENT");
        Assert.assertEquals(row5.get(12).getText(), "  WENT");
        row5.get(1).click();
        Assert.assertEquals(row5.get(1).getText(), "Direction");
        Assert.assertEquals(row5.get(2).getText(), "  WENT");
        Assert.assertEquals(row5.get(4).getText(), "  WENT");
        Assert.assertEquals(row5.get(6).getText(), "  WENT");
        Assert.assertEquals(row5.get(8).getText(), "  CAME");
        Assert.assertEquals(row5.get(10).getText(), "  CAME");
        Assert.assertEquals(row5.get(12).getText(), "  CAME");

        List<WebElement> row6 = driver.findElements(By.cssSelector(".colt5"));
        row6.get(1).click();
        Assert.assertEquals(row6.get(1).getText(), "Data Point");
        Assert.assertEquals(row6.get(2).getText(), "StrTdoOut.Fbr.AlaFbr001");
        Assert.assertEquals(row6.get(4).getText(), "StrTdoOut.Fbr.AlaFbr001");
        Assert.assertEquals(row6.get(6).getText(), "StrTdoOut.Fbr.AlaFbr002");
        Assert.assertEquals(row6.get(8).getText(), "StrTdoOut.Fbr.AlaFbr002");
        Assert.assertEquals(row6.get(10).getText(), "StrTdoOut.Fbr.AlaFbr003");
        Assert.assertEquals(row6.get(12).getText(), "StrTdoOut.Fbr.AlaFbr003");
        row6.get(1).click();
        Assert.assertEquals(row6.get(1).getText(), "Data Point");
        Assert.assertEquals(row6.get(2).getText(), "StrTdoOut.Fbr.AlaFbr003");
        Assert.assertEquals(row6.get(4).getText(), "StrTdoOut.Fbr.AlaFbr003");
        Assert.assertEquals(row6.get(6).getText(), "StrTdoOut.Fbr.AlaFbr002");
        Assert.assertEquals(row6.get(8).getText(), "StrTdoOut.Fbr.AlaFbr002");
        Assert.assertEquals(row6.get(10).getText(), "StrTdoOut.Fbr.AlaFbr001");
        Assert.assertEquals(row6.get(12).getText(), "StrTdoOut.Fbr.AlaFbr001");

    }
}*/
