package de.brueckner.fph.managers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.ContentManager.STATE_REPORT_HEADER_BUTTON;

public class StateReportManager {

    private static final Logger logger = LoggerFactory.getLogger(StateReportManager.class);

    public static final String STATE_REPORT_DATEPICKER = ".stateReportContainer .timeWrapper.brButton";
    public static final String STATE_REPORT_MODAL = ".stateReportContainer";
    public static final String STATE_REPORT_CLOSE = ".stateReportContainer .dbCancelClose";
    public static final String STATE_REPORT_SAVE = ".stateReportContainer .dbSaveDiskClose";
    public static final String STATE_REPORT_DATEPICKER_MONTH_NAME = ".daterangepicker.week-picker .calendarStyleContainer .rightContainer > div.calendar.first.single.right > div > table > thead > tr:nth-child(1) > th.month";
    public static final String STATE_REPORT_DATEPICKER_PREVIOUS_MONTH = ".daterangepicker.week-picker .calendarStyleContainer > div.calendarContainer.rightContainer > div.calendar.first.single.right > div > table > thead > tr:nth-child(1) > th.prev.available";
    public static final String STATE_REPORT_DATEPICKER_NEXT_MONTH = ".daterangepicker.week-picker .calendarStyleContainer > div.calendarContainer.rightContainer > div.calendar.first.single.right > div > table > thead > tr:nth-child(1) > th.next.available";
    public static final String STATE_REPORT_NOTES_CONTAINER = ".stateReportContainer div.box.col-xs-4.fullHeight.scrollbar > div.panel-body";
    public static final String STATE_REPORT_DATEPICKER_MODAL = ".daterangepicker.dropdown-menu.single.opensright.show-calendar";
    private static final String STATE_REPORT_SPINNER_SELECTOR = ".stateReportContainer  .contentSpinner";
    public static final String STATE_REPORT_NOTE_EXPAND_DETAILS = ".stateReportContainer > div.stateReportBody.slideFormBody  div.noteStateHeader .toggleVisibility";
    public static final String STATE_REPORT_NOTE_EDIT = ".stateReportContainer .dialogButton.dbEditClose";
    public static final String STATE_REPORT_NOTE_EDIT_SAVE = ".note.noteFormBody.editing .dialogButton.dbSaveClose";
    public static final String STATE_REPORT_NOTE_EDIT_CONTAINER = ".note.noteFormBody.editing";
    public static final String STATE_REPORT_NOTE_EDIT_TEXT = ".note.noteFormBody.editing textarea";
    public static final String STATE_REPORT_STATUS_PICKER = ".stateReportContainer .status-picker";
    public static final String STATE_REPORT_SUMMARY = ".stateReportContainer .stateSummary";
    public static final String STATE_REPORT_NOTE_BODY = ".stateReportContainer .note.noteFormBody";


    private WebDriver driver;

    public StateReportManager(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.findElement(By.cssSelector(STATE_REPORT_HEADER_BUTTON)).click();
        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(STATE_REPORT_MODAL)));
        waitStateReportSpinner();
    }

    public void openDatepicker() {
        driver.findElement(By.cssSelector(STATE_REPORT_DATEPICKER)).click();

        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(STATE_REPORT_DATEPICKER_MODAL)));
    }

    public void close() {
        driver.findElement(By.cssSelector(STATE_REPORT_CLOSE)).click();
        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(STATE_REPORT_MODAL)));
    }

    public void save() {
        driver.findElement(By.cssSelector(STATE_REPORT_SAVE)).click();
        waitStateReportSpinner();
    }

    public void waitStateReportSpinner() {
        logger.debug("Wait for timeline spinner to be hidden");
        WebDriverWait webDriverWait = new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS);
        ApplicationManager.waitForAngularLoad(webDriverWait);
        webDriverWait
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(STATE_REPORT_SPINNER_SELECTOR)));
    }

    public void editStateReportNote() {
        WebElement expandNote = driver.findElement(By.cssSelector(STATE_REPORT_NOTE_EXPAND_DETAILS));
        expandNote.click();

        WebElement editNote = driver.findElement(By.cssSelector(STATE_REPORT_NOTE_EDIT));
        editNote.click();

        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(STATE_REPORT_NOTE_EDIT_CONTAINER)));
    }

    public void noteStateReportSaveEdit() {
        WebElement saveEditNote = driver.findElement(By.cssSelector(STATE_REPORT_NOTE_EDIT_SAVE));
        saveEditNote.click();

        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(STATE_REPORT_NOTE_EDIT_CONTAINER)));
    }

    public void selectStateReportStatus(int index) {
        WebElement statusDropdown = driver.findElement(By.cssSelector(STATE_REPORT_STATUS_PICKER));
        statusDropdown.click();

        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".status-selecter")));

        WebElement status = driver.findElement(By.cssSelector(".status-selecter div:nth-child(" + index + ")"));
        status.click();

        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".status-selecter")));
    }

    public void stateReportSummary(String text) {
        WebElement summary = driver.findElement(By.cssSelector(STATE_REPORT_SUMMARY));
        summary.clear();
        summary.sendKeys(text);
    }
}
