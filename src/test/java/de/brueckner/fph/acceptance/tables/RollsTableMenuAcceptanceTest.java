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

public class RollsTableMenuAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(RollsTableMenuAcceptanceTest.class);

    @Test
    public void rollsTableMenuAcceptance() {
        logger.info("rollsTableMenuAcceptance");
        ActionsUtils.loginPage(LoginCredentials.USER_DEV, LoginCredentials.PASSWORD);

        //setting the date picker to a specific date
        dateUtils.setDatePicker("2016-04-12 11:47:00", "2016-04-13 10:47:00");

        //click on a campaign and check if the selection is done ok
        ActionsUtils.clickCampaign("campaign:160412114726310:1460461646310:1460511701759");
        ActionsUtils.waitSpinner();
        WebElement rolls = driver.findElement(By.cssSelector(".mainContentTabs li:nth-child(2)"));
        rolls.click();
        ActionsUtils.waitSpinner();

        List<WebElement> roll = driver.findElements(By.cssSelector(".colt2"));
        roll.get(2).click();
        ActionsUtils.waitSpinner();

        //check if the selection is ok in breadcrum
        WebElement breadCrumbContent = driver.findElement(By.cssSelector(".breadcrumb li:nth-child(1)"));
        Assert.assertEquals(breadCrumbContent.getText(), "Frd");
        breadCrumbContent = driver.findElement(By.cssSelector(".breadcrumb li:nth-child(2)"));
        Assert.assertEquals(breadCrumbContent.getText(), "160411_11T25u_400m_4.1t");
        breadCrumbContent = driver.findElement(By.cssSelector(".breadcrumb li:nth-child(3)"));
        Assert.assertEquals(breadCrumbContent.getText(), "PRODUCTIVE");
        breadCrumbContent = driver.findElement(By.cssSelector(".breadcrumb li:nth-child(4)"));
        Assert.assertEquals(breadCrumbContent.getText(), "A 160413 0748");

        WebElement historyBack = driver.findElement(By.cssSelector(".historyBack"));
        historyBack.click();
        ActionsUtils.waitSpinner();
        roll = driver.findElements(By.cssSelector(".colt2"));
        Assert.assertEquals(roll.get(2).getText().trim(), "A 160413 0748");

        WebElement menu = driver.findElement(By.cssSelector(".stripes"));
        menu.click();

        //show filters
        List<WebElement> showFilters = driver.findElements(By.cssSelector(".tableMenu li:nth-child(1)"));
        showFilters.get(0).click();
        List<WebElement> filters = driver.findElements(By.cssSelector(".filterColumnInput"));
        filters.get(0).sendKeys("TEST");

        //reset filters
        List<WebElement> resetFilters = driver.findElements(By.cssSelector(".tableMenu li:nth-child(2)"));
        resetFilters.get(0).click();
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

        WebElement header = driver.findElement(By.cssSelector(".ngColList li:nth-child(1)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(2)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(3)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(4)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(5)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(6)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(7)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(8)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(9)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(10)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(11)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(12)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(13)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(14)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(15)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(16)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(17)"));
        header.click();
        Utils.sleep(500);
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(1)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(2)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(3)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(4)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(5)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(6)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(7)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(8)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(9)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(10)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(11)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(12)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(13)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(14)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(15)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(16)"));
        header.click();
        header = driver.findElement(By.cssSelector(".ngColList li:nth-child(17)"));
        header.click();
        Utils.sleep(700);
        List<WebElement> sortedElements = driver.findElements(By.cssSelector(".colt0"));
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Visual Quality");
        sortedElements = driver.findElements(By.cssSelector(".colt1"));
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Lab Quality");
        sortedElements = driver.findElements(By.cssSelector(".colt2"));
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "ID");
        sortedElements = driver.findElements(By.cssSelector(".colt3"));
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "T. Inside");
        sortedElements = driver.findElements(By.cssSelector(".colt4"));
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "T. Outside");
        sortedElements = driver.findElements(By.cssSelector(".colt6"));
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Thickness Set");
        sortedElements = driver.findElements(By.cssSelector(".colt7"));
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "End Time");
        sortedElements = driver.findElements(By.cssSelector(".colt8"));
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Length");
        sortedElements = driver.findElements(By.cssSelector(".colt9"));
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Density");
        sortedElements = driver.findElements(By.cssSelector(".colt10"));
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Product");
        sortedElements = driver.findElements(By.cssSelector(".colt11"));
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Start Time");
        sortedElements = driver.findElements(By.cssSelector(".colt13"));
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Rating");
        sortedElements = driver.findElements(By.cssSelector(".colt14"));
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Validity");
        sortedElements = driver.findElements(By.cssSelector(".colt15"));
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "Thickness Avg");
        sortedElements = driver.findElements(By.cssSelector(".colt16"));
        Assert.assertEquals(sortedElements.get(1).getText().trim(), "2 Sigma %");

        menu = driver.findElement(By.cssSelector(".stripes"));
        menu.click();
        menu = driver.findElement(By.cssSelector(".stripes"));
        menu.click();

        //export
        List<WebElement> export = driver.findElements(By.cssSelector(".tableMenu li:nth-child(5)"));
        export.get(1).click();

        //pdf
        List<WebElement> pdf = driver.findElements(By.cssSelector("li:nth-child(1)"));
        pdf.get(12).click();
        WebLocator close = new WebLocator().setClasses("close").setRenderMillis(10000);
        close.click();
        Utils.sleep(1500);

        //csv
        menu = driver.findElement(By.cssSelector(".stripes"));
        menu.click();
        export = driver.findElements(By.cssSelector(".tableMenu li:nth-child(5)"));
        export.get(1).click();
        List<WebElement> csv = driver.findElements(By.cssSelector("li:nth-child(2)"));
        csv.get(12).click();
        Utils.sleep(1500);

        //excel
        export = driver.findElements(By.cssSelector(".tableMenu li:nth-child(5)"));
        export.get(1).click();
        List<WebElement> excel = driver.findElements(By.cssSelector("li:nth-child(3)"));
        excel.get(11).click();
    }

    @AfterMethod
    public void restoreApplicationSettings() throws IOException {
        getMongoUtils().executeScript(USER_SETTINGS_SCRIPT_NAME);
    }
}*/
