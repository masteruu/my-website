package de.brueckner.fph.acceptance.tables;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.MouseUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static de.brueckner.fph.managers.ContentManager.MATERIAL_TAB_TEXT;

public class MaterialTableLessDetailsAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(MaterialTableLessDetailsAcceptanceTest.class);

    @Test
    public void materialTableLessDetailsAcceptanceTest() {
        logger.info("materialTableLessDetailsAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-12 23:00:00", "2016-04-13 22:00:00");

        //click on a roll and check if the selection is done ok
        timelineManager.clickRoll("roll:A160413064950:1460530190433:1460535116127");
        contentManager.waitContentSpinner();

        contentManager.openTab(MATERIAL_TAB_TEXT);

        //check material table tooltip
        WebElement info = getWebDriver().findElement(By.cssSelector(".staticTableInfo"));
        MouseUtils.mouseOver(info, getWebDriver());
        WebElement tooltip = getWebDriver().findElement(By.cssSelector(".tooltip"));
        Assert.assertEquals(tooltip.getText(), "Stretching line perspective:\n" +
                "Recyclable & Non Recyclable rolls are part of Productive Quantity.");

        List<WebElement> tableHeader = getWebDriver().findElements(By.cssSelector(".ngHeaderText"));
        Assert.assertEquals(tableHeader.get(0).getText(), "Material");
        Assert.assertEquals(tableHeader.get(1).getText(), "Total Quantity [kg]");
        Assert.assertEquals(tableHeader.get(2).getText(), "Productive Quantity [kg]");
        Assert.assertEquals(tableHeader.get(3).getText(), "Non-Productive Quantity [kg]");

        List<WebElement> contentColumn = getWebDriver().findElements(By.cssSelector(".colt0"));
        Assert.assertEquals(contentColumn.get(2).getText().trim(), "*S1003(SH)");
        Assert.assertEquals(contentColumn.get(4).getText().trim(), "T38F(HD)");
        Assert.assertEquals(contentColumn.get(6).getText().trim(), "#T30(FD)");
        Assert.assertEquals(contentColumn.get(8).getText().trim(), "Undefined");
        Assert.assertEquals(contentColumn.get(10).getText().trim(), "#T30H(ZJG)");
        Assert.assertEquals(contentColumn.get(12).getText().trim(), "ABPP936");
        Assert.assertEquals(contentColumn.get(14).getText().trim(), "dirty");

        contentColumn = getWebDriver().findElements(By.cssSelector(".colt1"));
        Assert.assertEquals(contentColumn.get(2).getText().trim(), "3,069.8");
        Assert.assertEquals(contentColumn.get(4).getText().trim(), "2,046.5");
        Assert.assertEquals(contentColumn.get(6).getText().trim(), "525.1");
        Assert.assertEquals(contentColumn.get(8).getText().trim(), "334.7");
        Assert.assertEquals(contentColumn.get(10).getText().trim(), "0.0");
        Assert.assertEquals(contentColumn.get(12).getText().trim(), "0.0");
        Assert.assertEquals(contentColumn.get(14).getText().trim(), "0.0");

        contentColumn = getWebDriver().findElements(By.cssSelector(".colt2"));
        Assert.assertEquals(contentColumn.get(2).getText().trim(), "3,069.8");
        Assert.assertEquals(contentColumn.get(4).getText().trim(), "2,046.5");
        Assert.assertEquals(contentColumn.get(6).getText().trim(), "525.1");
        Assert.assertEquals(contentColumn.get(8).getText().trim(), "334.7");
        Assert.assertEquals(contentColumn.get(10).getText().trim(), "0.0");
        Assert.assertEquals(contentColumn.get(12).getText().trim(), "0.0");
        Assert.assertEquals(contentColumn.get(14).getText().trim(), "0.0");

        contentColumn = getWebDriver().findElements(By.cssSelector(".colt3"));
        Assert.assertEquals(contentColumn.get(2).getText().trim(), "0.0");
        Assert.assertEquals(contentColumn.get(4).getText().trim(), "0.0");
        Assert.assertEquals(contentColumn.get(6).getText().trim(), "0.0");
        Assert.assertEquals(contentColumn.get(8).getText().trim(), "0.0");
        Assert.assertEquals(contentColumn.get(10).getText().trim(), "0.0");
        Assert.assertEquals(contentColumn.get(12).getText().trim(), "0.0");
        Assert.assertEquals(contentColumn.get(14).getText().trim(), "0.0");

        WebElement kgScale = getWebDriver().findElement(By.cssSelector(".js-mbc__axis.mbc__axis"));
        Assert.assertEquals(kgScale.getText(), "0\n" +
                "500\n" +
                "1,000\n" +
                "1,500\n" +
                "2,000\n" +
                "2,500\n" +
                "3,000\n" +
                "3,500 kg");

        List<WebElement> textBars = getWebDriver().findElements(By.cssSelector(".nongood"));
        Assert.assertEquals(textBars.get(0).getText(), "*S1003(SH)");
        Assert.assertEquals(textBars.get(1).getText(), "T38F(HD)");
        Assert.assertEquals(textBars.get(2).getText(), "#T30(FD)");
        Assert.assertEquals(textBars.get(3).getText(), "Undefined");
        Assert.assertEquals(textBars.get(4).getText(), "Small Quantities");
        textBars.get(4).click();

        WebElement enter = getWebDriver().findElement(By.cssSelector(".enter"));
        Assert.assertEquals(enter.getText(), "#T30H(ZJG)\n" +
                "#T30H(ZJG)\n" +
                "ABPP936\n" +
                "ABPP936\n" +
                "dirty\n" +
                "dirty");

        List<WebElement> footer = getWebDriver().findElements(By.cssSelector(".footer"));
        Assert.assertEquals(footer.get(0).getText(), "Total Quantity 5,976.1 kg 5,976.1 kg 0 kg");

        WebElement legend = getWebDriver().findElement(By.cssSelector(".mbc__footer"));
        Assert.assertEquals(legend.getText().trim(), "Productive\n" +
                "Non-Productive");

    }

    @AfterMethod
    public void restoreApplicationSettings() throws IOException {
        getMongoUtils().executeScript(USER_SETTINGS_SCRIPT_NAME);
    }
}
