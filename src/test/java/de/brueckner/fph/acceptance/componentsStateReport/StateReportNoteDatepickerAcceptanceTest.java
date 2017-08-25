package de.brueckner.fph.acceptance.componentsStateReport;

import de.brueckner.fph.acceptance.config.AbstractAcceptanceTest;
import de.brueckner.fph.util.DateUtils;
import de.brueckner.fph.util.LoginCredentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.NoteManager.NOTE_DATEPICKER_MODAL;
import static de.brueckner.fph.util.DateUtils.DEFAULT_LONG_DATE_SHORT_TIME_FORMAT;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE;

public class StateReportNoteDatepickerAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(StateReportNoteDatepickerAcceptanceTest.class);

    @Test
    public void stateReportNoteDatepickerAcceptanceTest() {
        logger.info("stateReportNoteDatepickerAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //go to CW18 in the timeline
        timelineManager.setNewDateForDatePicker("2016-05-01 22:00:00", "2016-05-07 22:00:00");
        contentManager.waitContentSpinner();

        //add a note and set in to state report
        noteManager.noteButton();
        noteManager.addNote();
        noteManager.writeText("Text to be deleted");
        noteManager.noteStateReportCheckbox();
        noteManager.save();

        stateReportManager.open();

        stateReportManager.editStateReportNote();

        WebElement noteDatepicker = getWebDriver().findElement(By.cssSelector(".noteTimestampView"));
        noteDatepicker.click();

        //set the note date to 00:57 hour
        List<WebElement> noteHour = getWebDriver().findElements(By.cssSelector(".calendarContainer.rightContainer div.select2-container.hourselect"));
        noteHour.get(2).click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.className("select2-result-label")));

        WebElement input = getWebDriver().findElement(By.cssSelector("#select2-drop input"));
        input.sendKeys("0");
        applicationManager.pressEnter();
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.invisibilityOfElementLocated(By.className("select2-result-label")));

        applicationManager.pressEnter();
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(NOTE_DATEPICKER_MODAL)));

        stateReportManager.noteStateReportSaveEdit();

        //check if the date has changed
        noteDatepicker = getWebDriver().findElement(By.cssSelector(".noteTimestampView"));
        Assert.assertEquals(noteDatepicker.getText(), DateUtils.date("2016-05-03 22:57:00", DEFAULT_LONG_DATE_SHORT_TIME_FORMAT));

        stateReportManager.close();

        //check note new date in timeline tooltip
        Assert.assertEquals(noteManager.getNoteTooltipInTimeline(), DateUtils.date("2016-05-03 22:57:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE) + "\n" +
                "MACHINE PART\n" +
                "Text to be deleted");

        //delete note
        noteManager.openNote();
        noteManager.deleteNote();
    }

}