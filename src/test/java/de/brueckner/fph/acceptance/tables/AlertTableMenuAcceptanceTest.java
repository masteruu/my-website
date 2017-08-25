/*
package de.brueckner.fph.oldtests.acceptance.tables;


import com.sdl.selenium.web.utils.Utils;
import de.brueckner.fph.oldtests.acceptance.appConfig.AbstractAcceptanceTest;
import de.brueckner.fph.oldtests.acceptance.appConfig.LoginCredentials;
import de.brueckner.fph.oldtests.util.ActionsUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AlertTableMenuAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(AlertTableMenuAcceptanceTest.class);

    @Test
    public void rollAlertTableMenuAcceptanceTest() {
        logger.info("rollAlertTableMenuAcceptanceTest");
        ActionsUtils.loginPage(LoginCredentials.USER_DEV, LoginCredentials.PASSWORD);

        //setting the date picker to a specific date
        dateUtils.setDatePicker("2016-04-12 22:00:00", "2016-04-18 22:00:00");
        WebElement alert = driver.findElement(By.cssSelector(".mainContentTabs li:nth-child(4)"));
        alert.click();
        Utils.sleep(1500);

        //show filters
        WebElement menu = driver.findElement(By.cssSelector(".stripes"));
        menu.click();
        List<WebElement> showFilters = driver.findElements(By.cssSelector(".tableMenu li:nth-child(1)"));
        showFilters.get(0).click();
        List<WebElement> filter = driver.findElements(By.cssSelector(".filterColumnInput"));
        filter.get(0).sendKeys("TEST");

        //reset filters
        WebElement container = driver.findElement(By.cssSelector(".ngCanvas"));

        Assert.assertEquals(container.getAttribute("style"), "height: 0px;");
        List<WebElement> resetFilters = driver.findElements(By.cssSelector(".tableMenu li:nth-child(2)"));
        resetFilters.get(0).click();
        container = driver.findElement(By.cssSelector(".ngCanvas"));
        Assert.assertEquals(container.getAttribute("style"), "height: 120px;");
        showFilters = driver.findElements(By.cssSelector(".tableMenu li:nth-child(1)"));
        showFilters.get(0).click();

        //enable grouping
        List<WebElement> enableGrouping = driver.findElements(By.cssSelector(".tableMenu li:nth-child(3)"));
        enableGrouping.get(0).click();
        WebElement grouping = driver.findElement(By.cssSelector(".calculatedDecorator.ng-hide"));
        enableGrouping = driver.findElements(By.cssSelector(".tableMenu li:nth-child(3)"));
        enableGrouping.get(0).click();

        //customize headers
        List<WebElement> customizeHeaders = driver.findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(0).click();

        WebElement alarmPeriodType = driver.findElement(By.cssSelector(".ngColList li:nth-child(1)"));
        alarmPeriodType.click();

        WebElement type = driver.findElement(By.cssSelector(".ngColList li:nth-child(2)"));
        type.click();

        WebElement message = driver.findElement(By.cssSelector(".ngColList li:nth-child(3)"));
        message.click();

        WebElement came = driver.findElement(By.cssSelector(".ngColList li:nth-child(4)"));
        came.click();

        WebElement went = driver.findElement(By.cssSelector(".ngColList li:nth-child(5)"));
        went.click();

        WebElement dataPoint = driver.findElement(By.cssSelector(".ngColList li:nth-child(6)"));
        dataPoint.click();
        alarmPeriodType = driver.findElement(By.cssSelector(".ngColList li:nth-child(1)"));
        alarmPeriodType.click();
        List<WebElement> row1 = driver.findElements(By.cssSelector(".colt0"));
        Assert.assertEquals(row1.get(1).getText(), "Alarm Period Type");
        type = driver.findElement(By.cssSelector(".ngColList li:nth-child(2)"));
        type.click();
        List<WebElement> row2 = driver.findElements(By.cssSelector(".colt1"));
        Assert.assertEquals(row2.get(1).getText(), "Type");
        message = driver.findElement(By.cssSelector(".ngColList li:nth-child(3)"));
        message.click();
        List<WebElement> row3 = driver.findElements(By.cssSelector(".colt2"));
        Assert.assertEquals(row3.get(1).getText(), "Message");
        came = driver.findElement(By.cssSelector(".ngColList li:nth-child(4)"));
        came.click();
        List<WebElement> row4 = driver.findElements(By.cssSelector(".colt3"));
        Assert.assertEquals(row4.get(1).getText(), "Came");
        went = driver.findElement(By.cssSelector(".ngColList li:nth-child(5)"));
        went.click();
        List<WebElement> row5 = driver.findElements(By.cssSelector(".colt4"));
        Assert.assertEquals(row5.get(1).getText(), "Went");
        dataPoint = driver.findElement(By.cssSelector(".ngColList li:nth-child(6)"));
        dataPoint.click();
        List<WebElement> row6 = driver.findElements(By.cssSelector(".colt5"));
        Assert.assertEquals(row6.get(1).getText(), "Data Point");
    }
}*/
