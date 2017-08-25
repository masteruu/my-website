package de.brueckner.fph.acceptance.componentsTimeline;


import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.MouseUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.TimelineManager.*;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE;

public class TimelinePanningBarAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(TimelinePanningBarAcceptanceTest.class);

    @Test
    public void dialogCloseEscapeKey() {

        logger.info("dialogCloseEscape");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //zoom on scale to less than one minute
        WebElement scale = getWebDriver().findElement(By.cssSelector(TIMELINE_SCALE));
        MouseUtils.dragAndDrop(scale, -1, getWebDriver());
        MouseUtils.dragAndDrop(scale, -1, getWebDriver());

        //close the notification box with escape key
        Keyboard escape = ((RemoteWebDriver) getWebDriver()).getKeyboard();
        escape.pressKey(Keys.ESCAPE);

        //check if the notification box closed
        try {
            WebElement close = getWebDriver().findElement(By.cssSelector(".dismiss"));
            close.click();
            Assert.fail();
            logger.info("The dialog did not close");
        } catch (NoSuchElementException e) {
            logger.info("The dialog is closed");
        }
    }

    @Test
    public void timelinePanningBarZoomAcceptance() {
        logger.info("timelinePanningBarZoomAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //check the initial panning bar
        WebElement panningBar = getWebDriver().findElement(By.cssSelector(TIMELINE_PANNING_BAR));
        Assert.assertEquals(panningBar.getAttribute("width"), "1607");

        //zoom in 6 times until the message ""Your time selection must be more than 1 minute!" appears
        WebElement scale = getWebDriver().findElement(By.cssSelector(TIMELINE_SCALE));

        MouseUtils.dragAndDrop(scale, -500, getWebDriver());
        timelineManager.waitTimelineSpinner();

        panningBar = getWebDriver().findElement(By.cssSelector(TIMELINE_PANNING_BAR));
        Assert.assertEquals(panningBar.getAttribute("width").substring(0, 5), "429.9");

        MouseUtils.dragAndDrop(scale, -1, getWebDriver());

        //check the info message "Your time selection must be more than 1 minute!"
        WebLocator messageBox = new WebLocator().setText("Your time selection must be more than 1 minute!");
        messageBox.assertReady();

    }

    @Test
    public void timelinePanningBarDragAndDropAcceptance() {
        logger.info("timelinePanningBarDragAndDropAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //drag panning bar 500 px to the left
        WebElement panningBar = getWebDriver().findElement(By.cssSelector(TIMELINE_PANNING_BAR));
        MouseUtils.dragAndDrop(panningBar, -500, getWebDriver());
        timelineManager.waitTimelineSpinner();

        //check if the new selection is ok
        checkDatepickers(DateUtils.date("2016-05-08 19:17:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE), DateUtils.date("2016-05-11 10:00:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE));

        //drag panning bar 500 px to the right
        panningBar = getWebDriver().findElement(By.cssSelector(TIMELINE_PANNING_BAR));
        MouseUtils.dragAndDrop(panningBar, 500, getWebDriver());
        timelineManager.waitTimelineSpinner();

        //check if the new selection is ok
        checkDatepickers(DateUtils.date("2016-05-08 19:17:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE), DateUtils.date("2016-05-11 11:52:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE));

    }

    void checkDatepickers(String dateLeft, String dateRight) {
        WebElement datePleft = getWebDriver().findElement(By.cssSelector(LEFT_DATEPICKER_OPEN_BUTTON_SELECTOR));
        String datePickerDateLeft = datePleft.getText();
        WebElement datePright = getWebDriver().findElement(By.cssSelector(RIGHT_DATEPICKER_OPEN_BUTTON_SELECTOR));
        String datePickerDateRight = datePright.getText();
        DateUtils.compareDates(datePickerDateLeft, dateLeft);
        DateUtils.compareDates(datePickerDateRight, dateRight);
    }
}