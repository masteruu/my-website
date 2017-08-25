package de.brueckner.fph.managers;

import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.util.DateFormatShortOption;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.TimeFormatShortOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.List;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;

public class TimelineManager {

    private static final Logger logger = LoggerFactory.getLogger(TimelineManager.class);

    /**
     * Date picker selectors
     */
    public static final String LEFT_DATEPICKER_OPEN_BUTTON_SELECTOR = "#timeline .paddingContainer .leftSideText";
    public static final String RIGHT_DATEPICKER_OPEN_BUTTON_SELECTOR = "#timeline .paddingContainer .rightSideText";
    private static final String DATE_PICKER_START_INPUT_SELECTOR = ".daterangepicker_start_input .input-mini";
    private static final String DATE_PICKER_END_INPUT_SELECTOR = ".daterangepicker_end_input .input-mini";
    public static final String DATEPICKER_SAVE_BUTTON_SELECTOR = ".daterangepicker .dialogButton.dbSaveClose";
    public static final String DATEPICKER_CLOSE_BUTTON_SELECTOR = ".daterangepicker .dialogButton.dbCancelClose";
    public static final String DATEPICKER_MODAL = "daterangepicker";

    private static final String TIMELINE_SPINNER_SELECTOR = ".timelineContainer #timeline .contentSpinner";

    /**
     * Timeline next/previous selectors
     */
    public static final String TIMELINE_NEXT_ELEMENT = "#timeline .timeArrowRight";
    public static final String TIMELINE_PREVIOUS_ELEMENT = "#timeline .timeArrowLeft";
    private WebDriver driver;
    private ApplicationManager applicationManager;
    private ContentManager contentManager;

    /**
     * Timeline settings selectors
     **/
    public static final String TIMELINE_SETTINGS_MODAL = ".timelineSettingsModal";
    public static final String UNDER_2H_DROPDOWN = ".timelineSettingsList li:nth-child(1) .select2-chosen";
    public static final String UNDER_24H_DROPDOWN = ".timelineSettingsList li:nth-child(2) .select2-chosen";
    public static final String UNDER_3DAYS_DROPDOWN = ".timelineSettingsList li:nth-child(3) .select2-chosen";
    public static final String UNDER_1WEEK_DROPDOWN = ".timelineSettingsList li:nth-child(4) .select2-chosen";
    public static final String UNDER_1MONTH_DROPDOWN = ".timelineSettingsList li:nth-child(5) .select2-chosen";
    public static final String STATE_DROPDOWN_OPTION = ".select2-results li:nth-child(1)";
    public static final String PROD_NONPROD_DROPDOWN_OPTION = ".select2-results li:nth-child(2)";
    public static final String TIMELINE_SETTINGS_SAVE = ".timelineSettingsModal .dbSaveClose";
    public static final String TIMELINE_SETTINGS_DISPLAY = ".timelineSettingsModal .modal-tabs li:nth-child(1)";
    public static final String TIMELINE_SETTINGS_SHIFT_LANE = ".timelineSettingsModal .modal-tabs li:nth-child(2)";
    public static final String TIMELINE_SETTINGS_TREND = ".timelineSettingsModal .modal-tabs li:nth-child(3)";
    public static final String TIMELINE_SETTINGS_TREND_THICKNESS_CHECKBOX = ".timelineSettingsModal .fakeCheckboxLabel.Thickness";
    public static final String TIMELINE_SETTINGS_TREND_SPEED_CHECKBOX = ".timelineSettingsModal .fakeCheckboxLabel.Speed";
    public static final String TIMELINE_SETTINGS_TREND_OUTPUT_CHECKBOX = ".timelineSettingsModal .fakeCheckboxLabel.Output";

    public static final String TIMELINE_SCALE = "#timeline .background";
    public static final String TIMELINE_PANNING_BAR = "#timeline .paddingElementContainer";
    public static final String TIMELINE_TREND_CONTAINER = "#timeline .timelineTrendContainer";
    public static final String TIMELINE_TICKS_VALUES = ".x.axis.timelineAxisTranslate";


    public TimelineManager(WebDriver driver) {
        this.driver = driver;
        this.applicationManager = new ApplicationManager(driver);
        this.contentManager = new ContentManager(driver);
    }

