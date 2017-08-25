package de.brueckner.fph.acceptance.componentsTimeline;


import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.ContentManager.CONTENT_END_PERIOD;
import static de.brueckner.fph.managers.ContentManager.CONTENT_START_PERIOD;
import static de.brueckner.fph.managers.TimelineManager.LEFT_DATEPICKER_OPEN_BUTTON_SELECTOR;
import static de.brueckner.fph.managers.TimelineManager.RIGHT_DATEPICKER_OPEN_BUTTON_SELECTOR;

public class TimelineElementsSelectionAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(TimelineElementsSelectionAcceptanceTest.class);

    @Test
    public void timelineSelectionActual() {

        logger.info("TimelineElementsSelectionAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-19 22:00:00");
        contentManager.waitContentSpinner();

        //move to a more actual selection in the datepicker
        WebElement rightSelector = getWebDriver().findElement(By.cssSelector(".inverted.selectionDecorator"));
        new Actions(getWebDriver()).dragAndDropBy(rightSelector, -500, 0).build().perform();
        contentManager.waitContentSpinner();

        timelineManager.setNewDateForDatePicker("2016-04-18 22:00:00", "2016-04-19 22:00:00");
        contentManager.waitContentSpinner();

        //check if the new selection is moved at the beginning of the timeline
        WebElement datePleft = getWebDriver().findElement(By.cssSelector(LEFT_DATEPICKER_OPEN_BUTTON_SELECTOR));
        String datePickerDateLeft = datePleft.getText().replace("  ", " ");
        WebElement datePright = getWebDriver().findElement(By.cssSelector(RIGHT_DATEPICKER_OPEN_BUTTON_SELECTOR));
        String datePickerDateRight = datePright.getText().replace("  ", " ");

        contentManager.checkContentPeriod(datePickerDateLeft, datePickerDateRight);

    }

    @Test
    public void timelineSelectionOlder() {
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //move to a more actual selection in the datepicker
        WebElement rightSelector = getWebDriver().findElement(By.cssSelector(".selectionDecorator"));
        new Actions(getWebDriver()).dragAndDropBy(rightSelector, -500, 0).build().perform();
        contentManager.waitContentSpinner();

        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-13 22:00:00");

        //check if the new selection is moved at the beginning of the timeline
        WebElement datePright = getWebDriver().findElement(By.cssSelector(RIGHT_DATEPICKER_OPEN_BUTTON_SELECTOR));
        String datePickerDateRight = datePright.getText().replace("  ", " ");

        WebElement contentEndPeriod = getWebDriver().findElement(By.cssSelector(CONTENT_END_PERIOD));
        String dateEnd = contentEndPeriod.getText().trim();
        DateUtils.compareDates(datePickerDateRight, dateEnd);
    }

    @Test
    public void timelineSelectionSmaller() {
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-19 22:00:00");

        //set the smaller selection
        timelineManager.setNewDateForDatePicker("2016-04-12 23:00:00", "2016-04-19 22:00:00");

        //check if the new date matches the selection
        WebElement datePleft = getWebDriver().findElement(By.cssSelector(LEFT_DATEPICKER_OPEN_BUTTON_SELECTOR));
        String datePickerDateLeft = datePleft.getText().replace("  ", " ");
        WebElement datePright = getWebDriver().findElement(By.cssSelector(RIGHT_DATEPICKER_OPEN_BUTTON_SELECTOR));
        String datePickerDateRight = datePright.getText().replace("  ", " ");

        WebElement contentStartPeriod = getWebDriver().findElement(By.cssSelector(CONTENT_START_PERIOD));
        String dateStart = contentStartPeriod.getText().trim();
        WebElement contentEndPeriod = getWebDriver().findElement(By.cssSelector(CONTENT_END_PERIOD));
        String dateEnd = contentEndPeriod.getText().trim();

        DateUtils.compareDates(datePickerDateLeft, dateStart);
        DateUtils.compareDates(datePickerDateRight, dateEnd);
    }
}