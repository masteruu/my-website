package de.brueckner.fph.acceptance.componentsHeader;

import de.brueckner.fph.acceptance.applicationSettings.AbstractApplicationSettingsTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ContentManager.*;
import static de.brueckner.fph.managers.TimelineManager.LEFT_DATEPICKER_OPEN_BUTTON_SELECTOR;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT;

public class HeaderButtonBackToLastViewAcceptanceTest extends AbstractApplicationSettingsTest {
    private static final Logger logger = LoggerFactory.getLogger(HeaderButtonBackToLastViewAcceptanceTest.class);


    @Test
    public void headerBackToLastViewAcceptance() {
        logger.info("headerBackToLastViewAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-13 22:00:00");
        contentManager.waitContentSpinner();

        //check tooltip
        WebElement historyBack = getWebDriver().findElement(By.cssSelector(BACK_TO_LAST_VIEW_HEADER_BUTTON));
        Assert.assertEquals(applicationManager.getTooltipTextForHeaderButtons(historyBack), "BACK TO LAST VIEW B");

        historyBack.click();

        contentManager.waitContentSpinner();

        WebElement datePleft = getWebDriver().findElement(By.cssSelector(LEFT_DATEPICKER_OPEN_BUTTON_SELECTOR));
        String datePickerDateLeft = datePleft.getText().replace("  ", " ");

        //check datepicker dates to match content dates
        WebElement contentStartPeriod = getWebDriver().findElement(By.cssSelector(CONTENT_START_PERIOD));
        String dateStart = contentStartPeriod.getText().trim();
        WebElement contentEndPeriod = getWebDriver().findElement(By.cssSelector(CONTENT_END_PERIOD));
        String dateEnd = contentEndPeriod.getText().trim();

        DateUtils.compareDates(datePickerDateLeft, dateStart);
        DateUtils.compareDates("...", dateEnd);

        //check shortcut
        timelineManager.clickCampaign("campaign:160509041329419:1462767209419:null");
        contentManager.waitContentSpinner();

        Keyboard note = ((RemoteWebDriver) getWebDriver()).getKeyboard();
        note.pressKey(Keys.SHIFT + "B");

        contentManager.waitContentSpinner();

        DateUtils.compareDates(DateUtils.date("2016-05-09 10:00:00", DEFAULT_SHORT_DATE_FORMAT), dateStart);
        DateUtils.compareDates("...", dateEnd);
    }
}