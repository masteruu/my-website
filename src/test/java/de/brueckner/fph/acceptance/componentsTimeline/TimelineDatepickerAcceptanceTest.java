package de.brueckner.fph.acceptance.componentsTimeline;

import com.sdl.selenium.web.WebLocator;
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

import java.util.Arrays;
import java.util.List;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.TimelineManager.*;
import static de.brueckner.fph.managers.TreeManager.TREE_DATEPICKER_TEXT_SELECTOR;
import static de.brueckner.fph.util.DateUtils.DEFAULT_LONG_DATE_SHORT_TIME_FORMAT;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE;

public class TimelineDatepickerAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(TimelineDatepickerAcceptanceTest.class);

    @Test
    public void timelineDatepickerNavigationAcceptanceTest() {
        logger.info("timelineDatepickerNavigationAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        WebElement leftSideText = getWebDriver().findElement(By.cssSelector(LEFT_DATEPICKER_OPEN_BUTTON_SELECTOR));
        leftSideText.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.className(DATEPICKER_MODAL)));

        checkDatepickersArrows();

        //day on the left side
        List<WebElement> leftDay = getWebDriver().findElements(By.cssSelector(".daterangepicker .left .available"));
        leftDay.get(15).click();

        //day on the left side
        List<WebElement> rightDay = getWebDriver().findElements(By.cssSelector(".daterangepicker .right .available"));
        rightDay.get(2).click();

        setHourMinuteLeftSide();
        setHourMinuteRightSide();


        applicationManager.pressEnter();
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.invisibilityOfElementLocated(By.className(DATEPICKER_MODAL)));
        contentManager.waitContentSpinner();

        //check left and right datepickers
        WebElement leftDatepicker = getWebDriver().findElement(By.cssSelector(LEFT_DATEPICKER_OPEN_BUTTON_SELECTOR));
        Assert.assertEquals(leftDatepicker.getText(), DateUtils.date("2016-05-08 11:59:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE));

        WebElement rightDatepicker = getWebDriver().findElement(By.cssSelector(RIGHT_DATEPICKER_OPEN_BUTTON_SELECTOR));
        Assert.assertEquals(rightDatepicker.getText(), DateUtils.date("2016-05-09 20:19:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE));

        //check if the selection is ok in breadcrum
        breadcrumbManager.checkBreadcrumb(Arrays.asList("Frd", DateUtils.date("2016-05-08 11:59:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE) + "  -  " + DateUtils.date("2016-05-09 20:19:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE)));

        //check tree datepicker
        treeManager.openTree();
        WebElement treeDatepicker = getWebDriver().findElement(By.cssSelector(TREE_DATEPICKER_TEXT_SELECTOR));
        Assert.assertEquals(treeDatepicker.getText(), DateUtils.date("2016-05-08 11:59:00", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT) + "  -  " + DateUtils.date("2016-05-09 20:19:00", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));

    }

    @Test
    public void timelineDatepickerDifferentCasesAcceptanceTest() {
        logger.info("timelineDatepickerDifferentCasesAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //check tooltip
        WebElement datepickerTooltip = getWebDriver().findElement(By.cssSelector(LEFT_DATEPICKER_OPEN_BUTTON_SELECTOR));
        MouseUtils.mouseOver(datepickerTooltip, getWebDriver());

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.className("datepickerTooltip")));

        WebElement tooltip = getWebDriver().findElement(By.cssSelector(".datepickerTooltip"));
        Assert.assertEquals(tooltip.getText(), "OPEN DATEPICKERD");

        //check shortcut
        Keyboard openDatepicker = ((RemoteWebDriver) getWebDriver()).getKeyboard();
        openDatepicker.pressKey(Keys.SHIFT + "d");

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.className(DATEPICKER_MODAL)));

        WebElement close = getWebDriver().findElement(By.cssSelector(DATEPICKER_CLOSE_BUTTON_SELECTOR));
        close.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.invisibilityOfElementLocated(By.className(DATEPICKER_MODAL)));

        //check left date to be smaller than the right date
        //set date on the left to 29.06.16  12:00
        //check date on the right to be 29.06.16  12:01

        WebElement datepicker = getWebDriver().findElement(By.cssSelector(LEFT_DATEPICKER_OPEN_BUTTON_SELECTOR));
        datepicker.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.className(DATEPICKER_MODAL)));

        WebElement leftMonthLeftArrow = getWebDriver().findElement(By.cssSelector(".left .next.available"));
        leftMonthLeftArrow.click();
        leftMonthLeftArrow = getWebDriver().findElement(By.cssSelector(".left .next.available"));
        leftMonthLeftArrow.click();

        List<WebElement> leftDay = getWebDriver().findElements(By.cssSelector(".left .available"));
        leftDay.get(4).click();

        WebElement saveDatepicker = getWebDriver().findElement(By.cssSelector(DATEPICKER_SAVE_BUTTON_SELECTOR));
        saveDatepicker.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.invisibilityOfElementLocated(By.className(DATEPICKER_MODAL)));

        //check the right datepicker
        contentManager.waitContentSpinner();
        WebElement rightDatepicker = getWebDriver().findElement(By.cssSelector(RIGHT_DATEPICKER_OPEN_BUTTON_SELECTOR));
        Assert.assertEquals(rightDatepicker.getText(), DateUtils.date("2016-06-29 10:01:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE));

    }

    void setHourMinuteRightSide() {
        //hour on right side
        WebLocator hourright = new WebLocator().setClasses("calendar", "first", "right");
        WebLocator hRight = new WebLocator(hourright).setClasses("hourselect");
        hRight.click();
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.className("select2-result-label")));
        WebLocator input = new WebLocator().setClasses("select2-focused");
        input.sendKeys("22");
        applicationManager.pressEnter();
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.invisibilityOfElementLocated(By.className("select2-result-label")));

        //minute on right side
        WebLocator minuteright = new WebLocator().setClasses("calendar", "first", "right");
        WebLocator mRight = new WebLocator(minuteright).setClasses("minuteselect");
        mRight.click();
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.className("select2-result-label")));
        input = new WebLocator().setClasses("select2-focused");
        input.sendKeys("19");
        applicationManager.pressEnter();
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.invisibilityOfElementLocated(By.className("select2-result-label")));
    }

    void setHourMinuteLeftSide() {
        //hour on left side
        WebLocator hourleft = new WebLocator().setClasses("calendar", "second", "left");
        WebLocator hLeft = new WebLocator(hourleft).setClasses("hourselect");
        hLeft.click();
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.className("select2-result-label")));
        WebLocator input = new WebLocator().setClasses("select2-focused");
        input.sendKeys("13");
        applicationManager.pressEnter();
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.invisibilityOfElementLocated(By.className("select2-result-label")));

        //minute on left side
        WebLocator minuteleft = new WebLocator().setClasses("calendar", "second", "left");
        WebLocator mLeft = new WebLocator(minuteleft).setClasses("minuteselect");
        mLeft.click();
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.className("select2-result-label")));
        input = new WebLocator().setClasses("select2-focused");
        input.sendKeys("59");
        applicationManager.pressEnter();
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.invisibilityOfElementLocated(By.className("select2-result-label")));
    }

    void checkDatepickersArrows() {
        //left side left arrow
        WebElement leftMonthLeftArrow = getWebDriver().findElement(By.cssSelector(".left .next.available"));
        leftMonthLeftArrow.click();
        WebElement leftMonth = getWebDriver().findElement(By.cssSelector(".left .month"));
        Assert.assertEquals(leftMonth.getText(), "JUNE 2016");

        //left side right arrow
        WebElement leftMonthRightArrow = getWebDriver().findElement(By.cssSelector(".left .prev.available"));
        leftMonthRightArrow.click();
        leftMonth = getWebDriver().findElement(By.cssSelector(".left .month"));
        Assert.assertEquals(leftMonth.getText(), "MAY 2016");

        //right side left arrow
        WebElement rightMonthLeftArrow = getWebDriver().findElement(By.cssSelector(".right .next.available"));
        rightMonthLeftArrow.click();
        WebElement rightMonth = getWebDriver().findElement(By.cssSelector(".right .month"));
        Assert.assertEquals(rightMonth.getText(), "JUNE 2016");

        //left side right arrow
        WebElement rightMonthRightArrow = getWebDriver().findElement(By.cssSelector(".right .prev.available"));
        rightMonthRightArrow.click();
        rightMonth = getWebDriver().findElement(By.cssSelector(".right .month"));
        Assert.assertEquals(rightMonth.getText(), "MAY 2016");
    }
}
