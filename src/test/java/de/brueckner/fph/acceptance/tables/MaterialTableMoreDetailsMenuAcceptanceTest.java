/*
package de.brueckner.fph.acceptance.tables;

import com.sdl.selenium.web.WebLocator;
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

public class MaterialTableMoreDetailsMenuAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(MaterialTableMoreDetailsMenuAcceptanceTest.class);

    @Test
    public void materialTableMoreDetailsMenuAcceptanceTest() {
        logger.info("materialTableMoreDetailsMenuAcceptanceTest");
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

        List<WebElement> menu = driver.findElements(By.cssSelector(".stripes"));
        menu.get(1).click();

        //show filters
        List<WebElement> showFilters = driver.findElements(By.cssSelector(".tableMenu li:nth-child(1)"));
        showFilters.get(3).click();
        List<WebElement> filters = driver.findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("TEST");

        //reset filters
        List<WebElement> resetFilters = driver.findElements(By.cssSelector(".tableMenu li:nth-child(2)"));
        resetFilters.get(3).click();
        List<WebElement> footer = driver.findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(1).getText(), "Total Quantity 5,976.1 kg");
        showFilters = driver.findElements(By.cssSelector(".tableMenu li:nth-child(1)"));
        showFilters.get(3).click();

        //enable grouping
        List<WebElement> enableGrouping = driver.findElements(By.cssSelector(".tableMenu li:nth-child(3)"));
        enableGrouping.get(3).click();
        WebElement grouping = driver.findElement(By.cssSelector(".calculatedDecorator.ng-hide"));
        enableGrouping = driver.findElements(By.cssSelector(".tableMenu li:nth-child(3)"));
        enableGrouping.get(3).click();

        //customize headers
        List<WebElement> customizeHeaders = driver.findElements(By.cssSelector(".tableMenu li:nth-child(4)"));
        customizeHeaders.get(2).click();

        List<WebElement> quality = driver.findElements(By.cssSelector(".ngColList li:nth-child(1)"));
        quality.get(1).click();
        List<WebElement> validity = driver.findElements(By.cssSelector(".ngColList li:nth-child(2)"));
        validity.get(1).click();
        List<WebElement> prod = driver.findElements(By.cssSelector(".ngColList li:nth-child(3)"));
        prod.get(1).click();
        List<WebElement> dosing = driver.findElements(By.cssSelector(".ngColList li:nth-child(4)"));
        dosing.get(1).click();
        WebElement extruder = driver.findElement(By.cssSelector(".ngColList li:nth-child(5)"));
        extruder.click();
        WebElement rollI = driver.findElement(By.cssSelector(".ngColList li:nth-child(6)"));
        rollI.click();
        WebElement material = driver.findElement(By.cssSelector(".ngColList li:nth-child(7)"));
        material.click();
        WebElement quantity = driver.findElement(By.cssSelector(".ngColList li:nth-child(8)"));
        quantity.click();
        WebElement lineS = driver.findElement(By.cssSelector(".ngColList li:nth-child(9)"));
        lineS.click();

        quality = driver.findElements(By.cssSelector(".ngColList li:nth-child(1)"));
        quality.get(1).click();
        List<WebElement> filter1 = driver.findElements(By.cssSelector(".colt0"));
        Assert.assertEquals(filter1.get(31).getText().trim(), "Quality");
        validity = driver.findElements(By.cssSelector(".ngColList li:nth-child(2)"));
        validity.get(1).click();
        List<WebElement> filter2 = driver.findElements(By.cssSelector(".colt1"));
        Assert.assertEquals(filter2.get(31).getText().trim(), "Validity");
        prod = driver.findElements(By.cssSelector(".ngColList li:nth-child(3)"));
        prod.get(1).click();

        List<WebElement> filter3 = driver.findElements(By.cssSelector(".colt2"));
        Assert.assertEquals(filter3.get(31).getText().trim(), "Productivity");
        dosing = driver.findElements(By.cssSelector(".ngColList li:nth-child(4)"));
        dosing.get(1).click();
        List<WebElement> filter4 = driver.findElements(By.cssSelector(".colt3"));
        Assert.assertEquals(filter4.get(31).getText().trim(), "Dosing");
        extruder = driver.findElement(By.cssSelector(".ngColList li:nth-child(5)"));
        extruder.click();
        List<WebElement> filter5 = driver.findElements(By.cssSelector(".colt4"));
        Assert.assertEquals(filter5.get(1).getText().trim(), "Extruder");
        rollI = driver.findElement(By.cssSelector(".ngColList li:nth-child(6)"));
        rollI.click();
        List<WebElement> filter6 = driver.findElements(By.cssSelector(".colt5"));
        Assert.assertEquals(filter6.get(1).getText().trim(), "Roll ID");

        material = driver.findElement(By.cssSelector(".ngColList li:nth-child(7)"));
        material.click();
        List<WebElement> filter7 = driver.findElements(By.cssSelector(".colt6"));
        Assert.assertEquals(filter7.get(1).getText().trim(), "Material");
        quantity = driver.findElement(By.cssSelector(".ngColList li:nth-child(8)"));
        quantity.click();
        List<WebElement> filter8 = driver.findElements(By.cssSelector(".colt7"));
        Assert.assertEquals(filter8.get(1).getText().trim(), "Quantity[kg]");
        lineS = driver.findElement(By.cssSelector(".ngColList li:nth-child(9)"));
        lineS.click();
        List<WebElement> filter9 = driver.findElements(By.cssSelector(".colt8"));
        Assert.assertEquals(filter9.get(1).getText().trim(), "Line Status");

        //export
        List<WebElement> export = driver.findElements(By.cssSelector(".tableMenu li:nth-child(5)"));
        export.get(2).click();
        Utils.sleep(1000);

        //pdf
        List<WebElement> pdf = driver.findElements(By.cssSelector("li:nth-child(1)"));
        pdf.get(15).click();
        WebLocator close = new WebLocator().setClasses("close").setRenderMillis(10000);
        close.click();
        Utils.sleep(1500);

        //csv
        menu = driver.findElements(By.cssSelector(".stripes"));
        menu.get(1).click();
        export = driver.findElements(By.cssSelector(".tableMenu li:nth-child(5)"));
        export.get(2).click();
        List<WebElement> csv = driver.findElements(By.cssSelector("li:nth-child(2)"));
        csv.get(15).click();
        Utils.sleep(1500);

        //excel
        export = driver.findElements(By.cssSelector(".tableMenu li:nth-child(5)"));
        export.get(2).click();
        List<WebElement> excel = driver.findElements(By.cssSelector("li:nth-child(3)"));
        excel.get(15).click();
    }

    @AfterMethod
    public void restoreApplicationSettings() throws IOException {
        getMongoUtils().executeScript(USER_SETTINGS_SCRIPT_NAME);
    }
}*/
