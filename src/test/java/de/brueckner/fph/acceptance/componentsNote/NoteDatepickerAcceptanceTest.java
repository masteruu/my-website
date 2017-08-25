package de.brueckner.fph.acceptance.componentsNote;


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

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.NoteManager.*;
import static de.brueckner.fph.util.DateUtils.DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE;

public class NoteDatepickerAcceptanceTest extends AbstractAcceptanceTest {
    private static final Logger logger = LoggerFactory.getLogger(NoteDatepickerAcceptanceTest.class);

    @Test
    public void noteDatepickerAcceptance() {
        logger.info("noteDatepickerAcceptance");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //add note and change its date from the note datepicker
        noteManager.noteButton();
        noteManager.addNote();
        noteManager.writeText("Text to be deleted");

        noteManager.openDatepicker();

        WebElement hour = getWebDriver().findElement(By.cssSelector(NOTE_DATEPICKER_HOUR_SELECT));
        hour.click();
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.visibilityOfElementLocated(By.className("select2-result-label")));

        WebElement input = getWebDriver().findElement(By.cssSelector("#select2-drop input"));
        input.sendKeys("0");
        applicationManager.pressEnter();
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS).until(ExpectedConditions.invisibilityOfElementLocated(By.className("select2-result-label")));

        applicationManager.pressEnter();
        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(NOTE_DATEPICKER_MODAL)));

        noteManager.save();

        //check if date has changed (tooltip)
        String tooltipText = noteManager.getNoteTooltipInTimeline();
        Assert.assertEquals(tooltipText, DateUtils.date("2016-05-09 22:59:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE) + "\n" +
                "MACHINE PART\n" +
                "Text to be deleted");

        //check if the date has changed in the note datepicker
        noteManager.openNote();

        WebElement noteDatepicker = getWebDriver().findElement(By.cssSelector(NOTE_DATEPICKER_SELECTOR));
        Assert.assertEquals(noteDatepicker.getText().trim(), DateUtils.date("2016-05-09 22:59:00", DEFAULT_SHORT_DATE_FORMAT_DOUBLE_SPACE));

        //delete note
        noteManager.deleteNote();

    }
}
