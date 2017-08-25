package de.brueckner.fph.acceptance.selectionAdjustAlgorithm;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import de.brueckner.fph.util.MouseUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static de.brueckner.fph.managers.TimelineManager.*;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_LONG_TIME_FORMAT;

public class SelectionPanningBarLeftAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(SelectionPanningBarLeftAcceptanceTest.class);

    @Test
    public void selectionPanningBarLeftAcceptanceTest() {
        logger.info("SelectionPanningBarLeftAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //rolls are predefined as favorite and benchmark in this period
        timelineManager.setNewDateForDatePicker("2016-04-12 22:00:00", "2016-04-14 22:00:00");

        contentManager.waitContentSpinner();

        WebElement panningBar = getWebDriver().findElement(By.cssSelector(TIMELINE_PANNING_BAR));
        for (int i = 0; i < 10; i++) {
            MouseUtils.dragAndDrop(panningBar, -1500, getWebDriver());
            timelineManager.waitTimelineSpinner();
            contentManager.waitContentSpinner();

        }

        //check content dates (they should not change)
        contentManager.checkContentPeriod(DateUtils.date("2016-04-12 22:00:00", DEFAULT_SHORT_DATE_FORMAT), DateUtils.date("2016-04-14 22:00:00", DEFAULT_SHORT_DATE_FORMAT));
        //check that the left datepicker time is moving with the selection in this period
        checkDatepickersDates(DateUtils.date("2016-04-02 22:34:00", DEFAULT_SHORT_DATE_LONG_TIME_FORMAT), DateUtils.date("2016-04-14 22:00:00", DEFAULT_SHORT_DATE_LONG_TIME_FORMAT));

        for (int i = 0; i < 20; i++) {
            MouseUtils.dragAndDrop(panningBar, -1500, getWebDriver());
            timelineManager.waitTimelineSpinner();
            contentManager.waitContentSpinner();
        }

        //check content dates (they should not change)
        contentManager.checkContentPeriod(DateUtils.date("2016-04-12 22:00:00", DEFAULT_SHORT_DATE_FORMAT), DateUtils.date("2016-04-14 22:00:00", DEFAULT_SHORT_DATE_FORMAT));

        //check that both datepickers are moving in this period
        checkDatepickersDates(DateUtils.date("2016-03-14 02:03:00", DEFAULT_SHORT_DATE_LONG_TIME_FORMAT), DateUtils.date("2016-04-12 22:43:00", DEFAULT_SHORT_DATE_LONG_TIME_FORMAT));

    }

    void checkDatepickersDates(String dateLeft, String dateRight) {
        //datepicker dates
        WebElement datePickleft = getWebDriver().findElement(By.cssSelector(LEFT_DATEPICKER_OPEN_BUTTON_SELECTOR));
        String datePickerDateLeft = datePickleft.getText().replace("  ", " ") + ":00";
        WebElement datePickRight = getWebDriver().findElement(By.cssSelector(RIGHT_DATEPICKER_OPEN_BUTTON_SELECTOR));
        String datePickerRight = datePickRight.getText().replace("  ", " ") + ":00";
        DateUtils.compareDates(dateLeft, datePickerDateLeft);
        DateUtils.compareDates(dateRight, datePickerRight);
    }
}
