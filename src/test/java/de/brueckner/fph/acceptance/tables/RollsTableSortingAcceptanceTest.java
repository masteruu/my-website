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

public class RollsTableSortingAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(RollsTableSortingAcceptanceTest.class);

    @Test
    public void rollsTableSortingAcceptance() {
        logger.info("rollsTableSortingAcceptance");
        ActionsUtils.loginPage(LoginCredentials.USER_DEV, LoginCredentials.PASSWORD);

        //setting the date picker to a specific date
        dateUtils.setDatePicker("2016-04-12 11:47:00", "2016-04-13 10:47:00");

        //click on a campaign and check if the selection is done ok
        ActionsUtils.clickCampaign("campaign:160412114726310:1460461646310:1460511701759");
        ActionsUtils.waitSpinner();
        WebElement rolls = driver.findElement(By.cssSelector(".mainContentTabs li:nth-child(2)"));
        rolls.click();
        ActionsUtils.waitSpinner();
        Utils.sleep(1000);
        //Visual quality sorting
        List<WebElement> sortedElements = driver.findElements(By.cssSelector(".colt0"));
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Visual Quality");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "Good Roll");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "Good Roll");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "Good Roll");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "Good Roll");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "Good Roll");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "Good Roll");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "Good Roll");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "Good Roll");

        //Lab quality sorting
        sortedElements = driver.findElements(By.cssSelector(".colt1"));
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Lab Quality");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "Good Roll");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "Good Roll");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "Pending");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "Pending");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "Pending");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "Pending");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "Pending");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "Pending");
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Lab Quality");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "Pending");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "Pending");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "Pending");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "Pending");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "Pending");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "Pending");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "Good Roll");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "Good Roll");

        //ID sorting
        sortedElements = driver.findElements(By.cssSelector(".colt2"));
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "ID");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "A 160412 1947");
        Assert.assertEquals(sortedElements.get(3).getText().trim(), "A 160412 2130");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "A 160412 2313");
        Assert.assertEquals(sortedElements.get(5).getText().trim(), "A 160413 0056");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "A 160413 0239");
        Assert.assertEquals(sortedElements.get(7).getText().trim(), "A 160413 0422");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "A 160413 0605");
        Assert.assertEquals(sortedElements.get(9).getText().trim(), "A 160413 0748");
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "ID");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "A 160413 0748");
        Assert.assertEquals(sortedElements.get(3).getText().trim(), "A 160413 0605");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "A 160413 0422");
        Assert.assertEquals(sortedElements.get(5).getText().trim(), "A 160413 0239");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "A 160413 0056");
        Assert.assertEquals(sortedElements.get(7).getText().trim(), "A 160412 2313");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "A 160412 2130");
        Assert.assertEquals(sortedElements.get(9).getText().trim(), "A 160412 1947");

        //T.Inside sorting
        sortedElements = driver.findElements(By.cssSelector(".colt3"));
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "T. Inside");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "None");
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "T. Inside");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "None");

        //T.Outside sorting
        sortedElements = driver.findElements(By.cssSelector(".colt4"));
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "T. Outside");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "None");
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "T. Outside");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "None");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "None");

        //Recipe sorting
        sortedElements = driver.findElements(By.cssSelector(".colt5"));
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Recipe");

        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Recipe");

        //Thickness set sorting
        sortedElements = driver.findElements(By.cssSelector(".colt6"));
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Thickness Set");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "24.90");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "24.90");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "24.90");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "24.90");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "24.90");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "24.90");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "25.00");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "25.00");
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Thickness Set");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "25.00");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "25.00");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "24.90");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "24.90");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "24.90");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "24.90");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "24.90");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "24.90");

        //End time sorting
        sortedElements = driver.findElements(By.cssSelector(".colt7"));
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "End Time");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), DateUtils.date("2016-04-12 13:30:24", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(4).getText().trim(), DateUtils.date("2016-04-12 15:13:21", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(6).getText().trim(), DateUtils.date("2016-04-12 16:56:19", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(8).getText().trim(), DateUtils.date("2016-04-12 18:39:17", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(10).getText().trim(), DateUtils.date("2016-04-12 20:22:16", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(12).getText().trim(), DateUtils.date("2016-04-12 22:05:25", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(14).getText().trim(), DateUtils.date("2016-04-12 23:48:33", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(16).getText().trim(), DateUtils.date("2016-04-13 01:41:41", DEFAULT_LONG_DATE_FORMAT));
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "End Time");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), DateUtils.date("2016-04-13 01:41:41", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(4).getText().trim(), DateUtils.date("2016-04-12 23:48:33", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(6).getText().trim(), DateUtils.date("2016-04-12 22:05:25", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(8).getText().trim(), DateUtils.date("2016-04-12 20:22:16", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(10).getText().trim(), DateUtils.date("2016-04-12 18:39:17", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(12).getText().trim(), DateUtils.date("2016-04-12 16:56:19", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(14).getText().trim(), DateUtils.date("2016-04-12 15:13:21", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(16).getText().trim(), DateUtils.date("2016-04-12 13:30:24", DEFAULT_LONG_DATE_FORMAT));

        //Length sorting
        sortedElements = driver.findElements(By.cssSelector(".colt8"));
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Length");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "40,800");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "40,800");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "40,801");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "40,801");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "40,801");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "40,801");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "40,801");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "44,800");
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Length");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "44,800");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "40,801");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "40,801");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "40,801");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "40,801");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "40,801");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "40,800");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "40,800");

        //density sorting
        sortedElements = driver.findElements(By.cssSelector(".colt9"));
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Density");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "89.9");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "90.1");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "90.3");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "90.3");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "90.4");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "90.5");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "90.6");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "90.6");
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Density");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "90.6");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "90.6");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "90.5");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "90.4");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "90.3");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "90.3");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "90.1");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "89.9");

        //Product sorting
        sortedElements = driver.findElements(By.cssSelector(".colt10"));
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Product");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "25um Plain");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "25um Plain");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "25um Plain");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "25um Plain");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "25um Plain");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "25um Plain");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "25um Plain");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "25um Plain");
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Product");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), "25um Plain");
        Assert.assertEquals(sortedElements.get(4).getText().trim(), "25um Plain");
        Assert.assertEquals(sortedElements.get(6).getText().trim(), "25um Plain");
        Assert.assertEquals(sortedElements.get(8).getText().trim(), "25um Plain");
        Assert.assertEquals(sortedElements.get(10).getText().trim(), "25um Plain");
        Assert.assertEquals(sortedElements.get(12).getText().trim(), "25um Plain");
        Assert.assertEquals(sortedElements.get(14).getText().trim(), "25um Plain");
        Assert.assertEquals(sortedElements.get(16).getText().trim(), "25um Plain");

        //Start Time sorting
        sortedElements = driver.findElements(By.cssSelector(".colt11"));
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Start Time");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), DateUtils.date("2016-04-12 11:47:26", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(4).getText().trim(), DateUtils.date("2016-04-12 13:30:24", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(6).getText().trim(), DateUtils.date("2016-04-12 15:13:21", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(8).getText().trim(), DateUtils.date("2016-04-12 16:56:19", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(10).getText().trim(), DateUtils.date("2016-04-12 18:39:17", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(12).getText().trim(), DateUtils.date("2016-04-12 20:22:16", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(14).getText().trim(), DateUtils.date("2016-04-12 22:05:25", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(16).getText().trim(), DateUtils.date("2016-04-12 23:48:33", DEFAULT_LONG_DATE_FORMAT));
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Start Time");
        Assert.assertEquals(sortedElements.get(2).getText().trim(), DateUtils.date("2016-04-12 23:48:33", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(4).getText().trim(), DateUtils.date("2016-04-12 22:05:25", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(6).getText().trim(), DateUtils.date("2016-04-12 20:22:16", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(8).getText().trim(), DateUtils.date("2016-04-12 18:39:17", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(10).getText().trim(), DateUtils.date("2016-04-12 16:56:19", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(12).getText().trim(), DateUtils.date("2016-04-12 15:13:21", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(14).getText().trim(), DateUtils.date("2016-04-12 13:30:24", DEFAULT_LONG_DATE_FORMAT));
        Assert.assertEquals(sortedElements.get(16).getText().trim(), DateUtils.date("2016-04-12 11:47:26", DEFAULT_LONG_DATE_FORMAT));

        //Weight sorting
        sortedElements = driver.findElements(By.cssSelector(".colt12"));
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Weight");
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Weight");

        //Validity sorting
        sortedElements = driver.findElements(By.cssSelector(".colt13"));
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Rating");

        sortedElements = driver.findElements(By.cssSelector(".colt14"));
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Validity");

        sortedElements = driver.findElements(By.cssSelector(".colt15"));
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Thickness Avg");

        sortedElements = driver.findElements(By.cssSelector(".colt16"));
        sortedElements.get(1).click();
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "2 Sigma %");

        sortedElements.get(1).click();

    }

    @AfterMethod
    public void restoreApplicationSettings() throws IOException {
        getMongoUtils().executeScript(USER_SETTINGS_SCRIPT_NAME);
    }
}*/
