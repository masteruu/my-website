package de.brueckner.fph.managers;

import com.sdl.selenium.web.WebLocator;
import de.brueckner.fph.util.MouseUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;

public class NoteManager {

    private static final Logger logger = LoggerFactory.getLogger(NoteManager.class);

    public static final String NOTE_BUTTON = ".notes";
    private static final String NOTE_ADD = "#timeline";
    public static final String NOTE_TEXT_FIELD = "#noteText";
    public static final String NOTE_STATE_REPORT_CHECKBOX = ".fakeCheckbox[for=\"stateReport\"]";
    public static final String NOTE_PRIVATE_CHECKBOX = ".fakeCheckbox[for=\"idPrivateNote\"]";
    public static final String NOTE_SAVE = ".noteFormContainer .dialogButton.dbSaveClose";
    public static final String NOTE_OPEN = ".eventTimeBasedPossition.note div";
    public static final String NOTE_DELETE = ".noteFormContainer .dialogButton.dbDeleteClose";
    public static final String NOTE_CLOSE = "#noteSubmit .dbCancelClose";
    public static final String NOTE_MODAL = ".noteFormContainer";
    public static final String NOTE_DATEPICKER_SELECTOR = ".noteFormContainer #noteTimestampView";
    public static final String NOTE_DATEPICKER_MODAL = ".daterangepicker.dropdown-menu.single.opensright.show-calendar";
    public static final String NOTE_DATEPICKER_HOUR_SELECT = ".calendarContainer.rightContainer .calendar.first.single.right .calendar-time > div.select2-container.hourselect";
    public static final String NOTE_DATEPICKER_MINUTE_SELECT = ".calendarContainer.rightContainer .calendar.first.single.right .calendar-time > div.select2-container.minuteselect";
    public static final String NOTE_ATTACHEMENT_INPUT = "#filesUploadNote";
    public static final String NOTE_ATTACHMENT_DELETE_BUTTON = ".noteFormContainer  .k-icon.k-i-close.k-delete";
    public static final String NOTE_ATTACHMENT_DELETE_NOTIFICATION = ".confirmText";
    public static final String NOTE_ATTACHMENT_NAME = ".noteUploaderUpload";
    public static final String NOTE_TOO_BIG_INFORMATION = ".notificationBody";
    private WebDriver driver;

    public NoteManager(WebDriver driver) {
        this.driver = driver;
    }

    public void noteButton() {
        driver.findElement(By.cssSelector(NOTE_BUTTON)).click();
    }

    public void addNote() {
        driver.findElement(By.cssSelector(NOTE_ADD)).click();
        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(NOTE_MODAL)));
    }

    public void noteStateReportCheckbox() {
        driver.findElement(By.cssSelector(NOTE_STATE_REPORT_CHECKBOX)).click();
    }

    public void save() {
        driver.findElement(By.cssSelector(NOTE_SAVE)).click();
        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(NOTE_MODAL)));
    }

    public void openNote() {
        driver.findElement(By.cssSelector(NOTE_OPEN)).click();
        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(NOTE_MODAL)));
    }

    public void deleteNote() {
        driver.findElement(By.cssSelector(NOTE_DELETE)).click();
        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(NOTE_MODAL)));
    }

    public void privateCheckbox() {
        driver.findElement(By.cssSelector(NOTE_PRIVATE_CHECKBOX)).click();
    }

    public void close() {
        driver.findElement(By.cssSelector(NOTE_CLOSE)).click();
        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(NOTE_MODAL)));
    }

    public void writeText(String textToWrite) {
        driver.findElement(By.cssSelector(NOTE_TEXT_FIELD)).sendKeys(textToWrite);
    }

    public void setMachinePartCausedByOption(String choice, int index, String selector) {
        List<WebElement> causedBy = driver.findElements(By.cssSelector(selector));
        causedBy.get(index).click();
        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.elementToBeClickable(By.className("select2-result-label")));

        WebLocator option = new WebLocator().setClasses("select2-result-label").setText(choice);
        option.click();
        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.invisibilityOfElementLocated(By.className("select2-result-label")));
    }

    public void openDatepicker() {
        WebElement noteDatepicker = driver.findElement(By.cssSelector(NOTE_DATEPICKER_SELECTOR));
        noteDatepicker.click();

        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(NOTE_DATEPICKER_MODAL)));
    }

    public void pressEnter() {
        logger.info("Press enter key");
        Keyboard Enter = ((RemoteWebDriver) driver).getKeyboard();
        Enter.pressKey(Keys.ENTER);
    }

    public String getNoteTooltipInTimeline() {
        WebElement noteInTimeline = driver.findElement(By.cssSelector(NOTE_OPEN));
        return getNoteTooltipText(noteInTimeline);
    }

    public String getNoteTooltipInTree() {
        WebElement noteInTree = driver.findElement(By.cssSelector("#tree .note.notTreeClick"));
        return getNoteTooltipText(noteInTree);
    }

    String getNoteTooltipText(WebElement noteInTree) {
        MouseUtils.mouseOver(noteInTree, driver);

        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.className("timelinePopover")));

        String tooltipText = driver.findElement(By.cssSelector(".timelinePopover")).getText();
        return tooltipText;
    }

    public String getNoteMachinePartTooltip() {
        WebElement noteInCampaignManager = driver.findElement(By.cssSelector(NOTE_OPEN));
        MouseUtils.mouseOver(noteInCampaignManager, driver);

        new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.className("timelinePopover")));

        String tooltipText = driver.findElement(By.cssSelector(".timelinePopover .noteMachinePart")).getText();
        return tooltipText;
    }

}
