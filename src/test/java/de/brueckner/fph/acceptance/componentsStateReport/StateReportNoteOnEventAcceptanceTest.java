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

import static de.brueckner.fph.managers.ApplicationManager.WAIT_TIME_OUT_IN_SECONDS;
import static de.brueckner.fph.managers.NoteManager.NOTE_BUTTON;
import static de.brueckner.fph.managers.NoteManager.NOTE_MODAL;

public class StateReportNoteOnEventAcceptanceTest extends AbstractAcceptanceTest {

    private static final Logger logger = LoggerFactory.getLogger(StateReportNoteOnEventAcceptanceTest.class);

    @Test
    public void stateReportNoteOnEventAcceptance() {

        //state report should be accessible from the beginning of data, not only 51 weeks ago

        logger.info("StateReportNoteOnEventAcceptanceTest");
        applicationManager.loginPage(LoginCredentials.USER_DEV.getUsername(), LoginCredentials.USER_DEV.getPassword());

        //setting the date picker to a specific date
        timelineManager.setNewDateForDatePicker("2016-05-05 18:45:00", "2016-05-05 19:00:00");
        contentManager.waitContentSpinner();

        //add note to event TDO_BREAK
        WebElement notes = getWebDriver().findElement(By.cssSelector(NOTE_BUTTON));
        notes.click();

        WebElement event = getWebDriver().findElement(By.cssSelector(".eventLaneElement"));
        event.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(NOTE_MODAL)));

        noteManager.writeText("Text to be deleted");
        noteManager.noteStateReportCheckbox();
        noteManager.save();

        stateReportManager.open();

        //check note on event body
        WebElement noteBody = getWebDriver().findElement(By.cssSelector(".noteStateHeader"));
        Assert.assertEquals(noteBody.getText().trim(), DateUtils.date("2016-05-05 18:54:20", "dd.MM.YYYYHH:mm") + "\n" +
                "TDO_BREAK\n" +
                "Text to be deleted");

        stateReportManager.close();

        //delete note on event
        event = getWebDriver().findElement(By.cssSelector(".eventLaneElement"));
        event.click();

        new WebDriverWait(getWebDriver(), WAIT_TIME_OUT_IN_SECONDS)
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(NOTE_MODAL)));

        noteManager.deleteNote();

    }
}
