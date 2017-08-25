package de.brueckner.fph.acceptance.contentPeriod;


import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE;

public class PeriodProductsTabAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(PeriodProductsTabAcceptanceTest.class);

    @Test
    public void periodProductsTabAcceptanceTest() {
        logger.info("PeriodProductsTabAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());


        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-13 22:00:00");

        //check breadcrum
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", DateUtils.date("2016-04-12 22:00:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE) + "  -  " + DateUtils.date("2016-04-13 22:00:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE)));

        //products table
        List<WebElement> row0 = getWebDriver().findElements(By.cssSelector(".colt0"));
        Assert.assertEquals(row0.get(1).getText().trim(), "Product");
        Assert.assertEquals(row0.get(2).getText().trim(), "25um Plain");
        Assert.assertEquals(row0.get(3).getText().trim(), "23um Plain");

        List<WebElement> row1 = getWebDriver().findElements(By.cssSelector(".colt1"));
        Assert.assertEquals(row1.get(1).getText().trim(), "Quantity Total /Good");
        Assert.assertEquals(row1.get(2).getText().trim(), "17.55 t\n" +
                "17.55 t");
        Assert.assertEquals(row1.get(3).getText().trim(), "84.24 t\n" +
                "82.85 t");

        List<WebElement> row2 = getWebDriver().findElements(By.cssSelector(".colt2"));
        Assert.assertEquals(row2.get(1).getText().trim(), "Time Productive / Distribution");
        Assert.assertEquals(row2.get(2).getText().trim(), "100 %\n" +
                "15.40 %");
        Assert.assertEquals(row2.get(3).getText().trim(), "92.47 %\n" +
                "84.60 %");

        WebElement footer = getWebDriver().findElement(By.cssSelector(".footer"));
        Assert.assertEquals(footer.getText(), "Total 101.79 t\n" +
                "100.40 t\n" +
                "100.00 %");

        //tooltip quantity total/ good
        String tooltipText = applicationManager.getTooltipContainerText(".colt1 .tabFormulaInfo");

        Assert.assertEquals(tooltipText, "Total\n" +
                "Total quantity in t consumed to produce the product\n" +
                "Good\n" +
                "Total quantity in t of good and pending rolls of the respective product");

        //tooltip time productive/ distribution
        tooltipText = applicationManager.getTooltipContainerText(".colt2 .tabFormulaInfo");
        Assert.assertEquals(tooltipText, "Productive\n" +
                "Total time spend for producing good and pending rolls of the product relative to the total time spend for producing the product (in %)\n" +
                "Distribution\n" +
                "Total time spend for producing the product relative to the selected time period (in %)");

        //sort table by product
        row0 = getWebDriver().findElements(By.cssSelector(".colt0"));
        row0.get(1).click();

        WebElement checkSortIcon = getWebDriver().findElement(By.cssSelector(".ngSortButtonDown.isSorted"));
        Assert.assertEquals(row0.get(1).getText().trim(), "Product");
        Assert.assertEquals(row0.get(2).getText().trim(), "23um Plain");
        Assert.assertEquals(row0.get(3).getText().trim(), "25um Plain");
        row0.get(1).click();
        checkSortIcon = getWebDriver().findElement(By.cssSelector(".ngSortButtonUp.isSorted"));
        Assert.assertEquals(row0.get(2).getText().trim(), "25um Plain");
        Assert.assertEquals(row0.get(3).getText().trim(), "23um Plain");

        //sort table by quantity total/ good
        row1 = getWebDriver().findElements(By.cssSelector(".colt1"));
        row1.get(1).click();

        checkSortIcon = getWebDriver().findElement(By.cssSelector(".ngSortButtonDown.isSorted"));
        Assert.assertEquals(row1.get(1).getText().trim(), "Quantity Total /Good");
        Assert.assertEquals(row1.get(2).getText().trim(), "17.55 t\n" +
                "17.55 t");
        Assert.assertEquals(row1.get(3).getText().trim(), "84.24 t\n" +
                "82.85 t");
        row1.get(1).click();
        checkSortIcon = getWebDriver().findElement(By.cssSelector(".ngSortButtonUp.isSorted"));
        Assert.assertEquals(row1.get(2).getText().trim(), "84.24 t\n" +
                "82.85 t");
        Assert.assertEquals(row1.get(3).getText().trim(), "17.55 t\n" +
                "17.55 t");

        //sort table by time productive/ distribution
        row2 = getWebDriver().findElements(By.cssSelector(".colt2"));
        row2.get(1).click();

        checkSortIcon = getWebDriver().findElement(By.cssSelector(".ngSortButtonDown.isSorted"));
        Assert.assertEquals(row2.get(1).getText().trim(), "Time Productive / Distribution");
        Assert.assertEquals(row2.get(3).getText().trim(), "100 %\n" +
                "15.40 %");
        Assert.assertEquals(row2.get(2).getText().trim(), "92.47 %\n" +
                "84.60 %");
        row2.get(1).click();
        checkSortIcon = getWebDriver().findElement(By.cssSelector(".ngSortButtonUp.isSorted"));
        Assert.assertEquals(row2.get(1).getText().trim(), "Time Productive / Distribution");
        Assert.assertEquals(row2.get(2).getText().trim(), "100 %\n" +
                "15.40 %");
        Assert.assertEquals(row2.get(3).getText().trim(), "92.47 %\n" +
                "84.60 %");

    }

}