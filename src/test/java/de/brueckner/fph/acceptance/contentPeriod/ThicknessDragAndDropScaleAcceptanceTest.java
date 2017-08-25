package de.brueckner.fph.acceptance.contentPeriod;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.MouseUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ContentManager.THICKNESS_TAB_TEXT;
import static de.brueckner.fph.managers.TimelineManager.TIMELINE_TREND_CONTAINER;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR;

public class ThicknessDragAndDropScaleAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(ThicknessDragAndDropScaleAcceptanceTest.class);

    @Test
    public void thicknessDragAndDropScaleAcceptanceTest() {
        logger.info("ThicknessDragAndDropScaleAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-20 16:00:00", "2016-04-20 17:00:00");

        contentManager.openTab(THICKNESS_TAB_TEXT);

        //check axis
        WebElement xScale = getWebDriver().findElement(By.cssSelector(".x.axis.container.magnifierBrush"));
        Assert.assertEquals(xScale.getText().trim(), "20.04.1618:010 m\n" +
                DateUtils.date("2016-04-20 16:06:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "0 m\n" +
                DateUtils.date("2016-04-20 16:11:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "0 m\n" +
                DateUtils.date("2016-04-20 16:16:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "0 m\n" +
                DateUtils.date("2016-04-20 16:21:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "38 m\n" +
                DateUtils.date("2016-04-20 16:26:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "1517 m\n" +
                DateUtils.date("2016-04-20 16:31:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "1648 m\n" +
                DateUtils.date("2016-04-20 16:36:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "1767 m\n" +
                DateUtils.date("2016-04-20 16:41:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "1767 m\n" +
                DateUtils.date("2016-04-20 16:46:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "1767 m\n" +
                DateUtils.date("2016-04-20 16:51:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "1767 m\n" +
                DateUtils.date("2016-04-20 16:56:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "323 m\n" +
                DateUtils.date("2016-04-20 17:01:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "1544 m");

        //check panning bar
        MouseUtils.dragAndDrop(xScale, 500, getWebDriver());

        //check axis
        Assert.assertEquals(xScale.getText().trim(), "20.04.1618:311648 m\n" +
                DateUtils.date("2016-04-20 16:34:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "1727 m\n" +
                DateUtils.date("2016-04-20 16:37:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "1767 m\n" +
                DateUtils.date("2016-04-20 16:40:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "1767 m\n" +
                DateUtils.date("2016-04-20 16:43:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "1767 m\n" +
                DateUtils.date("2016-04-20 16:46:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "1767 m\n" +
                DateUtils.date("2016-04-20 16:49:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "1767 m\n" +
                DateUtils.date("2016-04-20 16:52:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "1767 m\n" +
                DateUtils.date("2016-04-20 16:55:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "888 m\n" +
                DateUtils.date("2016-04-20 16:58:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "1017 m\n" +
                DateUtils.date("2016-04-20 17:01:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "1544 m");

        //check with three opened
        treeManager.openTree();

        MouseUtils.dragAndDrop(xScale, 500, getWebDriver());

        //check axis
        Assert.assertEquals(xScale.getText().trim(), "20.04.1618:461767 m\n" +
                DateUtils.date("2016-04-20 16:48:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "1767 m\n" +
                DateUtils.date("2016-04-20 16:50:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "1767 m\n" +
                DateUtils.date("2016-04-20 16:52:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "1767 m\n" +
                DateUtils.date("2016-04-20 16:54:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "1767 m\n" +
                DateUtils.date("2016-04-20 16:56:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "323 m\n" +
                DateUtils.date("2016-04-20 16:58:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "1017 m\n" +
                DateUtils.date("2016-04-20 17:00:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "1544 m");


        //check with production trend opened
        Keyboard shortcut = ((RemoteWebDriver) getWebDriver()).getKeyboard();
        shortcut.pressKey(Keys.SHIFT + "p");

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(TIMELINE_TREND_CONTAINER)));

        MouseUtils.dragAndDrop(xScale, 500, getWebDriver());

        //check axis
        Assert.assertEquals(xScale.getText().trim(), "20.04.1618:531767 m\n" +
                DateUtils.date("2016-04-20 16:54:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "1767 m\n" +
                DateUtils.date("2016-04-20 16:55:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "888 m\n" +
                DateUtils.date("2016-04-20 16:56:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "323 m\n" +
                DateUtils.date("2016-04-20 16:57:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "669 m\n" +
                DateUtils.date("2016-04-20 16:58:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "1017 m\n" +
                DateUtils.date("2016-04-20 16:59:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "1363 m\n" +
                DateUtils.date("2016-04-20 17:00:00", DEFAULT_SHORT_DATE_FORMAT_NO_SEPARATOR) + "1544 m");

    }
}