    public void setNewDateForDatePicker(String startDate, String endDate) {
        logger.debug("Change timeline date picker to new period: start = {}, end = {}", startDate, endDate);

        /** Default date picker format is dd.MM.yy  HH:mm */
        String datePickerDefaultFormat = DateUtils.getNewDatepickerDateTimeFormatShort(DateFormatShortOption.OPTION_WITH_POINT,
                TimeFormatShortOption.OPTION_WITH_COLON);

        String convertedStartDate = DateUtils.date(startDate, datePickerDefaultFormat);
        String convertedEndDate = DateUtils.date(endDate, datePickerDefaultFormat);

        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS);
        WebElement datePickerLeft = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(LEFT_DATEPICKER_OPEN_BUTTON_SELECTOR)));
        datePickerLeft.click();

        //wait for date picker loading to finish
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(DATEPICKER_SAVE_BUTTON_SELECTOR)));

        WebElement startDateInput = driver.findElement(By.cssSelector(DATE_PICKER_START_INPUT_SELECTOR));
        applicationManager.sendKeysScript(startDateInput, convertedStartDate);
        logger.debug("Start date in date picker changed to {}", convertedStartDate);

        WebElement endDateInput = driver.findElement(By.cssSelector(DATE_PICKER_END_INPUT_SELECTOR));
        applicationManager.sendKeysScript(endDateInput, convertedEndDate);
        logger.debug("End date in date picker changed to {}", convertedEndDate);

        WebElement save = driver.findElement(By.cssSelector(DATEPICKER_SAVE_BUTTON_SELECTOR));
        save.click();

        waitTimelineSpinner();
    }

    public void timelineSettingsSetDropdownOption(String dropd, String choice) {
        openTimelineSettings();
        WebElement dropdown = driver.findElement(By.cssSelector(dropd));
        dropdown.click();
        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.elementToBeClickable(By.className("select2-result-label")));
        WebElement option = driver.findElement(By.cssSelector(choice));
        option.click();
        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.invisibilityOfElementLocated(By.className("select2-result-label")));
        WebElement saveTimelineSettings = driver.findElement(By.cssSelector(TIMELINE_SETTINGS_SAVE));
        saveTimelineSettings.click();
        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(TIMELINE_SETTINGS_MODAL)));
    }

    public void checkFitToSelection() {
        WebElement timelineMainSvg = driver.findElement(By.cssSelector("#timelineMainSvg"));
        Float timelineRealLength = Float.parseFloat(timelineMainSvg.getAttribute("width"));
        WebElement checkFit = driver.findElement(By.cssSelector(".inverted.selectionDecorator"));
        Float timelineRightSelectionX = Float.parseFloat(checkFit.getAttribute("x"));

        checkFit = driver.findElement(By.cssSelector(".selectionDecorator"));
        Float timelineLeftSelectionX = Float.parseFloat(checkFit.getAttribute("x"));

        if (timelineLeftSelectionX != 1) {
            Assert.fail();
        }

        int precision = 1;
        if (Math.abs(timelineRealLength - timelineRightSelectionX) > precision) {
            Assert.fail();
        }
    }

    public void openTimelineSettings() {
        WebElement timelineSettings = driver.findElement(By.cssSelector(".timelineSettingsCtrl"));
        timelineSettings.click();
        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(TIMELINE_SETTINGS_MODAL)));
    }

    public void clickRoll(String uid) {
        WebLocator rollTest = new WebLocator().setClasses("rollStatusLaneElement").setAttribute("uid", uid);
        rollTest.click();
        waitTimelineSpinner();
    }

    public void clickCampaign(String uid) {
        WebLocator campaignTest = new WebLocator().setClasses("campaignLaneElement").setAttribute("uid", uid);
        campaignTest.click();
        waitTimelineSpinner();
    }

    public void clickMonth(String uid) {
        WebLocator campaignTest = new WebLocator().setClasses("rollMonthStausLaneElement").setAttribute("uid", uid);
        campaignTest.click();
        waitTimelineSpinner();
    }

    public void clickWeek(String uid) {
        WebLocator campaignTest = new WebLocator().setClasses("rollWeekStausLaneElement").setAttribute("uid", uid);
        campaignTest.click();
        waitTimelineSpinner();
    }

    public void clickYear(String uid) {
        WebLocator campaignTest = new WebLocator().setClasses("rollyearStausLaneElement").setAttribute("uid", uid);
        campaignTest.click();
        waitTimelineSpinner();
    }

    public void selectMaxPeriod() {

        List<WebElement> leftArrow = driver.findElements(By.cssSelector(".calendar.left th.prev.available"));

        while (leftArrow.size() > 0) {
            leftArrow.get(0).click();
            leftArrow = driver.findElements(By.cssSelector(".calendar.left th.prev.available"));
        }

        List<WebElement> firstDay = driver.findElements(By.cssSelector(".calendar.left td.available"));
        firstDay.get(0).click();
        applicationManager.pressEnter();
        waitTimelineSpinner();
        contentManager.waitContentSpinner();
    }

    public void checkTimeOnScale(String day,
                                 String hour) {
        WebLocator tick = new WebLocator().setClasses("tick")
                .setText(day);
        tick.assertReady();
        tick = new WebLocator().setText(hour);
        tick.assertReady();
    }

    public void waitTimelineSpinner() {
        logger.debug("Wait for timeline spinner to be hidden");
        WebDriverWait webDriverWait = new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS);
        ApplicationManager.waitForAngularLoad(webDriverWait);
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(TIMELINE_SPINNER_SELECTOR)));
    }
}
