/*
package de.brueckner.fph.acceptance.tables;


import com.sdl.selenium.web.utils.Utils;
import de.brueckner.fph.oldtests.acceptance.appConfig.AbstractAcceptanceTest;
import de.brueckner.fph.oldtests.acceptance.appConfig.LoginCredentials;
import de.brueckner.fph.oldtests.util.ActionsUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class MaterialTableMoreDetailsUngroupHeadersAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(MaterialTableMoreDetailsUngroupHeadersAcceptanceTest.class);

    @Test
    public void materialTableMoreDetailsUngroupHeadersAcceptanceTest() {
        logger.info("materialTableMoreDetailsUngroupHeadersAcceptanceTest");
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
        WebElement viewMoreButton = driver.findElement(By.cssSelector(".viewMoreButton"));
        viewMoreButton.click();
        ActionsUtils.waitSpinner();

        List<WebElement> ngRemoveGroup = driver.findElements(By.cssSelector(".ngRemoveGroup"));
        ngRemoveGroup.get(2).click();

        List<WebElement> material = driver.findElements(By.cssSelector(".colt7"));
        material.get(0).click();
        Utils.sleep(1000);
        WebElement sorted = driver.findElement(By.cssSelector(".colt7 .ngSortButtonDown.isSorted"));
        sorted.click();

        ngRemoveGroup = driver.findElements(By.cssSelector(".ngRemoveGroup"));
        ngRemoveGroup.get(1).click();

        List<WebElement> prod = driver.findElements(By.cssSelector(".colt2"));
        prod.get(31).click();
        Utils.sleep(1000);
        sorted = driver.findElement(By.cssSelector(".colt2 .ngSortButtonDown.isSorted"));
        sorted.click();
    }

    @AfterMethod
    public void restoreApplicationSettings() throws IOException {
        getMongoUtils().executeScript(USER_SETTINGS_SCRIPT_NAME);
    }
}*/
