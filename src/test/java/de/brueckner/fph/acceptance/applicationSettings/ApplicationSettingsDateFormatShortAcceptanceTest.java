package de.brueckner.fph.acceptance.applicationSettings;

import de.brueckner.fph.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static de.brueckner.fph.managers.ContentManager.*;
import static de.brueckner.fph.managers.TimelineManager.LEFT_DATEPICKER_OPEN_BUTTON_SELECTOR;
import static de.brueckner.fph.managers.TimelineManager.RIGHT_DATEPICKER_OPEN_BUTTON_SELECTOR;

public class ApplicationSettingsDateFormatShortAcceptanceTest extends AbstractApplicationSettingsTest {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationSettingsDateFormatShortAcceptanceTest.class);

    @Test
    public void applicationSettings_dateFormatShort_dd_MM_yyyy() {
        //date format long dd.MM.yyyy
        testChangeDateFormatShort(DateFormatShortOption.OPTION_WITH_POINT);
    }

    @Test
    public void applicationSettings_dateFormatShort_yyyy_MM_dd() {
        //date format long yyyy-MM-dd
        testChangeDateFormatShort(DateFormatShortOption.OPTION_WITH_DASH);
    }

    @Test
    public void applicationSettings_dateFormatShort_MM_dd_yyyy() {
        //date format long MM/dd/yyyy
        testChangeDateFormatShort(DateFormatShortOption.OPTION_WITH_SLASH);
    }

    private void testChangeDateFormatShort(DateFormatShortOption formatToCheck) {
        logger.info("Test changing date format short to {} from application settings", formatToCheck.getOptionText());

        String startDateString = "2016-04-12 22:00:00";
        String endDateString = "2016-04-13 03:00:00";

        //format for timeline "dd.MM.YY  HH:mm:ss"
        String tooltipFormat = DateUtils.getNewDatepickerDateShortTimeLong(formatToCheck,
                TimeFormatLongOption.OPTION_WITH_COLON);

        //format for timeline "dd.MM.YY  HH:mm"
        String shortDateShortTime = DateUtils.getNewDatepickerDateTimeFormatShort(formatToCheck,
                TimeFormatShortOption.OPTION_WITH_COLON);

        //format for timeline "dd.MM.YYHH:mm"
        String dateFormatTimelineTicks = formatToCheck.getOptionText() + TimeFormatShortOption.OPTION_WITH_COLON.getOptionText();

        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());
        timelineManager.setNewDateForDatePicker(startDateString, endDateString);
        applicationSettingsManager.setNewDateOrTimeFormat(formatToCheck.getOptionText(), 1);

        dateOnBreadcrum(shortDateShortTime);
        dateCampaignTooltipTimeline(tooltipFormat);
        dateOnRollTooltipTimeline(tooltipFormat);
        dateOnTimelineTrend(dateFormatTimelineTicks);
        dateOnTimelineDatepickers(shortDateShortTime);
        dateInContentPeriod(formatToCheck.getOptionText());
        dateInTreeTooltips(tooltipFormat);
        //dateInAlertTable(tooltipFormat); (ALERT TABLE IS DISABLED)
    }

    private void dateOnRollTooltipTimeline(String tooltipFormat) {
        String tooltipText = applicationManager.getTooltipContentText("*[uid='roll:A160412220525:1460498725459:1460504913271']");
        String expectedText = DateUtils.date("2016-04-12 22:05:25", tooltipFormat) + "\n" +
                DateUtils.date("2016-04-12 23:48:33", tooltipFormat) + "1 h 43 min";
        Assert.assertEquals(tooltipText, expectedText, "Date on roll tooltip from timeline is not formatted correctly");
    }

    private void dateCampaignTooltipTimeline(String tooltipFormat) {
        String tooltipText = applicationManager.getTooltipContentText("*[uid='campaign:160412114726310:1460461646310:1460511701759']");
        String expectedText = DateUtils.date("2016-04-12 11:47:26", tooltipFormat) + "\n" +
                DateUtils.date("2016-04-13 01:41:41", tooltipFormat) + "13 h 54 min";
        Assert.assertEquals(tooltipText, expectedText, "Date on campaign tooltip from timeline is not formatted correctly");
    }

    private void dateOnBreadcrum(String shortDateShortTime) {
        WebElement dateOnBreadcrum = getWebDriver().findElement(By.cssSelector(".breadcrumbSelected"));
        Assert.assertEquals(dateOnBreadcrum.getText(), DateUtils.getTreeDatepickerDateString("2016-04-12 22:00:00", "2016-04-13 03:00:00", shortDateShortTime), "Date on breadcrumb is not formatted correctly");
    }

    private void dateInAlertTable(String dateFormat) {
        timelineManager.setNewDateForDatePicker("2016-04-19 11:47:00", "2016-04-20 11:48:00");
        contentManager.waitContentSpinner();
        contentManager.openTab(ALERT_TAB_TEXT);

        List<WebElement> dateInTable = getWebDriver().findElements(By.cssSelector(".colt4"));
        Assert.assertEquals(dateInTable.get(2).getText().trim(), DateUtils.date("2016-04-19 20:45:18", dateFormat), "Date in Alert table is not formatted correctly");
    }

    private void dateInTreeTooltips(String dateFormat) {
        treeManager.openTree();

        //date on campaign tooltip from tree
        String tooltipText = applicationManager.getTooltipContentText("#tree .name.treeRow", 1);
        Assert.assertEquals(tooltipText.substring(0, 18), DateUtils.date("2016-04-13 01:41:41", dateFormat), "Date in campaign tooltip from tree is not formatted correctly");

        treeManager.expandNode(0);

        //date on productive tooltip from tree
        tooltipText = applicationManager.getTooltipContentText("#tree .name.treeRow", 2);
        Assert.assertEquals(tooltipText.substring(0, 18), DateUtils.date("2016-04-13 01:41:41", dateFormat), " Date in productive tooltip from tree is not formatted correctly");

        treeManager.expandNode(0);

        //date on roll tooltip from tree
        tooltipText = applicationManager.getTooltipContentText("#tree .name.treeRow", 3);
        Assert.assertEquals(tooltipText.substring(0, 18), DateUtils.date("2016-04-13 01:41:41", dateFormat), "Date in roll tooltip from tree is not formatted correctly");

        treeManager.closeTree();

    }

    private void dateInContentPeriod(String dateFormat) {
        WebElement startPeriod = getWebDriver().findElement(By.cssSelector(CONTENT_START_DATE));
        Assert.assertEquals(startPeriod.getText().trim(), DateUtils.date("2016-04-12 22:00:00", dateFormat), "Date in content is not formatted correctly");
        WebElement endPeriod = getWebDriver().findElement(By.cssSelector(CONTENT_END_DATE));
        Assert.assertEquals(endPeriod.getText().trim(), DateUtils.date("2016-04-13 03:00:00", dateFormat));
    }

    private void dateOnTimelineDatepickers(String shortDateShortTime) {
        //date on datepicker left
        WebElement dateOnDatepickerLeft = getWebDriver().findElement(By.cssSelector(LEFT_DATEPICKER_OPEN_BUTTON_SELECTOR));
        Assert.assertEquals(dateOnDatepickerLeft.getText(), DateUtils.date("2016-04-12 22:00:00", shortDateShortTime), "Date in timeline datepicker left is not formatted correctly");

        //date on datepicker right
        WebElement dateOnDatepickerRight = getWebDriver().findElement(By.cssSelector(RIGHT_DATEPICKER_OPEN_BUTTON_SELECTOR));
        Assert.assertEquals(dateOnDatepickerRight.getText(), DateUtils.date("2016-04-13 03:00:00", shortDateShortTime), "Date in timeline datepicker right is not formatted correctly");
    }

    private void dateOnTimelineTrend(String dateFormat) {
        List<WebElement> dateOnTimeline = getWebDriver().findElements(By.cssSelector(".tick"));
        Assert.assertEquals(dateOnTimeline.get(5).getText(), DateUtils.date("2016-04-12 22:30:00", dateFormat), "Date in timeline trend is not formatted correctly");
    }
}