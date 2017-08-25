package de.brueckner.fph.acceptance.contentRoll;

import de.brueckner.fph.acceptance.applicationSettings.AbstractApplicationSettingsTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.MouseUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR;

public class RollThicknessTrendDragAndDropScaleAcceptanceTest extends AbstractApplicationSettingsTest {

    /**
     * The name of the roll used in tests
     */
    static final String CONST_ROLL_NAME = "A 160413 1449";
    /**
     * The UID of the roll used in tests
     */
    static final String CONST_ROLL_UID = "roll:A160413064950:1460530190433:1460535116127";
    private static final Logger logger = LoggerFactory.getLogger(RollThicknessTrendDragAndDropScaleAcceptanceTest.class);

    @Test
    public void rollThicknessTrendDragAndDropScaleAcceptanceTest() throws IOException {
        logger.info("RollThicknessTrendDragAndDropScaleAcceptanceTest");
        //perform the login
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());
        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-12 23:00:00", "2016-04-13 22:00:00");

        //click on a roll and check
        timelineManager.clickRoll(CONST_ROLL_UID);
        contentManager.waitContentSpinner();

        //check axis
        WebElement xScale = getWebDriver().findElement(By.cssSelector(".x.axis.container.magnifierBrush"));
        Assert.assertEquals(xScale.getText().trim(), "13.04.1608:50227 m\n" +
                DateUtils.date("2016-04-13 06:55:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "2059 m\n" +
                DateUtils.date("2016-04-13 07:00:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "4059 m\n" +
                DateUtils.date("2016-04-13 07:05:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "6060 m\n" +
                DateUtils.date("2016-04-13 07:10:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "8057 m\n" +
                DateUtils.date("2016-04-13 07:15:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "10057 m\n" +
                DateUtils.date("2016-04-13 07:20:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "12058 m\n" +
                DateUtils.date("2016-04-13 07:25:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "14059 m\n" +
                DateUtils.date("2016-04-13 07:30:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "16059 m\n" +
                DateUtils.date("2016-04-13 07:35:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "18059 m\n" +
                DateUtils.date("2016-04-13 07:40:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "20056 m\n" +
                DateUtils.date("2016-04-13 07:45:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "22054 m\n" +
                DateUtils.date("2016-04-13 07:50:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "24053 m\n" +
                DateUtils.date("2016-04-13 07:55:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "26052 m\n" +
                DateUtils.date("2016-04-13 08:00:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "28051 m\n" +
                DateUtils.date("2016-04-13 08:05:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "30049 m\n" +
                DateUtils.date("2016-04-13 08:10:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "32046 m");

        //check panning bar
        WebElement thicknessPanningBar = getWebDriver().findElement(By.cssSelector(".thicknessTrendPanningContainer .paddingElementContainer"));
        Assert.assertEquals(thicknessPanningBar.getAttribute("width").substring(0, 3), "965");

        MouseUtils.dragAndDrop(xScale, 500, getWebDriver());
        contentManager.waitContentSpinner();

        //check axis
        Assert.assertEquals(xScale.getText().trim(), "13.04.1609:3116464 m\n" +
                DateUtils.date("2016-04-13 07:34:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "17662 m\n" +
                DateUtils.date("2016-04-13 07:37:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "18858 m\n" +
                DateUtils.date("2016-04-13 07:40:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "20056 m\n" +
                DateUtils.date("2016-04-13 07:43:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "21258 m\n" +
                DateUtils.date("2016-04-13 07:46:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "22460 m\n" +
                DateUtils.date("2016-04-13 07:49:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "23652 m\n" +
                DateUtils.date("2016-04-13 07:52:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "24855 m\n" +
                DateUtils.date("2016-04-13 07:55:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "26052 m\n" +
                DateUtils.date("2016-04-13 07:58:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "27253 m\n" +
                DateUtils.date("2016-04-13 08:01:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "28452 m\n" +
                DateUtils.date("2016-04-13 08:04:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "29649 m\n" +
                DateUtils.date("2016-04-13 08:07:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "30845 m\n" +
                DateUtils.date("2016-04-13 08:10:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "32046 m");

        //check panning bar
        thicknessPanningBar = getWebDriver().findElement(By.cssSelector(".thicknessTrendPanningContainer .paddingElementContainer"));
        Assert.assertEquals(thicknessPanningBar.getAttribute("width").substring(0, 3), "489");

        //check with three opened
        treeManager.openTree();

        MouseUtils.dragAndDrop(xScale, 500, getWebDriver());
        contentManager.waitContentSpinner();

        //check axis
        Assert.assertEquals(xScale.getText().trim(), "13.04.1609:5124452 m\n" +
                DateUtils.date("2016-04-13 07:53:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "25255 m\n" +
                DateUtils.date("2016-04-13 07:55:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "26052 m\n" +
                DateUtils.date("2016-04-13 07:57:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "26853 m\n" +
                DateUtils.date("2016-04-13 07:59:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "27653 m\n" +
                DateUtils.date("2016-04-13 08:01:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "28452 m\n" +
                DateUtils.date("2016-04-13 08:03:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "29251 m\n" +
                DateUtils.date("2016-04-13 08:05:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "30049 m\n" +
                DateUtils.date("2016-04-13 08:07:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "30845 m\n" +
                DateUtils.date("2016-04-13 08:09:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "31649 m\n" +
                DateUtils.date("2016-04-13 08:11:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "32423 m");

        //check panning bar
        thicknessPanningBar = getWebDriver().findElement(By.cssSelector(".thicknessTrendPanningContainer .paddingElementContainer"));
        Assert.assertEquals(thicknessPanningBar.getAttribute("width").substring(0, 3), "245");

        //check with production trend opened
        Keyboard shortcut = ((RemoteWebDriver) getWebDriver()).getKeyboard();
        shortcut.pressKey(Keys.SHIFT + "p");
        timelineManager.waitTimelineSpinner();

        MouseUtils.dragAndDrop(xScale, 500, getWebDriver());
        contentManager.waitContentSpinner();

        //check axis
        Assert.assertEquals(xScale.getText().trim(), "13.04.1610:0128732 m\n" +
                DateUtils.date("2016-04-13 08:02:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "29052 m\n" +
                DateUtils.date("2016-04-13 08:03:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "29449 m\n" +
                DateUtils.date("2016-04-13 08:04:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "29849 m\n" +
                DateUtils.date("2016-04-13 08:05:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "30248 m\n" +
                DateUtils.date("2016-04-13 08:06:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "30647 m\n" +
                DateUtils.date("2016-04-13 08:07:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "31043 m\n" +
                DateUtils.date("2016-04-13 08:08:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "31448 m\n" +
                DateUtils.date("2016-04-13 08:09:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "31848 m\n" +
                DateUtils.date("2016-04-13 08:10:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "32246 m\n" +
                DateUtils.date("2016-04-13 08:11:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "32556 m");

        //check panning bar
        thicknessPanningBar = getWebDriver().findElement(By.cssSelector(".thicknessTrendPanningContainer .paddingElementContainer"));
        Assert.assertEquals(thicknessPanningBar.getAttribute("width").substring(0, 3), "124");
    }
}
